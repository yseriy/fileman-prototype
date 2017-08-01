package ys.fileman.prototype.api.facade;

import org.springframework.stereotype.Service;
import ys.fileman.prototype.api.assembler.ResponseAssembler;
import ys.fileman.prototype.api.dto.PathDTO;
import ys.fileman.prototype.api.dto.ResponseDTO;
import ys.fileman.prototype.domen.*;
import ys.fileman.prototype.service.AuthenticationService;
import ys.fileman.prototype.service.TransportService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Service
public class FileServiceFacade {

    private final AuthenticationService authenticationService;
    private final TransportService transportService;
    private final ResponseAssembler responseAssembler;

    public FileServiceFacade(AuthenticationService authenticationService, TransportService transportService, ResponseAssembler responseAssembler) {
        this.authenticationService = authenticationService;
        this.transportService = transportService;
        this.responseAssembler = responseAssembler;
    }

    public ResponseDTO list(HttpServletRequest httpServletRequest, String brand, String contract, String account,
                            String token, PathDTO pathDTO) throws IOException {
        Credentials credentials = authenticationService.getCredentials(new FmUserId(brand, contract, account), new Token(token));
        Transport transport = transportService.getFTPTransport(credentials);
        List<File> fileList = transport.list(new Path(pathDTO.getPath()));
        ResponseDTO<List<File>> responseDTO = responseAssembler.getSuccessResponse(httpServletRequest);
        responseDTO.setData(fileList);

        return responseDTO;
    }
}
