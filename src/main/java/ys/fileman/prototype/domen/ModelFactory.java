package ys.fileman.prototype.domen;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;
import ys.fileman.prototype.domen.transport.FTPTransport;

@Service
public class ModelFactory {

    public Token getToken(String value) {
        return new Token(value);
    }

    public FmUserId getFmUserId(String brand, String contract, String account) {
        return new FmUserId(brand, contract, account);
    }

    CredentialsCacheRow getCredentialsCacheRow(FmUserId fmUserId, Credentials credentials) {
        return new CredentialsCacheRow(fmUserId, credentials);
    }

    public Credentials getCredentials(String server, String login, String password) {
        return new Credentials(server, login, password);
    }

    public FTPClient getFTPClient() {
        return new FTPClient();
    }

    public FTPTransport getFTPTransport(FTPClient ftpClient) {
        return new FTPTransport(ftpClient);
    }
}
