package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SaleRepository extends CrudRepository<Sale, Long> {
    Iterable<Sale> findAllByIsPurchaseTrue();

}
