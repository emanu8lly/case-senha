package com.exemplo.validador.controller;

import com.exemplo.validador.service.ValidadorSenhaService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/senhas")
public class ValidadorSenhaController {
    private final ValidadorSenhaService validadorSenhaService;

    public ValidadorSenhaController(ValidadorSenhaService validadorSenhaService) {
        this.validadorSenhaService = validadorSenhaService;
    }

    @PostMapping(value = "/validacao", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Boolean> validarSenha(@RequestBody String senha) {
        boolean resultado = validadorSenhaService.validar(senha);
        return ResponseEntity.ok(resultado);
    }
}
