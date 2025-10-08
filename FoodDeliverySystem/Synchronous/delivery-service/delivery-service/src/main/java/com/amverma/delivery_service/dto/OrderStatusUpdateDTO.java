package com.amverma.delivery_service.dto;

import com.amverma.delivery_service.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusUpdateDTO {
    private OrderStatus status;
}
