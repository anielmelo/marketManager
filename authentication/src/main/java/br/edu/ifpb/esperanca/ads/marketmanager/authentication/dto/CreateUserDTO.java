package br.edu.ifpb.esperanca.ads.marketmanager.authentication.dto;

import java.util.List;

public record CreateUserDTO(String username, String password, List<String> roles) {
}
