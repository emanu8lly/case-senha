package com.exemplo.validador.service;

import com.exemplo.validador.validators.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidadorSenhaServiceTest {

    private final ValidadorSenhaService service = new ValidadorSenhaService(List.of(
            new RegraTamanhoMinimo(),
            new RegraPossuiDigito(),
            new RegraPossuiLetraMaiuscula(),
            new RegraPossuiLetraMinuscula(),
            new RegraPossuiCaractereEspecial(),
            new RegraSemCaracteresRepetidos()
    ));

    @Test
    @DisplayName("Deve rejeitar senha nula")
    void deveRejeitarSenhaNula() {
        assertFalse(service.validar(null));
    }

    @Test
    @DisplayName("Deve aceitar senha com exatamente 9 caracteres válidos")
    void deveAceitarSenhaComExatamenteNoveCaracteres() {
        assertTrue(service.validar("Ab3!fok9x"));
    }

    @Test
    @DisplayName("Deve rejeitar senha com tabulação ou quebra de linha")
    void deveRejeitarSenhaComTabulacaoOuQuebraDeLinha() {
        assertFalse(service.validar("Ab3!fok\t1"));
        assertFalse(service.validar("Ab3!fok\n1"));
    }

    @Test
    @DisplayName("Deve rejeitar repetição no primeiro ou no último caractere")
    void deveRejeitarRepeticaoNoInicioEOuNoFim() {
        // Primeiro caractere repetido no final ('A' e 'A')
        assertFalse(service.validar("Ab3!fok1A"));

        // Dois últimos caracteres iguais ('1' e '1')
        assertFalse(service.validar("Ab3!fok11"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "+"})
    @DisplayName("Deve aceitar cada um dos caracteres especiais permitidos")
    void deveAceitarDiferentesCaracteresEspeciais(String especial) {
        String senha = "Ab3" + especial + "fok12";
        assertTrue(service.validar(senha),
                "Falhou ao validar com o caractere especial: " + especial);
    }

    @ParameterizedTest(name = "Senha: \"{0}\" deve retornar {1}")
    @MethodSource("fornecerSenhasParaTeste")
    @DisplayName("Deve validar corretamente diferentes cenários de senhas através de testes parametrizados")
    void deveValidarCenariosDeSenhas(String senha, boolean resultadoEsperado) {
        boolean resultadoObtido = service.validar(senha);
        assertThat(resultadoObtido).isEqualTo(resultadoEsperado);
    }

    private static Stream<Arguments> fornecerSenhasParaTeste() {
        return Stream.of(
                Arguments.of("", false),
                Arguments.of("aa", false),
                Arguments.of("ab", false),
                Arguments.of("AAAbbbCc", false),
                Arguments.of("AbTp9!foo", false),
                Arguments.of("AbTp9!foA", false),
                Arguments.of("AbTp9 fok", false),
                Arguments.of("AbTp9!fok", true)
        );
    }
}