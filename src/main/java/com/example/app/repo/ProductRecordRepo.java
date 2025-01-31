package com.example.app.repo;

import com.example.app.model.Product;
import com.example.app.model.ProductRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface ProductRecordRepo extends JpaRepository<ProductRecord,Integer> {
}
