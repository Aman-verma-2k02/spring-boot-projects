package com.amverma.delivery_service.dao;

import com.amverma.delivery_service.entity.Delivery;
import com.amverma.delivery_service.enums.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryDao extends JpaRepository<Delivery, Integer> {
    @Query("Select delivery From Delivery delivery Where delivery.status = :status")
    public List<Delivery> findAllDeliveryByStatus(DeliveryStatus status);

    @Query("Select delivery From Delivery delivery Where delivery.orderId = :orderId")
    public List<Delivery> findDeliveryByOrderId(int orderId);
}
