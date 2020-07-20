package ru.morozov.models.modelsutil;

/**
 * POJO для получения данных из запроса в RegistrationControl.
 */
public class SiteRegRequestUtil {
    private String site;

    public SiteRegRequestUtil() {
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}