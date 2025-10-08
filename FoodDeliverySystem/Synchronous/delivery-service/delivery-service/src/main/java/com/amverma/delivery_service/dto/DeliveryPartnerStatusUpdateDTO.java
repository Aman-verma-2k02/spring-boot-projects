package com.amverma.delivery_service.dto;

import com.amverma.delivery_service.enums.DeliveryPartnerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryPartnerStatusUpdateDTO {
    private DeliveryPartnerStatus status;
}
