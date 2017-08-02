package ys.fileman.prototype.domen;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CredentialsCache.class, ModelFactory.class})
public class CredentialsCacheTest {

    @Autowired
    private CredentialsCache credentialsCache;
    private Map<Token, Credentials> testMap;

    @Before
    public void setup() {
        String tokenValue = "test_token_";

        String brand = "test_brand_";
        String contract = "test_contract_";
        String account = "test_account_";

        String server = "test_server_";
        String login = "test_login_";
        String password = "test_password_";

        testMap = new HashMap<>();

        for (Integer i = 0; i < 10; i++) {
            Token token = new Token(tokenValue + i);
            FmUserId fmUserId = new FmUserId(brand + i, contract + i, account + i);
            Credentials credentials = new Credentials(server + i, login + i, password + i, fmUserId);
            testMap.put(token, credentials);
        }
    }

    @Test
    public void addAndGetCredentials() throws Exception {
        Token randomToken = new Token("very_random_token");
        Set<Token> tokenSet = testMap.keySet();
        testMap.forEach((k, v) -> credentialsCache.addCredentials(k, v));

        tokenSet.forEach((k) -> assertThat(credentialsCache.getCredentials(k)).isEqualTo(testMap.get(k)));
        assertThat(credentialsCache.getCredentials(randomToken)).isNull();
    }

    @Test
    public void cleanCache() throws InterruptedException {
        Long timeout = 5000L;
        Set<Token> tokenSet = testMap.keySet();
        testMap.forEach((k, v) -> credentialsCache.addCredentials(k, v));

        credentialsCache.cleanCache();

        tokenSet.forEach((k) -> assertThat(credentialsCache.getCredentials(k)).isEqualTo(testMap.get(k)));

        Thread.sleep(timeout);
        credentialsCache.cleanCache();

        tokenSet.forEach((k) -> assertThat(credentialsCache.getCredentials(k)).isNull());
    }
}
