package com.exemplo.validador.validators;

import org.springframework.stereotype.Component;

@Component
public class RegraPossuiCaractereEspecial implements ValidacaoSenha {

    @Override
    public boolean validar(String senha) {
        return senha.chars()
                .mapToObj(c-> (char) c)
                .anyMatch(c -> ConstantesValidacaoSenha.CARACTERES_ESPECIAIS.indexOf(c) >= 0);
    }
}
