package ys.fileman.prototype.domen;

import java.util.List;

public interface Transport {

    List<File> list(Path path);

    void disconnect();
}
