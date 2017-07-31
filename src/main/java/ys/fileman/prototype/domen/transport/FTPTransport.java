package ys.fileman.prototype.domen.transport;

import org.apache.commons.net.ftp.FTPClient;
import ys.fileman.prototype.domen.File;
import ys.fileman.prototype.domen.Path;
import ys.fileman.prototype.domen.Transport;

import java.util.List;

public class FTPTransport implements Transport {

    private final FTPClient ftpClient;

    public FTPTransport(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }

    @Override
    public List<File> list(Path path) {
        return null;
    }

    @Override
    public void disconnect() {

    }
}
