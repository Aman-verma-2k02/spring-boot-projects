package com.amverma.restaurant_service.dto;

import com.amverma.restaurant_service.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private Integer orderId;
    private Integer restaurantId;
    private OrderStatus status;
}
