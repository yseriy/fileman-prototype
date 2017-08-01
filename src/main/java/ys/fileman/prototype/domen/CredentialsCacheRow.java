package ys.fileman.prototype.domen;

import lombok.Data;

@Data
class CredentialsCacheRow {
    private final FmUserId fmUserId;
    private final Credentials credentials;
}
