package com.amverma.restaurant_service.dao;

import com.amverma.restaurant_service.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemDao extends JpaRepository<MenuItem, Integer> {
    @Query("Select m From MenuItem m Where m.restaurant.id = :id")
    public List<MenuItem> findByRestaurantId(int id);
}
