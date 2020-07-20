package ru.morozov.controls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.morozov.models.PageObj;
import ru.morozov.models.SiteObj;
import ru.morozov.models.modelsutil.PageCodeUtil;
import ru.morozov.models.modelsutil.PageConvertUtil;
import ru.morozov.services.generators.PagesKeyGenerator;
import ru.morozov.services.security.JWTUtil;
import ru.morozov.services.storages.PageStorage;
import ru.morozov.services.storages.SiteStorage;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@RestController
public class ConvertControl {

    @Autowired
    private PagesKeyGenerator pagesKeyGenerator;
    @Autowired
    JWTUtil jwtUtil;
    @Autowired
    private PageStorage pageStorage;
    @Autowired
    private SiteStorage siteStorage;

    @PostMapping(value = "/convert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageCodeUtil getConvertedResult(@RequestBody PageConvertUtil inputData, ServletRequest servletRequest) {
        PageCodeUtil code = new PageCodeUtil();
        SiteObj parentSite = null;
        String token = jwtUtil.getTokenFromRequest((HttpServletRequest) servletRequest);
        if (token != null) {
            String parentLogin = jwtUtil.getLoginFromToken(token);
            parentSite = this.siteStorage.getByLogin(parentLogin);

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
