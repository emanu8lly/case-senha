package com.exemplo.validador.validators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RegraPossuiLetraMinusculaTest {

    private final RegraPossuiLetraMinuscula regra = new RegraPossuiLetraMinuscula();

    @Test
    @DisplayName("Deve retornar true quando a senha contiver pelo menos uma letra minúscula")
    void deveValidarQuandoSenhaContiverLetraMinuscula() {
        String senhaComMinuscula = "SENHA123a";
        boolean resultado = regra.validar(senhaComMinuscula);
        assertThat(resultado).isTrue();
    }

    @Test
    @DisplayName("Deve retornar false quando a senha não contiver letras minúsculas")
    void deveInvalidarQuandoSenhaNaoContiverLetraMinuscula() {
        String senhaSemMinuscula = "SENHA123!";
        boolean resultado = regra.validar(senhaSemMinuscula);
        assertThat(resultado).isFalse();
    }
}