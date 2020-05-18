package lv.tsi.javacourses.shipping.ships.boundary;

import lv.tsi.javacourses.shipping.ships.control.FileDAO;
import org.omnifaces.cdi.GraphicImageBean;

import javax.inject.Inject;
import java.io.InputStream;
import java.io.Serializable;

import org.omnifaces.util.Faces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GraphicImageBean
public class FileAccessBean implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(FileAccessBean.class);

    @Inject
    private FileDAO fileDAO;

    public byte[] loadFile(long fileId) {
        log.info("loadFile method called START {}", fileId);
        var file = fileDAO.selectFileById(fileId);
        if (file == null) {
            log.info("loadFile method called null {}", fileId);
            return null;
        }
        log.info("loadFile method called file NAME {}", file.getFileName());
        return file.getData();
    }

    public InputStream loadDefaultFile() {
        log.info("loadDefaultFile method called {}",5);
        return Faces.getResourceAsStream("/resources/img/ship.png");
    }

}
