package ys.fileman.prototype.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import ys.fileman.prototype.domen.Credentials;
import ys.fileman.prototype.domen.FmUserId;
import ys.fileman.prototype.domen.ModelFactory;
import ys.fileman.prototype.domen.Token;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(components = {AuthenticationService.class, ModelFactory.class})
public class AuthenticationServiceTest {

    @Autowired
    private MockRestServiceServer mockRestServiceServer;
    @Autowired
    private AuthenticationService authenticationService;

    @Test
    public void getCredentials() throws Exception {
        String host = "test_host";
        String brand = "test_brand";
        String contract = "test_contract";
        String account = "test_account";

        String tokenHeaderName = "FTP-AUTH-TOKEN";
        String tokenValue = "test_token";

        Token token = new Token(tokenValue);
        FmUserId fmUserId = new FmUserId(brand, contract, account);

        String urlTemplate = "http://%s/brands/%s/contracts/%s/accounts/%s/services/ftp/credentials";
        String url = String.format(urlTemplate, host, brand, contract, account);

        String fmResponseFilePath = "src/test/resources/fm_response.json";
        FileSystemResource responseExpectedFile = new FileSystemResource(fmResponseFilePath);

        mockRestServiceServer
                .expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(tokenHeaderName, tokenValue))
                .andRespond(withSuccess(responseExpectedFile, MediaType.APPLICATION_JSON));

        Credentials credentials = authenticationService.getCredentials(fmUserId, token);

        mockRestServiceServer.verify();
        assertNotNull(credentials);
        assertEquals(credentials.getServer(), "test_server");
        assertEquals(credentials.getLogin(), "test_user");
        assertEquals(credentials.getPassword(), "test_pass");
    }
}
