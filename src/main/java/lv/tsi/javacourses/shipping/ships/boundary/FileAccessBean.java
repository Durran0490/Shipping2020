package lv.tsi.javacourses.shipping.ships.boundary;

import lv.tsi.javacourses.shipping.ships.control.FileDAO;
import org.omnifaces.cdi.GraphicImageBean;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GraphicImageBean
public class FileAccessBean implements Serializable {

    @Inject
    private FileDAO fileDAO;

    public byte[] loadFile(long fileId) {
        var file = fileDAO.selectFileById(fileId);
        if (file == null) {
            return null;
        }
        return file.getData();
    }

    public byte[] loadDefaultShipImage() throws IOException {
        return Utils.toByteArray(Faces.getResourceAsStream("/resources/img/ship.png"));
    }

}
