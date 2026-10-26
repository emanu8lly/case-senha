package com.exemplo.validador.validators;

import org.springframework.stereotype.Component;

@Component
public class RegraPossuiLetraMinuscula implements ValidacaoSenha {

    @Override
    public boolean validar(String senha) {
        return senha.chars().anyMatch(Character::isLowerCase);
    }
}
