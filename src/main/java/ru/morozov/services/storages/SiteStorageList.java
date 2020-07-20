package ru.morozov.services.storages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.morozov.models.SiteObj;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SiteStorageList implements SiteStorage {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final List<SiteObj> sites = new CopyOnWriteArrayList<>();

    public SiteStorageList() {
    }

    @Override
    public SiteObj getByLogin(String login) {
        SiteObj result = null;
        for (SiteObj s : this.sites) {
            if (s.getLogin().equals(login)) {
                result = s;
                break;
            }
        }
        return result;
    }

    @Override
    public SiteObj getBySiteName(String siteName) {
        SiteObj result = null;
        for (SiteObj s : this.sites) {
            if (s.getName().equals(siteName)) {
                result = s;
                break;
            }
        }
        return result;
    }

    @Override
    public SiteObj getByLoginAndPassword(SiteObj siteObj) {
        SiteObj result = null;
        SiteObj target = this.getByLogin(siteObj.getLogin());
        if (target != null) {
            if (passwordEncoder.matches(siteObj.getPassword(), target.getPassword())) {
                result = target;
            }
        }
        return result;
    }

    @Override
    public List<SiteObj> getAllSites() {
        return new ArrayList<>(this.sites);
    }

    @Override
    public boolean saveSite(SiteObj siteObj) {
        SiteObj forSaving = new SiteObj();
        forSaving.setName(siteObj.getName());
        forSaving.setLogin(siteObj.getLogin());
        forSaving.setPassword(siteObj.getPassword());
        boolean result = false;
        if (this.getBySiteName(forSaving.getName()) == null) {
            String bufferPassword = passwordEncoder.encode(forSaving.getPassword());
            forSaving.setPassword(bufferPassword);
            this.sites.add(forSaving);
            result = true;
        }
        return result;
    }

    @Override
    public void deleteSite(SiteObj siteObj) {
        this.sites.remove(siteObj);
    }
}
