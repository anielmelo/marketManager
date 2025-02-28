package br.edu.ifpb.esperanca.ads.marketmanager.sale.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class CustomJwtAuthenticationToken extends JwtAuthenticationToken {
    private final String userId;

    public CustomJwtAuthenticationToken(Jwt jwt, Collection<? extends GrantedAuthority> authorities, String userId) {
        super(jwt, authorities);
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
