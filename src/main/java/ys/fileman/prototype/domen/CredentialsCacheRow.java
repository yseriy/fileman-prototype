package ys.fileman.prototype.domen;

import lombok.Data;

import java.time.Duration;
import java.time.Instant;

@Data
class CredentialsCacheRow {

    private final Credentials credentials;
    private final Instant createTimestamp;

    CredentialsCacheRow(Credentials credentials) {
        this.credentials = credentials;
        createTimestamp = Instant.now();
    }

    Boolean isExpired(Long cacheTimeout) {
        return Duration.between(createTimestamp, Instant.now()).getSeconds() >= cacheTimeout;
    }
}
