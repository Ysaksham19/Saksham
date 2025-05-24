package com.mockcompany.webapp.controller;

import com.mockcompany.webapp.api.SearchReportResponse;

import com.mockcompany.webapp.model.ProductItem;
import com.mockcompany.webapp.services.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Management decided it is super important that we have lots of products that match the following terms.
 * So much so, that they would like a daily report of the number of products for each term along with the total
 * product count.
 */
@RestController
public class ReportController {

    /**
     * The people that wrote this code didn't know about JPA Spring Repository interfaces!
     */
	private final SearchService searchService;

    @Autowired
    public ReportController(SearchService searchService) {
        this.searchService = searchService;
    }


    @GetMapping("/api/products/report")
    public SearchReportResponse runReport() {
        Map<String, Integer> hits = new HashMap<>();
        SearchReportResponse response = new SearchReportResponse();
        response.setSearchTermHits(hits);

        // Get all products using the SearchService
        Collection<ProductItem> allItems = searchService.getAllProducts();
        response.setProductCount(allItems.size());

        // SearchService handles "Cool", "Amazing", "Perfect"
        hits.put("Cool", searchService.search("Cool").size());
        hits.put("Amazing", searchService.search("Amazing").size());
        hits.put("Perfect", searchService.search("Perfect").size());

        // Custom "Kids" regex match
        int kidCount = 0;
        Pattern kidPattern = Pattern.compile(".*[kK][iI][dD][sS].*");
        for (ProductItem item : allItems) {
            if ((item.getName() != null && kidPattern.matcher(item.getName()).matches()) ||
                (item.getDescription() != null && kidPattern.matcher(item.getDescription()).matches())) {
                kidCount++;
            }
        }
        hits.put("Kids", kidCount);

        return response;
    }
}
