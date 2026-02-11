package org.skypro.skyshop.model.searchable;


import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


public class SearchEngine {

    // Храним продукты и статьи в HashSet, чтобы избежать дубликатов
    private Set<Product> products = new HashSet<>();
    private Set<Article> articles = new HashSet<>();

        private final Comparator<Searchable> comparator = new SearchableComparator();

        // Добавление продукта
        public boolean addProduct(Product product) {
            return products.add(product);
        }

        // Добавление статьи
        public boolean addArticle(Article article) {
            return articles.add(article);
        }

        // Создаем результат, используя TreeSet с компаратором
        public Set<Product> search(String query) {
            String lowerQuery = query.toLowerCase();
            Set<Product> результаты = new TreeSet<>( (o1, o2) -> comparator.compare(o1, o2));
            for (Product p : products) {
                if (p.getName().toLowerCase().contains(lowerQuery)) {
                    результаты.add(p);
                }
            }
            return результаты;

    }
}


