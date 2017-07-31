package ys.fileman.prototype.api.facade;

import org.springframework.stereotype.Service;
import ys.fileman.prototype.api.assembler.ResponseAssembler;
import ys.fileman.prototype.api.dto.PathDTO;
import ys.fileman.prototype.api.dto.ResponseDTO;
import ys.fileman.prototype.domen.File;
import ys.fileman.prototype.domen.Path;
import ys.fileman.prototype.domen.Transport;
import ys.fileman.prototype.service.FileService;
import ys.fileman.prototype.service.TransportService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class FileServiceFacade {

    private final FileService fileService;
    private final TransportService transportService;
    private final ResponseAssembler responseAssembler;

    public FileServiceFacade(FileService fileService, TransportService transportService,
                             ResponseAssembler responseAssembler) {
        this.fileService = fileService;
        this.transportService = transportService;
        this.responseAssembler = responseAssembler;
    }

    public ResponseDTO list(HttpServletRequest httpServletRequest, String brand, String contract, String account,
                            String token, PathDTO pathDTO) {
        Transport transport = transportService.getFTPTransport(brand, contract, account, token);
        List<File> fileList = fileService.list(transport, new Path(pathDTO.getPath()));
        ResponseDTO<List<File>> responseDTO = responseAssembler.getSuccessResponse(httpServletRequest);
        responseDTO.setData(fileList);

        return responseDTO;
    }
}
