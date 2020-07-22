package ru.morozov.models;

import javax.persistence.*;
import java.util.Objects;

/**
 * Класс, описывающий страницу.
 */
@Entity
@Table(name = "pages")
public class PageObj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "parent_site")
    private SiteObj parentSite;
    private String originalUrl;
    private String convertedUrl;
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

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getConvertedUrl() {
        return convertedUrl;
    }

    public void setConvertedUrl(String convertedUrl) {
        this.convertedUrl = convertedUrl;
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
                Objects.equals(originalUrl, pageObj.originalUrl) &&
                Objects.equals(convertedUrl, pageObj.convertedUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parentSite, originalUrl, convertedUrl, count);
    }
}