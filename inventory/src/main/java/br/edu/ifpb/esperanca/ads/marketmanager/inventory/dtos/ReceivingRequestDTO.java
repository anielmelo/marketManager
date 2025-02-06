package br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ReceivingRequestDTO (
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd['T'HH:mm:ss]") LocalDateTime date) { }
