package ys.fileman.prototype.api;

import org.springframework.web.bind.annotation.*;
import ys.fileman.prototype.api.dto.PathDTO;
import ys.fileman.prototype.api.dto.ResponseDTO;
import ys.fileman.prototype.api.facade.FileServiceFacade;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/brands/{brand}/contracts/{contract}/accounts/{account}/services/www/filesystem/")
public class FileController {

    private final FileServiceFacade fileServiceFacade;

    public FileController(FileServiceFacade fileServiceFacade) {
        this.fileServiceFacade = fileServiceFacade;
    }

    @PostMapping("/list")
    public ResponseDTO list(HttpServletRequest httpServletRequest, @RequestBody PathDTO pathDTO,
                            @PathVariable String brand, @PathVariable String contract,
                            @PathVariable String account,
                            @RequestHeader(value = "FTP-AUTH-TOKEN") String token) {

        return fileServiceFacade.list(httpServletRequest, brand, contract, account, token, pathDTO);
    }
}
