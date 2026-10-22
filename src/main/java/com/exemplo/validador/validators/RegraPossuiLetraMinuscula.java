package com.exemplo.validador.validators;

public class RegraPossuiLetraMinuscula implements ValidacaoSenha {

    @Override
    public boolean validar(String senha) {
        return senha.chars().anyMatch(Character::isLowerCase);
    }
}
