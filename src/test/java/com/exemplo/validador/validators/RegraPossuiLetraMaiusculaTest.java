package com.exemplo.validador.validators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RegraPossuiLetraMaiusculaTest {

    private final RegraPossuiLetraMaiuscula regra = new RegraPossuiLetraMaiuscula();

    @Test
    @DisplayName("Deve retornar true quando a senha contiver pelo menos uma letra maiúscula")
    void deveValidarQuandoSenhaContiverLetraMaiuscula() {
        String senhaComMaiuscula = "senhaMaiuscula1";
        boolean resultado = regra.validar(senhaComMaiuscula);
        assertThat(resultado).isTrue();
    }

    @Test
    @DisplayName("Deve retornar false quando a senha não contiver letras maiúsculas")
    void deveInvalidarQuandoSenhaNaoContiverLetraMaiuscula() {
        String senhaSemMaiuscula = "senhamaiuscula1";
        boolean resultado = regra.validar(senhaSemMaiuscula);
        assertThat(resultado).isFalse();
    }
}