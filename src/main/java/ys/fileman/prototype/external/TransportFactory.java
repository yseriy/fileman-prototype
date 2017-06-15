package ys.fileman.prototype.external;

import org.springframework.stereotype.Component;
import ys.fileman.prototype.web.dto.UserDTO;

@Component
public interface TransportFactory {
    Transport connect(UserDTO userDTO, String token);
}
