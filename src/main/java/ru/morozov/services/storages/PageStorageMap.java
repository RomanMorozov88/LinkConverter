package ru.morozov.services.storages;

import org.springframework.stereotype.Service;
import ru.morozov.models.PageObj;
import ru.morozov.models.SiteObj;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Простое хранилище страниц-
 * внутри используется Map, где в качестве ключа используется уникальный ключ,
 * сгенерированный данным приложением.
 */
@Service
public class PageStorageMap implements PageStorage {

    private final Map<String, PageObj> pages = new ConcurrentHashMap<>();

    public PageStorageMap() {
    }

    @Override
    public List<PageObj> getAllPages() {
        return new ArrayList<>(pages.values());
    }

    @Override
    public List<PageObj> getAllPagesByParentSite(SiteObj parentSiteName) {
        ArrayList<PageObj> result = new ArrayList<>();
        for (PageObj p : this.pages.values()) {
            if (p.getParentSite().equals(parentSiteName)) {
                result.add(p);
            }
        }
        return result;
    }

    @Override
    public PageObj getByKey(String key) {
        return pages.getOrDefault(key, null);
    }

    @Override
    public boolean savePage(PageObj pageObj) {
        boolean result = false;
        if (this.pages.get(pageObj.getConvertedUrl()) == null) {
            this.pages.put(pageObj.getConvertedUrl(), pageObj);
            result = true;
        }
        return result;
    }

    @Override
    public void deletePage(PageObj pageObj) {
        this.pages.remove(pageObj);
    }

    @Override
    public void updateCount(PageObj pageObj) {
        pageObj.incrCount();
    }

    ;
}
