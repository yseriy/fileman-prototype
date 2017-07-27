package ys.fileman.prototype.api;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ys.fileman.prototype.api.assembler.ResponseAssembler;
import ys.fileman.prototype.api.dto.ResponseDTO;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class FileControllerAdvice {

    private final ResponseAssembler responseAssembler;

    public FileControllerAdvice(ResponseAssembler responseAssembler) {
        this.responseAssembler = responseAssembler;
    }

    @ExceptionHandler(Exception.class)
    public ResponseDTO allException(HttpServletRequest httpServletRequest, Exception e) {
        ResponseDTO responseDTO = responseAssembler.getErrorResponse(httpServletRequest);
        responseDTO.addMessage("error", "0", e.getMessage());

        return responseDTO;
    }
}
