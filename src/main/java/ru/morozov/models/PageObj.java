package ru.morozov.models;

import java.util.Objects;

/**
 * Класс, описывающий страницу.
 */
public class PageObj {

    private int id;
    private SiteObj parentSite;
    private String originalURL;
    private String convertedURL;
    private int count = 0;

    public PageObj() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SiteObj getParentSite() {
        return parentSite;
    }

    public void setParentSite(SiteObj parentSite) {
        this.parentSite = parentSite;
    }

    public String getOriginalURL() {
        return originalURL;
    }

    public void setOriginalURL(String originalURL) {
        this.originalURL = originalURL;
    }

    public String getConvertedURL() {
        return convertedURL;
    }

    public void setConvertedURL(String convertedURL) {
        this.convertedURL = convertedURL;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public synchronized void incrCount() {
        this.count++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageObj pageObj = (PageObj) o;
        return id == pageObj.id &&
                count == pageObj.count &&
                Objects.equals(parentSite, pageObj.parentSite) &&
                Objects.equals(originalURL, pageObj.originalURL) &&
                Objects.equals(convertedURL, pageObj.convertedURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parentSite, originalURL, convertedURL, count);
    }
}