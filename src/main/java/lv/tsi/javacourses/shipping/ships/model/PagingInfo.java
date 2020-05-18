package lv.tsi.javacourses.shipping.ships.model;

public class PagingInfo {
    private int pageCount;
    private int currentPage = 1;

    public int previousPage(){
        return Math.max(getCurrentPage() - 1, 1);
    }

    public int nextPage(){
        return Math.min(getCurrentPage() + 1, getPageCount());
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
