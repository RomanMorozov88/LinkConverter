package ru.morozov.controls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.morozov.models.PageObj;
import ru.morozov.models.SiteObj;
import ru.morozov.models.modelsutil.PageCodeUtil;
import ru.morozov.models.modelsutil.PageConvertUtil;
import ru.morozov.services.generators.PagesKeyGenerator;
import ru.morozov.services.storages.PageStorage;
import ru.morozov.services.storages.SiteStorage;

@RestController
public class ConvertControl {

    @Autowired
    private PagesKeyGenerator pagesKeyGenerator;
    @Autowired
    private PageStorage pageStorage;
    @Autowired
    private SiteStorage siteStorage;

    @PostMapping(value = "/convert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageCodeUtil getConvertedResult(@RequestBody PageConvertUtil inputData) {
        PageCodeUtil code = new PageCodeUtil();
        String parentName = null;
        SiteObj parentSite = null;
        Object buffer = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (buffer instanceof UserDetails) {
            parentName = ((UserDetails) buffer).getUsername();
            parentSite = this.siteStorage.getByLogin(parentName);
        }
        PageObj pageObj = new PageObj();
        pageObj.setOriginalURL(inputData.getUrl());
        pageObj.setConvertedURL(this.pagesKeyGenerator.keyGenerate());
        pageObj.setParentSite(parentSite);
        this.pageStorage.savePage(pageObj);
        code.setCodeFromPage(pageObj);
        return code;
    }

}
