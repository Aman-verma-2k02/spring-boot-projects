package com.amverma.restaurant_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemResponseDTO {
    public Integer menuItemId;
    private String name;
    private Double price;
    private Boolean available;
    public Integer restaurantId;
}
