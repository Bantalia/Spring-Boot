package org.skypro.skyshop.model.searchable;

import java.util.UUID;

public interface Searchable {

    UUID getId();
    // Возвращает термин поиска
    String getSearchTerm();


    // Возвращает тип найденного контента
    String getType();

    // Возвращает имя Searchable-объекта
    String getName();

    //Преобразует Searchable-объект в строку

    @Override
    String toString();

    // Представление объекта (default-метод)
    default String getStringRepresentation() {
        return getName() + " — тип " + getType();
    }


}

