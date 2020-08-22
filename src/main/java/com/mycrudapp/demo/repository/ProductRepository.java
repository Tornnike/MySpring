package com.mycrudapp.demo.repository;

import com.mycrudapp.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product, Long > {
}
