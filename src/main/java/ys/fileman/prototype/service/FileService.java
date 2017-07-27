package ys.fileman.prototype.service;

import org.springframework.stereotype.Service;
import ys.fileman.prototype.domen.File;
import ys.fileman.prototype.domen.Path;
import ys.fileman.prototype.domen.Transport;

import java.util.List;

@Service
public class FileService {

    public List<File> list(Transport transport, Path path) {
        try {
            return transport.list(path);
        } finally {
            transport.disconnect();
        }
    }
}
