package ys.fileman.prototype.domen.transport;

import org.apache.commons.net.ftp.FTPClient;
import ys.fileman.prototype.domen.Transport;
import ys.fileman.prototype.domen.TransportFactory;

public class FTPTransportFactory implements TransportFactory {

    private final String brand;
    private final String contract;
    private final String account;
    private final String token;

    public FTPTransportFactory(String brand, String contract, String account, String token) {
        this.brand = brand;
        this.contract = contract;
        this.account = account;
        this.token = token;
    }

    @Override
    public Transport connect() {
        return new FTPTransport(new FTPClient());
    }
}
