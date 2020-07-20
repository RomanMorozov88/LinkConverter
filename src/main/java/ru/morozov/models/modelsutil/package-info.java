/**
 * Т.к. Spring сам сериализует\десериализует в\из JSON
 * То, что бы не писать в контроллерах всевозможные конверторы\маппинги
 * в этом пакете будут лежать POJO для приёа запросов\отправки ответов с необохдимыми полями.
 * В именах этих POJO будет указано какой класс (SiteObj или PageObj (Site или Page соответственно))
 * является "родительским".
 */
package ru.morozov.models.modelsutil;