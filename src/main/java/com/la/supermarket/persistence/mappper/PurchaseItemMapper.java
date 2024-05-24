package com.la.supermarket.persistence.mappper;

import com.la.supermarket.domain.PurchaseItem;
import com.la.supermarket.persistence.entity.ComprasProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseItemMapper {
    //convertir CompraProducto en PurchaseItem
    @Mappings({
            @Mapping(source = "id.idProducto", target = "productId"),//id.idProducto = ComprasProducto-ComprasProductoPK
            @Mapping(source = "cantidad", target = "quantity"),
            //@Mapping(source = "total", target = "total") - como se llaman igual lo puedo omitir
            @Mapping(source = "estado", target = "active")
    })
    PurchaseItem toPurchaseItem(ComprasProducto producto);

    //hacer lo contrario
    @InheritInverseConfiguration
    //ignorar propiedades que no voy a utilizar
    @Mappings({
            @Mapping(target = "compra", ignore = true),
            @Mapping(target = "producto", ignore = true),//como hago referencia a poducto tengo que importarlo en Mapper
            @Mapping(target = "id.idCompra", ignore = true)
    })
    ComprasProducto toComprasProducto(PurchaseItem item);
}
