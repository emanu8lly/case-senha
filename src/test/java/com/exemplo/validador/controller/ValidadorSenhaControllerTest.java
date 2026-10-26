package com.exemplo.validador.controller;

import com.exemplo.validador.service.ValidadorSenhaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ValidadorSenhaController.class)
class ValidadorSenhaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ValidadorSenhaService validadorSenhaService;

    @Test
    @DisplayName("Deve retornar 200 OK com true quando a senha for válida")
    void deveRetornarTrueParaSenhaValida() throws Exception {
        when(validadorSenhaService.validar("AbTp9!fok")).thenReturn(true);

        mockMvc.perform(post("/senhas/validacao")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("AbTp9!fok"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    @DisplayName("Deve retornar 200 OK com false quando a senha for inválida")
    void deveRetornarFalseParaSenhaInvalida() throws Exception {
        when(validadorSenhaService.validar("senhafraca")).thenReturn(false);

        mockMvc.perform(post("/senhas/validacao")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("senhafraca"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    @DisplayName("Deve retornar 400 Bad Request quando o Content-Type estiver vazio")
    void deveRetornarBadRequestQuandoContentTypeVazio() throws Exception {
        mockMvc.perform(post("/senhas/validacao")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(""))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.mensagem").exists());
    }

    @Test
    @DisplayName("Deve retornar 415 Unsupported Media Type quando o Content-Type não for text/plain")
    void deveRetornarUnsupportedMediaTypeQuandoContentTypeInvalido() throws Exception {
        mockMvc.perform(post("/senhas/validacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"senha\": \"AbTp9!fok\"}"))
                .andExpect(status().isUnsupportedMediaType())
                .andExpect(jsonPath("$.status").value(415))
                .andExpect(jsonPath("$.mensagem").exists());
    }

    @Test
    @DisplayName("Deve retornar 500 Internal Server Error quando ocorrer um erro inesperado no serviço")
    void deveRetornarErroInterno500() throws Exception {
        when(validadorSenhaService.validar(anyString())).thenThrow(new RuntimeException("Falha simulada na validação"));

        mockMvc.perform(post("/senhas/validacao")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("SenhaQualquer123"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.mensagem").value("Ocorreu um erro interno no servidor. Por favor, tente novamente mais tarde."));
    }
}