package com.la.supermarket.domain.repository;

import com.la.supermarket.domain.Product;
import com.la.supermarket.persistence.entity.Producto;
import com.la.supermarket.persistence.mappper.CategoryMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {
    //convertir una producto en product
    @Mappings({
            @Mapping(source = "idProducto", target = "productId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "precioVenta", target = "price"),
            @Mapping(source = "catidadStock", target = "stock"),
            @Mapping(source = "estado", target = "active"),
            //como category tiene su propio mapper incluyo una propiedad uses en Mapper
            @Mapping(source = "categoria", target = "category")
    })
    Product toProduct(Producto producto);
    //como es el mismo tipo de conversión no se requiere repetir todo el MAppings
    List<Product> toProducts(List<Producto> productos);

    //convertir una product en producto
    @InheritInverseConfiguration //con esta notación indicamos hacer lo contrario que mappings
    @Mapping(target = "codigoBarras", ignore = true) // como no ocupo codigoBarras lo ignoro
    Producto toProducto(Product product);
}
