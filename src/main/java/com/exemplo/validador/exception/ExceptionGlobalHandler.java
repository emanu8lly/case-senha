package com.exemplo.validador.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionGlobalHandler {

    /**
     * Trata quando o corpo da requisição está ausente ou ilegível (400 Bad Request).
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        Map<String, Object> corpoErro = new HashMap<>();
        corpoErro.put("timestamp", LocalDateTime.now());
        corpoErro.put("status", HttpStatus.BAD_REQUEST.value());
        corpoErro.put("mensagem", "O corpo da requisição é obrigatório e está ausente ou inválido.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(corpoErro);
    }

    /**
     * Trata quando o Content-Type enviado é inválido/não suportado (415 Unsupported Media Type).
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Map<String, Object>> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
        Map<String, Object> corpoErro = new HashMap<>();
        corpoErro.put("timestamp", LocalDateTime.now());
        corpoErro.put("status", HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
        corpoErro.put("mensagem", "A senha deve ser enviada como um texto comum e direto (sem formatações ou chaves de JSON). Certifique-se de configurar o formato de envio (Content-Type) como 'text/plain'.");
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(corpoErro);
    }

    /**
     * Trata quaisquer falhas inesperadas no servidor (500 Internal Server Error).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> tratarErroInterno(Exception e) {
        Map<String, Object> corpoErro = new HashMap<>();
        corpoErro.put("timestamp", LocalDateTime.now());
        corpoErro.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        corpoErro.put("mensagem", "Ocorreu um erro interno no servidor. Por favor, tente novamente mais tarde.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(corpoErro);
    }
}