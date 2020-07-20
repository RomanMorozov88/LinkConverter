package ru.morozov.controls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.morozov.models.PageObj;
import ru.morozov.models.SiteObj;
import ru.morozov.models.modelsutil.PageStatisticUtil;
import ru.morozov.services.security.JWTUtil;
import ru.morozov.services.storages.PageStorage;
import ru.morozov.services.storages.SiteStorage;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StatisticControl {

    @Autowired
    private SiteStorage siteStorage;
    @Autowired
    private PageStorage pageStorage;
    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping(value = "/statistic", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<PageStatisticUtil> getStatistic(ServletRequest servletRequest) {
        List<PageStatisticUtil> result = new ArrayList<>();
        List<PageObj> buffer = new ArrayList<>();
        String token = jwtUtil.getTokenFromRequest((HttpServletRequest) servletRequest);
        if (token != null) {
            String siteLogin = jwtUtil.getLoginFromToken(token);
            SiteObj parent = this.siteStorage.getByLogin(siteLogin);
            buffer = this.pageStorage.getAllPagesByParentSite(parent);
            for (PageObj p : buffer) {
                result.add(new PageStatisticUtil(p));
            }
        }
        return result;
    }

}