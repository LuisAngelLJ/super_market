package com.la.supermarket.persistence.mappper;

import com.la.supermarket.domain.Purchase;
import com.la.supermarket.persistence.entity.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class})
//uso uses = {PurchaseItemMapper.class} porque internamente se va a mapear dentro de la compra todos sus productos
public interface PurchaseMapper {
    //convertir Compra en Purchase
    @Mappings({
            @Mapping(source = "idCompra", target = "purchaseId"),
            @Mapping(source = "idCliente", target = "clientId"),
            @Mapping(source = "fecha", target = "date"),
            @Mapping(source = "medioPago", target = "paymentMethod"),
            @Mapping(source = "comentario", target = "comment"),
            @Mapping(source = "estado", target = "state"),
            @Mapping(source = "productos", target = "items")//por esto uso PurchaseItemMapper
    })
    Purchase toPurchase(Compra compra);

    //no es necesario mapear ya que utiliza el mapeo anterior
    List<Purchase> toPurchases(List<Compra> compras);

    //conversion inversa
    @InheritInverseConfiguration
    //gnorar atributo cliente porque no lo voy a utilizar
    @Mapping(target = "cliente", ignore = true)
    Compra toCompra(Purchase purchase) ;
}