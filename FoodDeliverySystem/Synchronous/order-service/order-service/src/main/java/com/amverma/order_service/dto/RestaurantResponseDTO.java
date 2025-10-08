package com.amverma.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantResponseDTO {
    private Integer restaurantId;
    private String name;
    private String address;
    private Float rating;
    private List<Integer> menuItemsList;
}
