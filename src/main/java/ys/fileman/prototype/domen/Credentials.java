package ys.fileman.prototype.domen;

import lombok.Data;

@Data
public class Credentials {

    private final String server;
    private final String login;
    private final String password;
    private final FmUserId fmUserId;

    public Boolean hasSameFmUserId(FmUserId fmUserId) {
        return this.fmUserId.equals(fmUserId);
    }
}
