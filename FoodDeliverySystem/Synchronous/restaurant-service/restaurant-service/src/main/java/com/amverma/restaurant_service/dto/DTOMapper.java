package com.amverma.restaurant_service.dto;

import com.amverma.restaurant_service.entity.MenuItem;
import com.amverma.restaurant_service.entity.Restaurant;
import org.mapstruct.*;

import java.awt.*;
import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DTOMapper {
    Restaurant toEntity(RestaurantRequestDTO restaurantRequestDTO);
    MenuItem toEntity(MenuItemRequestDTO menuItemRequestDTO);

    @Mapping(source = "id", target = "restaurantId")
    RestaurantResponseDTO toDTO(Restaurant restaurant);

    @Mapping(source = "id", target = "menuItemId")
    @Mapping(source = "restaurant.id", target = "restaurantId")
    MenuItemResponseDTO toDTO(MenuItem menuItem);

    default List<Integer> mapMenuItemsToIds(List<MenuItem> menuItemList) {
        return menuItemList.stream().map(MenuItem::getId).toList();
    }

    void update(RestaurantRequestDTO source, @MappingTarget Restaurant target);
    void update(MenuItemRequestDTO source, @MappingTarget MenuItem menuItem);
}
