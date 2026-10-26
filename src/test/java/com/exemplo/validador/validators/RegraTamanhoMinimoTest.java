package com.exemplo.validador.validators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RegraTamanhoMinimoTest {

    private final RegraTamanhoMinimo regra = new RegraTamanhoMinimo();

    @Test
    @DisplayName("Deve retornar verdadeiro quando a senha tiver 9 ou mais caracteres")
    void deveValidarTamanhoMinimoComSucesso() {
        String senhaValida = "SenhaLonga123";
        boolean resultado = regra.validar(senhaValida);
        assertThat(resultado).isTrue();
    }

    @Test
    @DisplayName("Deve retornar falso quando a senha tiver menos de 9 caracteres")
    void deveInvalidarSenhaComTamanhoInsuficiente() {
        String senhaCurta = "Curta1";
        boolean resultado = regra.validar(senhaCurta);
        assertThat(resultado).isFalse();
    }
}