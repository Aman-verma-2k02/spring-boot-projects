package com.amverma.delivery_service.dto;

import com.amverma.delivery_service.enums.DeliveryPartnerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryPartnerResponseDTO {
    private Integer deliveryPartnerId;
    private String name;
    private String email;
    private String address;
    private DeliveryPartnerStatus status;
    private List<Integer> deliveryList;
}
