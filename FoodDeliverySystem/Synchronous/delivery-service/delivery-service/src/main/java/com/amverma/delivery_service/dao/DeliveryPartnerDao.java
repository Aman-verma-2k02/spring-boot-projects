package com.amverma.delivery_service.dao;

import com.amverma.delivery_service.entity.DeliveryPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryPartnerDao extends JpaRepository<DeliveryPartner, Integer> {
}
