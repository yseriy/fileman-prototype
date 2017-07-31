package ys.fileman.prototype.domen.transport;

import lombok.Data;
import org.apache.commons.net.ftp.FTPClient;
import ys.fileman.prototype.domen.Transport;
import ys.fileman.prototype.domen.TransportAuth;

import java.io.IOException;

public class FTPTransportAuth implements TransportAuth {

    private final FTPClient ftpClient;
    private final String brand;
    private final String contract;
    private final String account;
    private final String token;

    @Data
    private final static class FTPCredential {
        private final String server;
        private final String user;
        private final String pass;

        private FTPCredential(String server, String user, String pass) {
            this.server = server;
            this.user = user;
            this.pass = pass;
        }
    }

    public FTPTransportAuth(FTPClient ftpClient, String brand, String contract, String account, String token) {
        this.ftpClient = ftpClient;
        this.brand = brand;
        this.contract = contract;
        this.account = account;
        this.token = token;
    }

    @Override
    public Transport connect() {
        FTPCredential ftpCredential = getCredentials();

        try {
            ftpClient.connect(ftpCredential.getServer());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private FTPCredential getCredentials() {
        return null;
    }
}
