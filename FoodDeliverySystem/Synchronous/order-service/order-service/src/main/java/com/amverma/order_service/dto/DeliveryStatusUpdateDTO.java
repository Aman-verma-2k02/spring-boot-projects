package com.amverma.order_service.dto;

import com.amverma.order_service.enums.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryStatusUpdateDTO {
    private DeliveryStatus status;
}