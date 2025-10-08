package com.amverma.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {
    private Integer userId;
    private Integer restaurantId;
    private Double totalAmount;
    private List<OrderItemRequestDTO> orderItemList;
}
