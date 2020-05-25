package lv.tsi.javacourses.shipping.ships.boundary;

import lv.tsi.javacourses.shipping.ships.control.FlagDAO;
import lv.tsi.javacourses.shipping.ships.control.ShipyardDAO;
import lv.tsi.javacourses.shipping.ships.model.FlagEntity;
import lv.tsi.javacourses.shipping.ships.model.PagingInfo;
import lv.tsi.javacourses.shipping.ships.model.ShipyardEntity;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class FlagListBean implements Serializable {
    private List<FlagEntity> flags;
    @Inject
    private FlagDAO flagDAO;

    private PagingInfo pagingInfo = new PagingInfo();
    private static final int PAGE_SIZE = 14;

    public void init(){
        var from = pagingInfo.from(PAGE_SIZE);
        flags = flagDAO.selectFlagPage(from, PAGE_SIZE);
        var pageCount = (int)Math.ceil((double)flagDAO.selectFlagCount() / PAGE_SIZE);
        pagingInfo.setPageCount(pageCount);
    }

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    public List<FlagEntity> getFlags() {
        return flags;
    }
}
