package ru.morozov.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

/**
 * Класс, описывающий сайт(пользователя)
 */
public class SiteObj {

    @JsonIgnore
    private String name;
    private String login;
    private String password;

    public SiteObj() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SiteObj siteObj = (SiteObj) o;
        return Objects.equals(name, siteObj.name) &&
                Objects.equals(login, siteObj.login) &&
                Objects.equals(password, siteObj.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, login, password);
    }
}