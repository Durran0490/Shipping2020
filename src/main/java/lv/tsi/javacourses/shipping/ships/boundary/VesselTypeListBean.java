package lv.tsi.javacourses.shipping.ships.boundary;

import lv.tsi.javacourses.shipping.ships.control.VesselTypeDAO;
import lv.tsi.javacourses.shipping.ships.model.PagingInfo;
import lv.tsi.javacourses.shipping.ships.model.VesselTypeEntity;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class VesselTypeListBean implements Serializable {
    private List<VesselTypeEntity> types;
    @Inject
    private VesselTypeDAO vesselTypeDAO;

    private PagingInfo pagingInfo = new PagingInfo();
    private static final int PAGE_SIZE = 14;

    public void init(){
        var from = PAGE_SIZE * Math.max(pagingInfo.getCurrentPage() - 1, 0);
        types = vesselTypeDAO.selectTypePage(from, PAGE_SIZE);
        var pageCount = (int)Math.ceil((double)vesselTypeDAO.selectTypeCount() / PAGE_SIZE);
        pagingInfo.setPageCount(pageCount);
    }

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    public List<VesselTypeEntity> getTypes() {
        return types;
    }
}
