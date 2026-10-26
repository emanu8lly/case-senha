package com.exemplo.validador.validators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RegraSemCaracteresRepetidosTest {

    private final RegraSemCaracteresRepetidos regra = new RegraSemCaracteresRepetidos();

    @Test
    @DisplayName("Deve retornar true quando a senha não possuir caracteres repetidos")
    void deveValidarQuandoSenhaNaoTiverCaracteresRepetidos() {
        String senhaSemRepeticao = "Abc123!@#";
        boolean resultado = regra.validar(senhaSemRepeticao);
        assertThat(resultado).isTrue();
    }

    @Test
    @DisplayName("Deve retornar false quando a senha possuir caracteres repetidos")
    void deveInvalidarQuandoSenhaTiverCaracteresRepetidos() {
        String senhaComRepeticao = "Aabb12345";
        boolean resultado = regra.validar(senhaComRepeticao);
        assertThat(resultado).isFalse();
    }
}