package com.la.supermarket.persistence;

import com.la.supermarket.domain.Product;
import com.la.supermarket.persistence.mappper.ProductMapper;
import com.la.supermarket.domain.repository.ProductRepository;
import com.la.supermarket.persistence.crud.ProductoCrudRepository;
import com.la.supermarket.persistence.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    //conversión de producto a product
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        //conversión de producto a product
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        //conversión de producto a product
        return Optional.of(mapper.toProducts(productos));
    }

    //productos escasos
    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        //como no tengo un mapper que convierta una lista de opcionales, hago un map a los productos apara convertir
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        //como este método devuelve un optional hago el retorno directo y hago la conversión
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        //como save espera un producto tengo que usar la conversión inversa
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public void delete(int idProducto) {
        productoCrudRepository.deleteById(idProducto);
    }
}
