package ru.morozov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.morozov.models.SiteObj;
import ru.morozov.services.storages.SiteStorage;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SiteStorage siteStorage;

    public UserDetailsServiceImpl() {
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SiteObj siteObj = this.siteStorage.getByLogin(s);
        if (siteObj == null) {
            throw new UsernameNotFoundException("Site not found");
        }
        return new User(
                siteObj.getLogin(),
                siteObj.getPassword(),
                new HashSet<>(Arrays.asList(Roles.ROLE_USER))
        );
    }

}