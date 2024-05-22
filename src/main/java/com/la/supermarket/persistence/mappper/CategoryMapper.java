package com.la.supermarket.persistence.mappper;

import com.la.supermarket.domain.Category;
import com.la.supermarket.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    //convertir una categoria en category
    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active")
    })
    Category toCategory(Categoria categoria);

    //convertir una category en categoria
    @InheritInverseConfiguration //con esta notación indicamos hacer lo contrario que mappings
    @Mapping(target = "productos", ignore = true) //ingnoro la propiedad productos en categoria
    Categoria toCategoria(Category category);
}
