package ys.fileman.prototype.api.assembler;

import org.springframework.stereotype.Component;
import ys.fileman.prototype.api.dto.ResponseDTO;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class ResponseAssembler {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public <E> ResponseDTO<E> getSuccessResponse(HttpServletRequest httpServletRequest) {
        return new ResponseDTO<>("success", UUID.randomUUID().toString(),
                httpServletRequest.getMethod(), httpServletRequest.getRequestURI(), DATE_FORMAT.format(new Date()));
    }

    public ResponseDTO getErrorResponse(HttpServletRequest httpServletRequest) {
        return new ResponseDTO("fail", UUID.randomUUID().toString(), httpServletRequest.getMethod(),
                httpServletRequest.getRequestURI(), DATE_FORMAT.format(new Date()));
    }
}
