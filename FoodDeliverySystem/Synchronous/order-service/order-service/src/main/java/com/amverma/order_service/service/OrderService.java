package com.amverma.order_service.service;

import com.amverma.order_service.dao.OrderDao;
import com.amverma.order_service.dto.*;
import com.amverma.order_service.entity.Order;
import com.amverma.order_service.enums.OrderStatus;
import com.amverma.order_service.exception.OrderNotFoundException;
import com.amverma.order_service.exception.RestaurantNotFoundException;
import com.amverma.order_service.exception.UserNotFoundException;
import com.amverma.order_service.feign.DeliveryInterface;
import com.amverma.order_service.feign.RestaurantInterface;
import com.amverma.order_service.feign.UserInterface;
import com.amverma.order_service.util.ApiResponse;
import com.amverma.order_service.util.CommonConstants;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
public class OrderService {
    private final OrderDao orderDao;
    private final DTOMapper dtoMapper;
    private final UserInterface userInterface;
    private final RestaurantInterface restaurantInterface;
    private final DeliveryInterface deliveryInterface;
    public OrderService(OrderDao orderDao, DTOMapper dtoMapper, UserInterface userInterface, RestaurantInterface restaurantInterface, DeliveryInterface deliveryInterface) {
        this.orderDao = orderDao;
        this.dtoMapper = dtoMapper;
        this.userInterface = userInterface;
        this.restaurantInterface = restaurantInterface;
        this.deliveryInterface = deliveryInterface;
    }
    private Order findOrderById(int id) {
        return orderDao.findById(id).orElseThrow(() ->
                new OrderNotFoundException(MessageFormat.format(CommonConstants.ORDER_NOT_FOUND, id)));
    }

    private UserResponseDTO getValidUser(int id) {
        ApiResponse<UserResponseDTO> userResponse = userInterface.getUserById(id).getBody();
        if(!userResponse.getStatus()) throw new UserNotFoundException(userResponse.getMessage());
        return userResponse.getData();
    }

    private RestaurantResponseDTO getValidRestaurant(int id) {
        ApiResponse<RestaurantResponseDTO> restaurantResponse = restaurantInterface.getRestaurantById(id).getBody();
        if(!restaurantResponse.getStatus()) throw new RestaurantNotFoundException(restaurantResponse.getMessage());
        return restaurantResponse.getData();
    }

    public OrderResponseDTO createNewOrder(OrderRequestDTO orderRequestDto) {
        UserResponseDTO userResponseDTO = this.getValidUser(orderRequestDto.getUserId());
        RestaurantResponseDTO restaurantResponseDTO = this.getValidRestaurant(orderRequestDto.getRestaurantId());

        Order order = dtoMapper.toEntity(orderRequestDto);
        orderDao.save(order);

        deliveryInterface.createNewDelivery(
                new DeliveryRequestDTO(order.getId(), restaurantResponseDTO.getAddress(), userResponseDTO.getAddress())
        );

        return dtoMapper.toDto(order);
    }

    public List<OrderResponseDTO> createNewOrders(List<OrderRequestDTO> orderRequestDTOList) {
        return orderRequestDTOList.stream().map(this::createNewOrder).toList();
    }

    public List<OrderResponseDTO> getAllOrders() {
        List<Order> orderList = orderDao.findAll();
        return orderList.stream().map(dtoMapper::toDto).toList();
    }

    public OrderResponseDTO getOrderById(int id) {
        return dtoMapper.toDto(this.findOrderById(id));
    }

    public OrderResponseDTO deleteOrderById(int id) {
        Order order = this.findOrderById(id);
        orderDao.delete(order);
        return dtoMapper.toDto(order);
    }

    public OrderResponseDTO updateOrderStatus(int id, OrderStatusUpdateDTO orderStatusUpdateDto) {
        Order order = this.findOrderById(id);
        OrderStatus status = orderStatusUpdateDto.getStatus();

        order.setStatus(status);
        orderDao.save(order);

        if(status == OrderStatus.CANCELLED) {
            deliveryInterface.cancelDelivery(id);
        }
        return dtoMapper.toDto(order);
    }

    public List<OrderResponseDTO> getOrdersByUserId(int userId) {
        List<Order> orderList = orderDao.findByUserId(userId);
        return orderList.stream().map(dtoMapper::toDto).toList();
    }
}
