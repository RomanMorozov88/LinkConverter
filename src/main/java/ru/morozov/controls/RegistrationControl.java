package ru.morozov.controls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.morozov.models.SiteObj;
import ru.morozov.models.modelsutil.SiteRegRequestUtil;
import ru.morozov.models.modelsutil.SiteRegResponseUtil;
import ru.morozov.services.generators.LoginGenerator;
import ru.morozov.services.generators.PasswordGenerator;
import ru.morozov.services.storages.SiteStorage;

@RestController
public class RegistrationControl {

    @Autowired
    private SiteStorage siteStorage;
    @Autowired
    private LoginGenerator loginGenerator;
    @Autowired
    private PasswordGenerator passwordGenerator;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    public SiteRegResponseUtil getRegResult(@RequestBody SiteRegRequestUtil inputData) {
        SiteObj siteObj = new SiteObj();
        siteObj.setName(inputData.getSite());
        siteObj.setLogin(loginGenerator.loginGenerate());
        siteObj.setPassword(passwordGenerator.passwordGenerate());
        boolean registration = siteStorage.saveSite(siteObj);
        SiteRegResponseUtil result = null;
        if (registration) {
            result = new SiteRegResponseUtil(siteObj, !registration);
        } else {
            result = new SiteRegResponseUtil(new SiteObj(), !registration);
        }
        return result;
    }

}
