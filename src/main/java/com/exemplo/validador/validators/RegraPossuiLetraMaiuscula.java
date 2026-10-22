package com.exemplo.validador.validators;

public class RegraPossuiLetraMaiuscula implements ValidacaoSenha {
    @Override
    public boolean validar(String senha) {
        return senha.chars().anyMatch(Character::isUpperCase);
    }
}
