package ru.morozov.services.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.morozov.models.PageObj;
import ru.morozov.models.SiteObj;

import java.util.List;

@Repository
public interface PageRepository extends CrudRepository<PageObj, Long> {

    PageObj findByConvertedUrl(String convertedUrl);

    List<PageObj> findByParentSite(SiteObj parentSite);

    PageObj findByOriginalUrl(String originalUrl);
}