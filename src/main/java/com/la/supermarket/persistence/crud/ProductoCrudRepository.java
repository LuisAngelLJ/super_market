package com.la.supermarket.persistence.crud;

import com.la.supermarket.persistence.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
    //recuperar toda la lista de productos de cierta categoria y odenarlo por nombre
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    //recuperar los productos escasos que su estado sea activo
    //los parametros recibidos que tienen que llamr igual al parametro de la entidad
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}
