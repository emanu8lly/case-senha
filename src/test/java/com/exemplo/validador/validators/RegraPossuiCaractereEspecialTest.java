package com.exemplo.validador.validators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RegraPossuiCaractereEspecialTest {
    private final RegraPossuiCaractereEspecial regra = new RegraPossuiCaractereEspecial();

    @Test
    @DisplayName("Deve retornar true quando a senha contiver pelo menos um caractere especial")
    void deveValidarQuandoSenhaContiverCaractereEspecial() {
        String senhaComCaractereEspecial = "Senha@123";
        boolean resultado = regra.validar(senhaComCaractereEspecial);
        assertThat(resultado).isTrue();
    }

    @Test
    @DisplayName("Deve retornar false quando a senha não contiver caracteres especiais")
    void deveInvalidarQuandoSenhaNaoContiverCaractereEspecial() {
        String senhaSemCaractereEspecial = "Senha123";
        boolean resultado = regra.validar(senhaSemCaractereEspecial);
        assertThat(resultado).isFalse();
    }
}