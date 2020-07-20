package ru.morozov.models.modelsutil;

import ru.morozov.models.PageObj;

public class PageStatisticUtil {

    private String url;
    private int total;

    public PageStatisticUtil() {
    }

    public PageStatisticUtil(PageObj pageObj) {
        this.url = pageObj.getOriginalURL();
        this.total = pageObj.getCount();
    }

//    public void setDataFromPage(PageObj pageObj) {
//        this.url = pageObj.getOriginalURL();
//        this.total = pageObj.getCount();
//    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}