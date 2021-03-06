package ru.morozov.services.storages;

import ru.morozov.models.SiteObj;

import java.util.List;

public interface SiteStorage {

    SiteObj getByLogin(String login);

    SiteObj getBySiteName(String siteName);

    SiteObj getByLoginAndPassword(SiteObj siteObj);

    List<SiteObj> getAllSites();

    boolean saveSite(SiteObj siteObj);

    void deleteSite(SiteObj siteObj);

}
