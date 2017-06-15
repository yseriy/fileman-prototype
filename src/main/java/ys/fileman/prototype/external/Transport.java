package ys.fileman.prototype.external;

import ys.fileman.prototype.domen.File;
import ys.fileman.prototype.domen.Path;

import java.util.List;

public interface Transport {
    List<File> list(Path path);
    void disconnect();
}
