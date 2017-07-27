package ys.fileman.prototype.domen;

import lombok.Data;

@Data
public class Path {

    private final String name;

    public Path(String name) {
        this.name = name;
    }
}
