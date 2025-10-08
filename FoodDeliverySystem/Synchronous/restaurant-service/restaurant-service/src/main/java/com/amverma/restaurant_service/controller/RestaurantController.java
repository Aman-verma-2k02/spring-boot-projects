package com.amverma.restaurant_service.controller;

import com.amverma.restaurant_service.dto.MenuItemRequestDTO;
import com.amverma.restaurant_service.dto.OrderRequestDTO;
import com.amverma.restaurant_service.dto.RestaurantRequestDTO;
import com.amverma.restaurant_service.service.RestaurantService;
import com.amverma.restaurant_service.util.ApiResponse;
import com.amverma.restaurant_service.util.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;

@RestController
@RequestMapping("restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<?> addNewRestaurant(@RequestBody RestaurantRequestDTO restaurantRequestDTO) {
        return ResponseEntity.ok(ApiResponse.success(restaurantService.addNewRestaurant(restaurantRequestDTO), CommonConstants.RESTAURANT_REGISTERED));
    }

    @PostMapping("/internal")
    public ResponseEntity<?> addNewRestaurants(@RequestBody List<RestaurantRequestDTO> restaurantRequestDTOList) {
        return ResponseEntity.ok(ApiResponse.success(restaurantService.addNewRestaurants(restaurantRequestDTOList), CommonConstants.RESTAURANT_REGISTERED));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRestaurantById(@PathVariable int id) {
        return ResponseEntity.ok(ApiResponse.success(restaurantService.getRestaurantById(id), CommonConstants.RESTAURANT_FETCHED));
    }

    @GetMapping
    public ResponseEntity<?> getAllRestaurants() {
        return ResponseEntity.ok(ApiResponse.success(restaurantService.getAllRestaurants(), CommonConstants.RESTAURANT_FETCHED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRestaurantById(@PathVariable int id, @RequestBody RestaurantRequestDTO restaurantRequestDTO) {
        return ResponseEntity.ok(ApiResponse.success(restaurantService.updateRestaurantById(id, restaurantRequestDTO), CommonConstants.RESTAURANT_UPDATED));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRestaurantById(@PathVariable int id) {
        return ResponseEntity.ok(ApiResponse.success(restaurantService.deleteRestaurantById(id), CommonConstants.RESTAURANT_DELETED));
    }

    @PostMapping("/{id}/menu-items")
    public ResponseEntity<?> addNewMenuItemToRestaurant(@PathVariable int id, @RequestBody List<MenuItemRequestDTO> menuItemRequestDTOList) {
        return ResponseEntity.ok(ApiResponse.success(restaurantService.addNewMenuItemToRestaurant(id, menuItemRequestDTOList), CommonConstants.MENU_ITEM_ADDED));
    }

    @GetMapping("/{id}/menu-items")
    public ResponseEntity<?> getAllMenuItemsByRestaurantId(@PathVariable int id) {
        return ResponseEntity.ok(ApiResponse.success(restaurantService.getAllMenuItemsByRestaurantId(id), CommonConstants.MENU_ITEM_FETCHED));
    }

    @GetMapping("/{id}/menu-items/{itemId}")
    public ResponseEntity<?> getMenuItemById(@PathVariable int id, @PathVariable int itemId) {
        return ResponseEntity.ok(ApiResponse.success(restaurantService.getMenuItemById(id, itemId), CommonConstants.MENU_ITEM_FETCHED));
    }

    @PatchMapping("/{id}/menu-items/{itemId}")
    public ResponseEntity<?> updateMenuItemById(@PathVariable int id, @PathVariable int itemId, @RequestBody MenuItemRequestDTO menuItemRequestDTO) {
        return ResponseEntity.ok(ApiResponse.success(restaurantService.updateMenuItemById(id, itemId, menuItemRequestDTO), CommonConstants.MENU_ITEM_UPDATED));
    }

    @DeleteMapping("/{id}/menu-items/{itemId}")
    public ResponseEntity<?> deleteMenuItemById(@PathVariable int id, @PathVariable int itemId) {
        return ResponseEntity.ok(ApiResponse.success(restaurantService.deleteMenuItemById(id, itemId), CommonConstants.MENU_ITEM_DELETED));
    }

    @PatchMapping("/order/accept")
    public ResponseEntity<?> acceptOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        return ResponseEntity.ok(ApiResponse.success(restaurantService.acceptOrder(orderRequestDTO), MessageFormat.format(CommonConstants.ORDER_ACCEPTED, orderRequestDTO.getOrderId(), orderRequestDTO.getRestaurantId())));
    }

    @PatchMapping("/order/prepare")
    public ResponseEntity<?> prepareOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        return ResponseEntity.ok(ApiResponse.success(restaurantService.prepareOrder(orderRequestDTO), MessageFormat.format(CommonConstants.ORDER_PREPARED, orderRequestDTO.getOrderId(), orderRequestDTO.getRestaurantId())));
    }

}
