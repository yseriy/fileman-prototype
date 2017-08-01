package ys.fileman.prototype.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ys.fileman.prototype.domen.Credentials;
import ys.fileman.prototype.domen.ModelFactory;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final ModelFactory modelFactory;

    public AuthenticationService(RestTemplateBuilder restTemplateBuilder,
                                 Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder, ModelFactory modelFactory) {
        restTemplate = restTemplateBuilder.build();
        objectMapper = jackson2ObjectMapperBuilder.build();
        this.modelFactory = modelFactory;
    }

    public Credentials getCredentials(String brand, String contract, String account, String token) throws IOException {
        String url = "http://{host}/brands/{brand}/contracts/{contract}/accounts/{account}/services/ftp/credentials";
        String tokenHeaderName = "FTP-AUTH-TOKEN";
        String host = "test_host";

        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("host", host);
        urlVariables.put("brand", brand);
        urlVariables.put("contract", URLEncoder.encode(contract, "UTF-8"));
        urlVariables.put("account", account);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(tokenHeaderName, token);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        return parseResponse(restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class, urlVariables));
    }

    private Credentials parseResponse(ResponseEntity<String> response) throws IOException {
        JsonNode root = objectMapper.readTree(response.getBody());
        JsonNode serverNode = root.path("response").path("data").path("ftp_farm_ip");

        if (serverNode.isMissingNode()) {
            throw new RuntimeException("cannot find farm ip");
        }

        JsonNode loginNode = root.path("response").path("data").path("ftp_user");

        if (loginNode.isMissingNode()) {
            throw new RuntimeException("cannot find user login");
        }

        JsonNode passwordNode = root.path("response").path("data").path("ftp_pass");

        if (passwordNode.isMissingNode()) {
            throw new RuntimeException("cannot find user password");
        }

        return modelFactory.getCredentials(serverNode.asText(), loginNode.asText(), passwordNode.asText());
    }
}
