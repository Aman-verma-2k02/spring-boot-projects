package com.amverma.delivery_service.entity;

import com.amverma.delivery_service.enums.DeliveryPartnerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "delivery_partners")
public class DeliveryPartner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String address;
    @Enumerated(EnumType.STRING)
    private DeliveryPartnerStatus status;
    @OneToMany(mappedBy = "deliveryPartner")
    private List<Delivery> deliveryList = new ArrayList<>();
}
