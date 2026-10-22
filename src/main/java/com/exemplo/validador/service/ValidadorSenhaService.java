package com.exemplo.validador.service;

import com.exemplo.validador.validators.RegraValidacaoSenha;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidadorSenhaService {

    private final List<RegraValidacaoSenha> regras;

    public ValidadorSenhaService(List<RegraValidacaoSenha> regras) {
        this.regras = List.copyOf(regras);
    }

    public boolean validar(String senha) {
        if (senha == null || senha.isEmpty()) {
            return false;
        }

        boolean contemEspacoEmBranco =
                senha.chars().anyMatch(Character::isWhitespace);

        if (contemEspacoEmBranco) {
            return false;
        }

        return regras.stream()
                .allMatch(regra -> regra.validar(senha));
    }
}
