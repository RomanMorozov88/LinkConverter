package ru.morozov.services.storages;

import ru.morozov.models.PageObj;
import ru.morozov.models.SiteObj;

import java.util.List;

public interface PageStorage {

    List<PageObj> getAllPages();

    List<PageObj> getAllPagesByParentSite(SiteObj parentSiteName);

    PageObj getByKey(String key);

    boolean savePage(PageObj pageObj);

    void deletePage(PageObj pageObj);

}