package ys.fileman.prototype.service;

import org.springframework.stereotype.Service;
import ys.fileman.prototype.domen.File;
import ys.fileman.prototype.domen.Path;
import ys.fileman.prototype.external.Transport;
import ys.fileman.prototype.external.TransportFactory;
import ys.fileman.prototype.web.dto.PathDTO;
import ys.fileman.prototype.web.dto.UserDTO;

import java.util.List;

@Service
public class ListService {

    private final TransportFactory transportFactory;

    public ListService(TransportFactory transportFactory) {
        this.transportFactory = transportFactory;
    }

    public List<File> list(PathDTO pathDTO, UserDTO userDTO, String token) {
        Transport transport = transportFactory.connect(userDTO, token);
        List<File> fileList = transport.list(new Path(pathDTO.getPath()));
        transport.disconnect();

        return fileList;
    }
}
