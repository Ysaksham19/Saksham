package com.mockcompany.webapp.api;

import java.util.Map;

public class SearchReportResponse {

    private String query;
    private long productCount; // ✅ Renamed to match the test
    private String timestamp;
    private Map<String, Integer> searchTermHits;

    public SearchReportResponse() {}

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public long getProductCount() {
        return productCount;
    }

    public void setProductCount(long productCount) {
        this.productCount = productCount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, Integer> getSearchTermHits() {
        return searchTermHits;
    }

    public void setSearchTermHits(Map<String, Integer> searchTermHits) {
        this.searchTermHits = searchTermHits;
    }
}
