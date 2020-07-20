package ru.morozov.models.modelsutil;

import ru.morozov.models.SiteObj;

/**
 * POJO для формирования ответа RegistrationControl`ом.
 */
public class SiteRegResponseUtil {
    private String login;
    private String password;
    private boolean registration;

    public SiteRegResponseUtil() {
    }

    public SiteRegResponseUtil(SiteObj siteObj, boolean registration) {
        this.login = siteObj.getLogin();
        this.password = siteObj.getPassword();
        this.registration = registration;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRegistration() {
        return registration;
    }

    public void setRegistration(boolean registration) {
        this.registration = registration;
    }
}