package br.com.elotech.oxy.library.application.dtos.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LivroOnlineRequest(

        @NotBlank
        @Size(min = 12, max = 12)
        String id

) { }
