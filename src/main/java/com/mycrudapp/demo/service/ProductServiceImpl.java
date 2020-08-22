package com.mycrudapp.demo.service;

import com.mycrudapp.demo.exception.ResourceNotFoundException;
import com.mycrudapp.demo.model.Product;
import com.mycrudapp.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl  {
    @Autowired
    private ProductRepository productRepository;


    public Product createProduct(Product product) {
        return productRepository.save(product);

    }

    public Product updateProduct(Product product) {
        Optional<Product> productDb = this.productRepository.findById(product.getId());

        if (productDb.isPresent()) {
            Product productUpdate = productDb.get();
            productUpdate.setId(product.getId());
            productUpdate.setName(product.getName());
            productUpdate.setDescription(product.getDescription());
            productRepository.save(productUpdate);
            return productUpdate;

        }

        throw new ResourceNotFoundException("product not found with id " + product.getId());


    }

    public List<Product> getAllProducts() {

    try {


        return productRepository.findAll();
    }

    catch (Exception e){

        throw new ResourceNotFoundException("product list could not be found!");
    }
    }

    public Product getProductById(long productId) {

        Optional<Product> productDb = this.productRepository.findById(productId);

        if (productDb.isPresent()) {

            return productDb.get();

    }

        else {
            throw new ResourceNotFoundException("product not found with id " + productId);
        }
    }

    public void deleteProduct(long productId) {

        Optional<Product> productDb = this.productRepository.findById(productId);

        if (productDb.isPresent()) {

            this.productRepository.delete(productDb.get());
        }
        else {
            throw new ResourceNotFoundException("product not found with id " + productId);
        }

    }
}
