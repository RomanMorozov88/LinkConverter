package ru.morozov.models.modelsutil;

import ru.morozov.models.PageObj;

/**
 * POJO для формирования ответа ConvertControl`ом.
 */
public class PageCodeUtil {

    private String code;

    public void setCodeFromPage(PageObj pageObj) {
        this.code = pageObj.getConvertedUrl();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
