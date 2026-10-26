package com.exemplo.validador.validators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RegraPossuiDigitoTest {

    private final RegraPossuiDigito regra = new RegraPossuiDigito();

    @Test
    @DisplayName("Deve retornar true quando a senha contiver pelo menos um dígito numérico")
    void deveValidarQuandoSenhaContiverDigito() {
        String senhaComDigito = "SenhaValida1";
        boolean resultado = regra.validar(senhaComDigito);
        assertThat(resultado).isTrue();
    }

    @Test
    @DisplayName("Deve retornar false quando a senha não contiver dígitos numéricos")
    void deveInvalidarQuandoSenhaNaoContiverDigito() {
        String senhaSemDigito = "ApenasLetras";
        boolean resultado = regra.validar(senhaSemDigito);
        assertThat(resultado).isFalse();
    }
}