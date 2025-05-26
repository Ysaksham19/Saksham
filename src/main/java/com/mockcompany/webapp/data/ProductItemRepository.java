package com.mockcompany.webapp.data;

import com.mockcompany.webapp.model.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {

    List<ProductItem> findByNameContainingIgnoreCase(String query);

    List<ProductItem> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String query1, String query2);

    // ✅ Required for ReportController to build searchTermHits map
    long countByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
}
