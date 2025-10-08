package com.amverma.delivery_service.controller;

import com.amverma.delivery_service.dto.DeliveryPartnerRequestDTO;
import com.amverma.delivery_service.dto.DeliveryPartnerStatusUpdateDTO;
import com.amverma.delivery_service.service.DeliveryPartnerService;
import com.amverma.delivery_service.util.ApiResponse;
import com.amverma.delivery_service.util.CommonConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;

@RestController
@RequestMapping("/delivery-partners")
public class DeliveryPartnerController {
    private final DeliveryPartnerService deliveryPartnerService;
    public DeliveryPartnerController(DeliveryPartnerService deliveryPartnerService) {
        this.deliveryPartnerService = deliveryPartnerService;
    }

    @PostMapping("/internal")
    public ResponseEntity<ApiResponse<?>> addNewDeliveryPartners(@RequestBody List<DeliveryPartnerRequestDTO> deliveryRequestDTOList) {
        return ResponseEntity.ok(ApiResponse.success(deliveryPartnerService.addNewDeliveryPartners(deliveryRequestDTOList), CommonConstants.DELIVERY_PARTNER_REGISTERED));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> addNewDeliveryPartner(@RequestBody DeliveryPartnerRequestDTO deliveryPartnerRequestDTO) {
        return ResponseEntity.ok(ApiResponse.success(deliveryPartnerService.addNewDeliveryPartner(deliveryPartnerRequestDTO), CommonConstants.DELIVERY_PARTNER_REGISTERED));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllDeliveryPartners() {
        return ResponseEntity.ok(ApiResponse.success(deliveryPartnerService.getAllDeliveryPartners(), CommonConstants.DELIVERY_PARTNER_FETCHED));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getDeliveryPartnerById(@PathVariable int id) {
        return ResponseEntity.ok(ApiResponse.success(deliveryPartnerService.getDeliveryPartnerById(id), CommonConstants.DELIVERY_PARTNER_FETCHED));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteDeliveryPartnerById(@PathVariable int id) {
        return ResponseEntity.ok(ApiResponse.success(deliveryPartnerService.deleteDeliveryPartnerById(id), CommonConstants.DELIVERY_PARTNER_DELETED));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateDeliveryPartnerById(@PathVariable int id, @RequestBody DeliveryPartnerRequestDTO deliveryPartnerRequestDTO) {
        return ResponseEntity.ok(ApiResponse.success(deliveryPartnerService.updateDeliveryPartnerById(id, deliveryPartnerRequestDTO), CommonConstants.DELIVERY_PARTNER_UPDATED));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<?>> updateDeliveryPartnerStatus(@PathVariable int id, @RequestBody DeliveryPartnerStatusUpdateDTO deliveryPartnerStatusUpdateDTO) {
        return ResponseEntity.ok(ApiResponse.success(deliveryPartnerService.updateDeliveryPartnerStatus(id, deliveryPartnerStatusUpdateDTO), MessageFormat.format(CommonConstants.DELIVERY_PARTNER_STATUS_UPDATED, deliveryPartnerStatusUpdateDTO.getStatus())));
    }
}
