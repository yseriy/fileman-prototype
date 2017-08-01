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

        if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
            ftpClient.disconnect();
            throw new RuntimeException(String.format("cannot connect ftp server: %s. reply code: %s, reply message: %s",
                    credentials.getServer(), ftpClient.getReplyCode(), ftpClient.getReplyString().trim()));
        }

        if (ftpClient.login(credentials.getLogin(), credentials.getPassword())) {
            ftpClient.disconnect();
            throw new RuntimeException(String.format("cannot login to ftp server: %s. reply code: %s, reply message: %s",
                    credentials.getServer(), ftpClient.getReplyCode(), ftpClient.getReplyString().trim()));
        }

        return modelFactory.getFTPTransport(ftpClient);
    }
}
