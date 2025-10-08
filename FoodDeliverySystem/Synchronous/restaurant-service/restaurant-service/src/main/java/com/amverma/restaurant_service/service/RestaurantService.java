package com.amverma.restaurant_service.service;

import com.amverma.restaurant_service.enums.OrderStatus;
import com.amverma.restaurant_service.dao.MenuItemDao;
import com.amverma.restaurant_service.dao.RestaurantDao;
import com.amverma.restaurant_service.dto.*;
import com.amverma.restaurant_service.entity.MenuItem;
import com.amverma.restaurant_service.entity.Restaurant;
import com.amverma.restaurant_service.exception.MenuItemNotFoundException;
import com.amverma.restaurant_service.exception.RestaurantNotFoundException;
import com.amverma.restaurant_service.feign.OrderInterface;
import com.amverma.restaurant_service.util.CommonConstants;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantDao restaurantDao;
    private final DTOMapper dtoMapper;
    private final MenuItemDao menuItemDao;
    private final OrderInterface orderInterface;
    public RestaurantService(RestaurantDao restaurantDao, MenuItemDao menuItemDao, DTOMapper dtoMapper, OrderInterface orderInterface) {
        this.restaurantDao= restaurantDao;
        this.menuItemDao = menuItemDao;
        this.dtoMapper = dtoMapper;
        this.orderInterface = orderInterface;
    }

    public Restaurant findRestaurantById(int id) {
        return restaurantDao.findById(id).orElseThrow(() ->
                new RestaurantNotFoundException(MessageFormat.format(CommonConstants.RESTAURANT_NOT_FOUND, id)));
    }

    public MenuItem findMenuById(int id) {
        return menuItemDao.findById(id).orElseThrow(() ->
                new MenuItemNotFoundException(MessageFormat.format(CommonConstants.RESTAURANT_NOT_FOUND, id)));
    }

    public RestaurantResponseDTO addNewRestaurant(RestaurantRequestDTO restaurantRequestDTO) {
        Restaurant restaurant = dtoMapper.toEntity(restaurantRequestDTO);
        restaurantDao.save(restaurant);
        return dtoMapper.toDTO(restaurant);
    }

    public List<RestaurantResponseDTO> addNewRestaurants(List<RestaurantRequestDTO> restaurantRequestDTOList) {
        return restaurantRequestDTOList.stream().map(this::addNewRestaurant).toList();
    }

    public RestaurantResponseDTO getRestaurantById(int id) {
        Restaurant restaurant = this.findRestaurantById(id);
        return dtoMapper.toDTO(restaurant);
    }

    public List<RestaurantResponseDTO> getAllRestaurants() {
        return restaurantDao.findAll().stream().map(dtoMapper::toDTO).toList();
    }

    public RestaurantResponseDTO updateRestaurantById(int id, RestaurantRequestDTO restaurantRequestDTO) {
        Restaurant restaurant = this.findRestaurantById(id);
        dtoMapper.update(restaurantRequestDTO, restaurant);
        restaurantDao.save(restaurant);
        return dtoMapper.toDTO(restaurant);
    }

    public RestaurantResponseDTO deleteRestaurantById(int id) {
        Restaurant restaurant = this.findRestaurantById(id);
        restaurant.getMenuItemsList().size();
        restaurantDao.deleteById(id);
        return dtoMapper.toDTO(restaurant);
    }

    public List<MenuItemResponseDTO> addNewMenuItemToRestaurant(int id, List<MenuItemRequestDTO> menuItemRequestDTOList) {
        Restaurant restaurant = this.findRestaurantById(id);

        List<MenuItemResponseDTO> menuItemResponseDTOS = new ArrayList<>();
        for(MenuItemRequestDTO menuItemRequestDTO: menuItemRequestDTOList) {
            MenuItem menuItem = dtoMapper.toEntity(menuItemRequestDTO);
            menuItem.setRestaurant(restaurant);
            menuItemDao.save(menuItem);
            menuItemResponseDTOS.add(dtoMapper.toDTO(menuItem));
        }
        return menuItemResponseDTOS;
    }

    public List<MenuItemResponseDTO> getAllMenuItemsByRestaurantId(int id) {
        Restaurant restaurant = this.findRestaurantById(id);
        return menuItemDao.findByRestaurantId(id).stream().map(dtoMapper::toDTO).toList();
    }

    public MenuItemResponseDTO getMenuItemById(int id, int itemId) {
        Restaurant restaurant = this.findRestaurantById(id);
        MenuItem menuItem = this.findMenuById(itemId);
        return dtoMapper.toDTO(menuItem);
    }

    public MenuItemResponseDTO deleteMenuItemById(int id, int itemId) {
        Restaurant restaurant = this.findRestaurantById(id);
        MenuItem menuItem = this.findMenuById(itemId);
        menuItemDao.delete(menuItem);
        return dtoMapper.toDTO(menuItem);
    }

    public MenuItemResponseDTO updateMenuItemById(int id, int itemId, MenuItemRequestDTO menuItemRequestDTO) {
        Restaurant restaurant = this.findRestaurantById(id);
        MenuItem menuItem = this.findMenuById(itemId);

        dtoMapper.update(menuItemRequestDTO, menuItem);
        menuItemDao.save(menuItem);
        return dtoMapper.toDTO(menuItem);
    }

    public OrderResponseDTO acceptOrder(OrderRequestDTO orderRequestDTO) {
        OrderStatus updatedStatus = OrderStatus.ACCEPTED;
        int orderId = orderRequestDTO.getOrderId();
        int restaurantId = orderRequestDTO.getRestaurantId();
        orderInterface.updateOrderStatus(orderId, new OrderStatusUpdateDTO(updatedStatus));
        return new OrderResponseDTO(orderId, restaurantId, updatedStatus);
    }

    public OrderResponseDTO prepareOrder(OrderRequestDTO orderRequestDTO) {
        OrderStatus updatedStatus = OrderStatus.PREPARED;
        int orderId = orderRequestDTO.getOrderId();
        int restaurantId = orderRequestDTO.getRestaurantId();
        orderInterface.updateOrderStatus(orderId, new OrderStatusUpdateDTO(updatedStatus));
        return new OrderResponseDTO(orderId, restaurantId, updatedStatus);
    }
 }
