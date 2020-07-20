package ru.morozov.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.morozov.services.UserDetailsServiceImpl;
import ru.morozov.services.generators.LoginGenerator;
import ru.morozov.services.generators.PagesKeyGenerator;
import ru.morozov.services.generators.PasswordGenerator;
import ru.morozov.services.generators.SimpleCodeGenerator;
import ru.morozov.services.security.JWTUtil;
import ru.morozov.services.storages.PageStorage;
import ru.morozov.services.storages.PageStorageMap;
import ru.morozov.services.storages.SiteStorage;
import ru.morozov.services.storages.SiteStorageList;

@Configuration
@ComponentScan("ru.morozov")
public class AppConfig {

    @Autowired
    SimpleCodeGenerator simpleCodeGenerator;

    @Bean
    public SimpleCodeGenerator simpleCodeGenerator() {
        return new SimpleCodeGenerator();
    }

    @Bean
    public JWTUtil jwtUtil() {
        return new JWTUtil();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SiteStorage siteStorage() {
        return new SiteStorageList();
    }

    @Bean
    public PageStorage pageStorage() {
        return new PageStorageMap();
    }

    @Bean
    public LoginGenerator loginGenerator() {
        return this.simpleCodeGenerator;
    }

    @Bean
    public PasswordGenerator passwordGenerator() {
        return this.simpleCodeGenerator;
    }

    @Bean
    public PagesKeyGenerator pagesKeyGenerator() {
        return this.simpleCodeGenerator;
    }

}
