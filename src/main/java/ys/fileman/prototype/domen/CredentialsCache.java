package ys.fileman.prototype.domen;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class CredentialsCache {

    private final ConcurrentMap<Token, CredentialsCacheRow> credentialsCache = new ConcurrentHashMap<>();
    private final ModelFactory modelFactory;

    public CredentialsCache(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

    public void addCredentials(Token token, FmUserId fmUserId, Credentials credentials) {
        credentialsCache.put(token, modelFactory.getCredentialsCacheRow(fmUserId, credentials));
    }

    public CredentialsCacheRow getCredentials(Token token) {
        return credentialsCache.get(token);
    }

    public void deleteCredentials(Token token) {
        credentialsCache.remove(token);
    }
}
