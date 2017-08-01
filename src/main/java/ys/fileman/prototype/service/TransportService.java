package ys.fileman.prototype.service;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Service;
import ys.fileman.prototype.domen.Credentials;
import ys.fileman.prototype.domen.ModelFactory;
import ys.fileman.prototype.domen.Transport;

import java.io.IOException;

@Service
public class TransportService {

    private final ModelFactory modelFactory;

    public TransportService(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

    public Transport getFTPTransport(Credentials credentials) throws IOException {
        FTPClient ftpClient = modelFactory.getFTPClient();

        ftpClient.connect(credentials.getServer());
        checkError(ftpClient, String.format("cannot connect ftp server: %s", credentials.getServer()));

        ftpClient.login(credentials.getLogin(), credentials.getPassword());
        checkError(ftpClient, String.format("cannot login to ftp server: %s, login: %s",
                credentials.getServer(), credentials.getLogin()));

        return modelFactory.getFTPTransport(ftpClient);
    }

    private void checkError(FTPClient ftpClient, String errorMessage) throws IOException {
        if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
            ftpClient.disconnect();
            throw new RuntimeException(String.format("%s. reply code: %s, reply message: %s",
                    errorMessage, ftpClient.getReplyCode(), ftpClient.getReplyString().trim()));
        }
    }
}
