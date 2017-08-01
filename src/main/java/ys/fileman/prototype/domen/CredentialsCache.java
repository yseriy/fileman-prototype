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

    public void addCredentials(Token token, Credentials credentials, FmUserId fmUserId) {
        credentialsCache.put(token, modelFactory.getCredentialsCacheRow(fmUserId, credentials));
    }

    public CredentialsCacheRow getCredentialsCacheRow(Token token) {
        return credentialsCache.get(token);
    }

    public Boolean isRowValid(CredentialsCacheRow cacheRow, FmUserId fmUserId) {
        return cacheRow.getFmUserId().canEqual(fmUserId);
    }

    public void deleteCredentials(Token token) {
        credentialsCache.remove(token);
    }
}
