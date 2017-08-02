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

    CredentialsCacheRow getCredentialsCacheRow(Credentials credentials) {
        return new CredentialsCacheRow(credentials);
    }

    public Credentials getCredentials(String server, String login, String password, FmUserId fmUserId) {
        return new Credentials(server, login, password, fmUserId);
    }

    public FTPClient getFTPClient() {
        return new FTPClient();
    }

    public FTPTransport getFTPTransport(FTPClient ftpClient) {
        return new FTPTransport(ftpClient);
    }
}
