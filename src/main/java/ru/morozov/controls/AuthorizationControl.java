package ru.morozov.controls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.morozov.models.SiteObj;
import ru.morozov.services.security.JWTUtil;
import ru.morozov.services.storages.SiteStorage;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthorizationControl {

    private static final String AUTHORIZATION = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private SiteStorage siteStorage;
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/login")
    public void getAuth(@RequestBody SiteObj siteObj, HttpServletResponse response) {
        SiteObj target = siteStorage.getByLogin(siteObj.getLogin());
        if (target != null) {
            String token = TOKEN_PREFIX + jwtUtil.generateToken(target);
            response.setHeader(AUTHORIZATION, token);
        }
    }

}