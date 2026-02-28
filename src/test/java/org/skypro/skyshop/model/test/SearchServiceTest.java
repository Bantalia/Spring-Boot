package org.skypro.skyshop.model.test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.searchable.Searchable;
import org.skypro.skyshop.model.searchable.SearchableResult;
import org.skypro.skyshop.model.service.SearchService;
import org.skypro.skyshop.model.service.StorageService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    private SearchService searchService;

    @BeforeEach
    void setUp() {
        searchService = new SearchService(storageService);
    }

    @Test
    void searchReturnsEmptyWhenStorageEmpty() {
        when(storageService.getAllSearchables()).thenReturn(Collections.emptyList());

        Collection<SearchableResult> results = searchService.search("Яблоко");

        assertTrue(results.isEmpty());
    }

    @Test
    void searchReturnsEmptyWhenNoMatchFound() {
        Searchable s = mock(Searchable.class);
        when(s.getSearchTerm()).thenReturn("Молоко");
        when(storageService.getAllSearchables()).thenReturn(List.of(s));

        Collection<SearchableResult> results = searchService.search("Хлеб");

        assertTrue(results.isEmpty());
    }

    @Test
    void searchReturnsMatchWhenFound() {
        Searchable s = mock(Searchable.class);
        when(s.getSearchTerm()).thenReturn("Яблоко");
        SearchableResult expectedResult = SearchableResult.fromSearchable(s);

        when(storageService.getAllSearchables()).thenReturn(List.of(s));

        Collection<SearchableResult> results = searchService.search("ял"); // часть "яблоко" в нижнем регистре

        assertEquals(1, results.size());
        assertTrue(results.contains(expectedResult));
    }

    @Test
    void searchReturnsEmptyWhenPatternIsNullOrBlank() {
        assertTrue(searchService.search(null).isEmpty());
        assertTrue(searchService.search("").isEmpty());
        assertTrue(searchService.search("   ").isEmpty());
    }

}