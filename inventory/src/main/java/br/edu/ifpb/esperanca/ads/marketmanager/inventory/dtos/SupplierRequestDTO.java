package br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SupplierRequestDTO(
        @NotBlank(message = "Name is required") String name,
        @NotBlank(message = "CNPJ is required")
        @Size(min = 14, max = 14, message = "CNPJ must have 14 characters") String cnpj,
        @NotBlank(message = "Address is required") String address,
        @NotBlank(message = "Contact is required") String contact
) {}
