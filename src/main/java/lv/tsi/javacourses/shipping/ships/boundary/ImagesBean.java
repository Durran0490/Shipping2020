package lv.tsi.javacourses.shipping.ships.boundary;

import org.omnifaces.cdi.GraphicImageBean;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.SessionScoped;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

@GraphicImageBean
public class ImagesBean implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(ImagesBean.class);

    public InputStream loadCompanyLogo() {
        return Faces.getResourceAsStream("/resources/img/logo400x120.png");
    }
    public InputStream loadDefaultShipImage() {
        return Faces.getResourceAsStream("/resources/img/ship.png");
    }


}
