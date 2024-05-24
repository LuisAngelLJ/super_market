package com.la.supermarket.persistence;

import com.la.supermarket.domain.Purchase;
import com.la.supermarket.domain.repository.PurchaseRepository;
import com.la.supermarket.persistence.crud.CompraCrudRespository;
import com.la.supermarket.persistence.entity.Compra;
import com.la.supermarket.persistence.mappper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRespository compraCrudRespository;
    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRespository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        //para este creo un query method en la interface
        return compraCrudRespository.findByIdCliente(clientId).map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        //guardar en cascada
        compra.getProductos().forEach(producto -> producto.setCompra(compra));//indicar a cada producto a que compra pertenecen
        return mapper.toPurchase(compraCrudRespository.save(compra));
    }
}
