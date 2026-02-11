package org.skypro.skyshop.model.searchable;

import javax.naming.directory.SearchResult;

public final class SearchableResult {
    private final String id;
    private final String name;
    private final String contentType;

    public SearchableResult (String id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return contentType;
    }

    public static SearchableResult fromSearchable(Searchable searchable) {
        return new SearchableResult (
                searchable.getId().toString(),
                searchable.getSearchTerm(),
                searchable.getType()
        );
    }
}
