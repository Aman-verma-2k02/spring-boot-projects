package com.amverma.delivery_service.dto;

import com.amverma.delivery_service.enums.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryResponseDTO {
    private Integer deliveryId;
    private Integer orderId;
    private String source;
    private String destination;
    private DeliveryStatus status;
    private LocalDateTime createdAt;
    private Integer deliveryPartnerId;
}
