package ys.fileman.prototype.web;

import org.springframework.web.bind.annotation.*;
import ys.fileman.prototype.domen.File;
import ys.fileman.prototype.service.ListService;
import ys.fileman.prototype.web.dto.PathDTO;
import ys.fileman.prototype.web.dto.UserDTO;

import java.util.List;

@RestController
@RequestMapping("/brands/{brand}/contracts/{contract}/accounts/{account}/services/www/filesystem/")
public class FileController {

    private final ListService listService;

    public FileController(ListService listService) {
        this.listService = listService;
    }

    @PostMapping("/list")
    public List<File> list(@RequestBody PathDTO pathDTO, @PathVariable String brand, @PathVariable String contract,
                           @PathVariable String account, @RequestHeader(value = "FTP-AUTH-TOKEN") String token) {
        return listService.list(pathDTO, new UserDTO(brand, contract, account), token);
    }
}
