package com.exemplo.validador.validators;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RegraSemCaracteresRepetidos implements ValidacaoSenha {

    @Override
    public boolean validar(String senha) {
        Set<Character> caracteres = new HashSet<>();
        for (char c : senha.toCharArray()) {
            if (!caracteres.add(c)) {
                return false; // Caractere repetido encontrado
            }
        }
        return true; // Nenhum caractere repetido encontrado
    }
}
