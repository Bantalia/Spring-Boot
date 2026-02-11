package org.skypro.skyshop.model.article;

import org.skypro.skyshop.model.searchable.Searchable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    private final UUID id;
    private final String title;
    private final String text;

    public Article(UUID id, String title, String text) {
        this.title = title;
        this.text = text;
        this.id = id;
    }

    public UUID getId() {
        return this.id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
// Метод toString возвращает формат:
    // Название статьи
    // Текст статьи

    @Override
    public String toString() {
        return title + "\n" + text;
    }

    // Реализация интерфейса Searchable
    @Override
    public String getSearchTerm() {
        return toString(); // название + текст
    }

    @JsonIgnore
    public String getType() {
        return "ARTICLE"; // в классе Article
    }


    @Override
    public String getName() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(title, article.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}


