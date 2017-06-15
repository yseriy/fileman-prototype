package ys.fileman.prototype.web.dto;

import lombok.Data;

@Data
public class UserDTO {

    private final String brand;
    private final String contract;
    private final String account;

    public UserDTO(String brand, String contract, String account) {
        this.brand = brand;
        this.contract = contract;
        this.account = account;
    }
}
