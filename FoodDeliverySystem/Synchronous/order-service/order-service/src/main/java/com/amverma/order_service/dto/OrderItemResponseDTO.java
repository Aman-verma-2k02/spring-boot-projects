package com.amverma.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponseDTO {
    private Integer orderItemId;
    private Integer menuItemId;
    private Integer quantity;
    private Double price;
}
