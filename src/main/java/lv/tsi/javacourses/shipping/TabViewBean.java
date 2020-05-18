package lv.tsi.javacourses.shipping;


import org.primefaces.event.TabChangeEvent;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named(value = "tabViewBean")
@SessionScoped
public class TabViewBean implements Serializable {

    private int activeIndex = 0;

    public void onTabChange(TabChangeEvent event) {
//        System.out.println("tab id = " + event.getTab().getId());
        switch (event.getTab().getId()) {
            case "tab0":
                setActiveIndex(0);
                break;
            case "tab1":
                setActiveIndex(1);
                break;
            case "tab2":
                setActiveIndex(2);
                break;
            default:
                setActiveIndex(0);
                break;
        }

    }

    public int getActiveIndex() {
        return this.activeIndex;
    }

    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
    }
}
