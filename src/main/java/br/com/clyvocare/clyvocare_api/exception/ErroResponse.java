package br.com.clyvocare.clyvocare_api.exception;

import java.time.LocalDateTime;

public record ErroResponse(
        int status,
        String mensagem,
        LocalDateTime timestamp
) {}
