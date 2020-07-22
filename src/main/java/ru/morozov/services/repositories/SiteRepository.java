package ru.morozov.services.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.morozov.models.SiteObj;

@Repository
public interface SiteRepository extends CrudRepository<SiteObj, Long> {

    SiteObj findByLogin(String login);

    SiteObj findByName(String name);

    SiteObj findByLoginAndPassword(String login, String password);
}