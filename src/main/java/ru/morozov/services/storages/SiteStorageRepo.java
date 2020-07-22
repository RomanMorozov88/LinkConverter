package ru.morozov.services.storages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.morozov.models.SiteObj;
import ru.morozov.services.repositories.SiteRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SiteStorageRepo implements SiteStorage {

    @Autowired
    private SiteRepository siteRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public SiteObj getByLogin(String login) {
        return siteRepository.findByLogin(login);
    }

    @Override
    public SiteObj getBySiteName(String siteName) {
        return siteRepository.findByName(siteName);
    }

    @Override
    public SiteObj getByLoginAndPassword(SiteObj siteObj) {
        return siteRepository.findByLoginAndPassword(siteObj.getLogin(), siteObj.getPassword());
    }

    @Override
    public List<SiteObj> getAllSites() {
        List<SiteObj> result = new ArrayList<>();
        siteRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public boolean saveSite(SiteObj siteObj) {
        SiteObj forSaving = new SiteObj();
        forSaving.setName(siteObj.getName());
        forSaving.setLogin(siteObj.getLogin());
        forSaving.setPassword(siteObj.getPassword());
        boolean result = false;
        if (siteRepository.findByName(forSaving.getName()) == null) {
            String bufferPassword = passwordEncoder.encode(forSaving.getPassword());
            forSaving.setPassword(bufferPassword);
            siteRepository.save(forSaving);
            result = true;
        }
        return result;
    }

    @Override
    public void deleteSite(SiteObj siteObj) {
        siteRepository.delete(siteObj);
    }
}
