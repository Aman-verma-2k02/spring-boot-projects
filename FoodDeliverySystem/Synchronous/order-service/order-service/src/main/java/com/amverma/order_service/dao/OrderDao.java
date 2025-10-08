package com.amverma.order_service.dao;

import com.amverma.order_service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer> {
    @Query("Select o FROM Order o WHERE o.userId = :userId")
    public List<Order> findByUserId(int userId);
}
