package br.edu.ifpb.esperanca.ads.marketmanager.authentication.dto;

public record GetUserDTO(String clientId, String username, String password, String grantType) {
}
