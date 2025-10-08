package com.amverma.delivery_service.service;

import com.amverma.delivery_service.dao.DeliveryDao;
import com.amverma.delivery_service.dto.*;
import com.amverma.delivery_service.entity.Delivery;
import com.amverma.delivery_service.entity.DeliveryPartner;
import com.amverma.delivery_service.enums.DeliveryPartnerStatus;
import com.amverma.delivery_service.enums.DeliveryStatus;
import com.amverma.delivery_service.enums.OrderStatus;
import com.amverma.delivery_service.exception.DeliveryNotFoundException;
import com.amverma.delivery_service.exception.DeliveryPartnerAssignmentFailedException;
import com.amverma.delivery_service.feign.OrderInterface;
import com.amverma.delivery_service.util.CommonConstants;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
public class DeliveryService {
    private final DeliveryDao deliveryDao;
    private final DTOMapper dtoMapper;
    private final DeliveryPartnerService deliveryPartnerService;
    private final OrderInterface orderInterface;

    public DeliveryService(DeliveryDao deliveryDao, DTOMapper dtoMapper, DeliveryPartnerService deliveryPartnerService, OrderInterface orderInterface) {
        this.deliveryDao = deliveryDao;
        this.dtoMapper = dtoMapper;
        this.deliveryPartnerService = deliveryPartnerService;
        this.orderInterface = orderInterface;
    }

    public Delivery findById(int id) {
        return deliveryDao.findById(id).orElseThrow(() ->
                new DeliveryNotFoundException(MessageFormat.format(CommonConstants.DELIVERY_NOT_FOUND, id)));
    }

    public DeliveryResponseDTO createNewDelivery(DeliveryRequestDTO deliveryRequestDTO) {
        Delivery delivery = dtoMapper.toEntity(deliveryRequestDTO);
        delivery.setStatus(DeliveryStatus.PENDING);
        deliveryDao.save(delivery);
        return dtoMapper.toDTO(delivery);
    }

    public List<DeliveryResponseDTO> getAllDeliveries() {
        return deliveryDao.findAll().stream().map(dtoMapper::toDTO).toList();
    }

    public List<DeliveryResponseDTO> getAllPendingDeliveries() {
        return deliveryDao.findAllDeliveryByStatus(DeliveryStatus.PENDING).stream().map(dtoMapper::toDTO).toList();
    }

    private void validateAssignment(Delivery delivery, DeliveryPartner deliveryPartner) {
        if(delivery.getStatus() != DeliveryStatus.PENDING) {
            throw new DeliveryPartnerAssignmentFailedException(CommonConstants.DELIVERY_ALREADY_ASSIGNED);
        }
        if(deliveryPartner.getStatus() != DeliveryPartnerStatus.AVAILABLE) {
            throw new DeliveryPartnerAssignmentFailedException(CommonConstants.DELIVERY_PARTNER_NOT_AVAILABLE);
        }
    }

    public DeliveryResponseDTO assignDeliveryPartnerToDelivery(int id, int partnerId) {
        Delivery delivery = this.findById(id);
        DeliveryPartner deliveryPartner = deliveryPartnerService.findById(partnerId);
        validateAssignment(delivery, deliveryPartner);

        delivery.setDeliveryPartner(deliveryPartner);
        delivery.setStatus(DeliveryStatus.ASSIGNED);
        deliveryDao.save(delivery);

        deliveryPartner.setStatus(DeliveryPartnerStatus.BUSY);
        deliveryPartnerService.save(deliveryPartner);
        return dtoMapper.toDTO(delivery);
    }

    public DeliveryResponseDTO updateDeliveryStatus(int id, DeliveryStatusUpdateDTO deliveryStatusUpdateDTO) {
        Delivery delivery = this.findById(id);
        int orderId = delivery.getOrderId();
        int deliveryPartnerId = delivery.getDeliveryPartner().getId();

        DeliveryStatus status = deliveryStatusUpdateDTO.getStatus();
        if(status == DeliveryStatus.CANCELLED) {
            //TODO: Not allowed directly it should come from order-service and then via cancelDelivery
        }

        delivery.setStatus(status);
        deliveryDao.save(delivery);

        if(status == DeliveryStatus.EN_ROUTE) {
            orderInterface.updateOrderStatus(orderId, new OrderStatusUpdateDTO(OrderStatus.OUT_FOR_DELIVERY));
        } else if(status == DeliveryStatus.DELIVERED) {
            orderInterface.updateOrderStatus(orderId, new OrderStatusUpdateDTO(OrderStatus.DELIVERED));
            deliveryPartnerService.updateDeliveryPartnerStatus(deliveryPartnerId, new DeliveryPartnerStatusUpdateDTO(DeliveryPartnerStatus.AVAILABLE));
        }

        return dtoMapper.toDTO(delivery);
    }

    public DeliveryResponseDTO cancelDelivery(int orderId) {
        List<Delivery> deliveryList = deliveryDao.findDeliveryByOrderId(orderId);
        if(deliveryList.isEmpty()) throw new DeliveryNotFoundException(CommonConstants.DELIVERY_NOT_FOUND);

        Delivery delivery = deliveryList.get(0);
        delivery.setStatus(DeliveryStatus.CANCELLED);
        DeliveryPartner deliveryPartner = delivery.getDeliveryPartner();
        if(deliveryPartner != null) {
            deliveryPartnerService.updateDeliveryPartnerStatus(deliveryPartner.getId(), new DeliveryPartnerStatusUpdateDTO(DeliveryPartnerStatus.AVAILABLE));
        }
        deliveryDao.save(delivery);
        return dtoMapper.toDTO(delivery);
    }
}
