package br.com.clyvocare.clyvocare_api.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ErroValidacaoResponse(
        int status,
        String mensagem,
        Map<String, String> erros,
        LocalDateTime timestamp
) {}
