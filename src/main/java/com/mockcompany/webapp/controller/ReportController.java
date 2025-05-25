package com.mockcompany.webapp.controller;

import com.mockcompany.webapp.api.SearchReportResponse;
import com.mockcompany.webapp.data.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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

        SearchReportResponse response = new SearchReportResponse();
        response.setQuery("N/A");
        response.setResultCount(totalItems);
        response.setTimestamp(LocalDateTime.now().toString());

        return response;
    }
}
