package ru.morozov.services.storages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.morozov.models.PageObj;
import ru.morozov.models.SiteObj;
import ru.morozov.services.repositories.PageRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PageStorageRepo implements PageStorage {

    @Autowired
    private PageRepository pageRepository;

    @Override
    public List<PageObj> getAllPages() {
        List<PageObj> result = new ArrayList<>();
        pageRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public List<PageObj> getAllPagesByParentSite(SiteObj parentSite) {
        List<PageObj> result = new ArrayList<>();
        pageRepository.findByParentSite(parentSite).forEach(result::add);
        return result;
    }

    @Override
    public PageObj getByKey(String key) {
        return pageRepository.findByConvertedUrl(key);
    }

    @Override
    public boolean savePage(PageObj pageObj) {
        boolean result = false;
        if (pageRepository.findByOriginalUrl(pageObj.getOriginalUrl()) == null) {
            pageRepository.save(pageObj);
            result = true;
        }
        return result;
    }

    @Override
    public void deletePage(PageObj pageObj) {
        pageRepository.delete(pageObj);
    }

    @Override
    public void updateCount(PageObj pageObj) {
        pageObj.incrCount();
        pageRepository.save(pageObj);
    }
}
