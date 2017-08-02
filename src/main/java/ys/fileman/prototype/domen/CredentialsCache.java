package ys.fileman.prototype.domen;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class CredentialsCache {

    private static final Long CACHE_TIMEOUT = 3L;

    private final ConcurrentMap<Token, CredentialsCacheRow> credentialsCache = new ConcurrentHashMap<>();
    private final ModelFactory modelFactory;

    public CredentialsCache(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

    void addCredentials(Token token, Credentials credentials) {
        credentialsCache.put(token, modelFactory.getCredentialsCacheRow(credentials));
    }

    Credentials getCredentials(Token token) {
        return credentialsCache.get(token) != null ? credentialsCache.get(token).getCredentials() : null;
    }

    void cleanCache() {
        credentialsCache.entrySet().removeIf(entry -> entry.getValue().isExpired(CACHE_TIMEOUT));
    }
}
