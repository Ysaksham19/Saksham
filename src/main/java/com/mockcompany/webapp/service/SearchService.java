package com.mockcompany.webapp.services;


import com.mockcompany.webapp.data.ProductItemRepository;
import com.mockcompany.webapp.model.ProductItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private final ProductItemRepository productItemRepository;

    @Autowired
    public SearchService(ProductItemRepository productItemRepository) {
        this.productItemRepository = productItemRepository;
    }

    public List<ProductItem> searchProducts(String query) {
        Iterable<ProductItem> allItems = productItemRepository.findAll();
        List<ProductItem> itemList = new ArrayList<>();

        if (query == null || query.trim().isEmpty()) {
            allItems.forEach(itemList::add);
            return itemList;
        }

        String lowerQuery = query.toLowerCase();

        for (ProductItem item : allItems) {
            boolean matchesSearch = item.getName().toLowerCase().contains(lowerQuery)
                    || item.getDescription().toLowerCase().contains(lowerQuery);

            if (matchesSearch) {
                itemList.add(item);
            }
        }
        return itemList;
    }
}

