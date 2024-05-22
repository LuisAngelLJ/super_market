package com.la.supermarket.domain.service;

import com.la.supermarket.domain.Product;
import com.la.supermarket.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired //en lugar de la interface llama a la implementación que es ProductoRepository
    private ProductRepository productRepository;


    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(int productId) {
        return productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId) {
        return productRepository.getByCategory(categoryId);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public boolean delete(int productId) {
        return getProduct(productId).map(product -> {//si existe el producto
            productRepository.delete(productId);
            return true;
        }).orElse(false);//si no existe el producto

        //otra forma
        /*if (getProduct(productId).isPresent()) {
            productRepository.delete(productId);
            return true;
        } else {
            return false;
        }*/
    }
}
