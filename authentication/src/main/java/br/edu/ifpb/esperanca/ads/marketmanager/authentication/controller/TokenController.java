package br.edu.ifpb.esperanca.ads.marketmanager.authentication.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.edu.ifpb.esperanca.ads.marketmanager.authentication.dto.TokenDTO;

@RestController
@RequestMapping("/auth")
public class TokenController {
    
    @PostMapping("/token")
    public ResponseEntity<String> token(@RequestBody TokenDTO tokenDto) {

        HttpHeaders headers = new HttpHeaders();
        RestTemplate rt = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("client_id", tokenDto.clientId());
        data.add("username", tokenDto.username());
        data.add("password", tokenDto.password());
        data.add("grant_type", tokenDto.grantType());

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(data, headers);

        var result = rt.postForEntity("http://localhost:8080/realms/marketmanager/protocol/openid-connect/token",
                entity, String.class);

        return result;
    }
}
