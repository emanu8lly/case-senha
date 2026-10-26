package com.exemplo.validador.validators;

import org.springframework.stereotype.Component;

@Component
public class RegraTamanhoMinimo implements ValidacaoSenha {

    @Override
    public boolean validar(String senha) {
        return senha.length() >= ConstantesValidacaoSenha.TAMANHO_MINIMO;
    }
}
