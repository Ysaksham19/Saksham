package com.mockcompany.webapp.controller;

import com.mockcompany.webapp.api.SearchReportResponse;
import com.mockcompany.webapp.data.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    private final ProductItemRepository productItemRepository;

    @Autowired
    public ReportController(ProductItemRepository productItemRepository) {
        this.productItemRepository = productItemRepository;
    }

    @GetMapping
    public SearchReportResponse generateReport() {
        long totalItems = productItemRepository.count();

        Map<String, Integer> searchTermHits = new HashMap<>();
        searchTermHits.put("Cool", (int) productItemRepository.countByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase("Cool", "Cool"));
        searchTermHits.put("Amazing", (int) productItemRepository.countByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase("Amazing", "Amazing"));
        searchTermHits.put("Perfect", (int) productItemRepository.countByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase("Perfect", "Perfect"));
        searchTermHits.put("Kids", (int) productItemRepository.countByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase("Kids", "Kids"));

        SearchReportResponse response = new SearchReportResponse();
        response.setQuery("N/A");
        response.setProductCount(totalItems);
        response.setTimestamp(LocalDateTime.now().toString());
        response.setSearchTermHits(searchTermHits); // ✅ This was missing

        return response;
    }
}
