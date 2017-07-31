package ys.fileman.prototype.domen;

import lombok.Data;

@Data
public class File {
    private String type;
    private Long size;
    private String owner;
    private String attrs;
    private String name;
    private String mdate;
    private String resolvedName;
    private String resolvedType;
    private Boolean isBroken;
}
