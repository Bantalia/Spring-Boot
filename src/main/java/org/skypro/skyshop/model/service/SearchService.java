package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.searchable.Searchable;
import org.skypro.skyshop.model.searchable.SearchableResult;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SearchService {

    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<SearchableResult> search(String pattern) {
        if (pattern == null || pattern.isBlank()) {
            return List.of();
        }
        String lowerPattern = pattern.toLowerCase();
        return storageService.getAllSearchables().stream()
                .filter(s -> s.getSearchTerm().toLowerCase().contains(lowerPattern))
                .map(SearchableResult::fromSearchable)
                .collect(Collectors.toList());
    }
}

