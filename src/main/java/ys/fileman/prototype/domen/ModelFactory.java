package ys.fileman.prototype.domen;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;
import ys.fileman.prototype.domen.transport.FTPTransport;

@Service
public class ModelFactory {

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
