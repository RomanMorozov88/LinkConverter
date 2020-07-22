package ru.morozov.controls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.morozov.models.PageObj;
import ru.morozov.services.storages.PageStorage;

import javax.servlet.http.HttpServletResponse;

@RestController
public class RedirectControl {

    @Autowired
    private PageStorage pageStorage;

    @GetMapping("/redirect/{key}")
    public void getRegResult(@PathVariable String key, HttpServletResponse response) {
        PageObj target = this.pageStorage.getByKey(key);
        pageStorage.updateCount(target);
        response.setHeader("REDIRECT", target.getOriginalUrl());
        response.setStatus(302);
    }

}
