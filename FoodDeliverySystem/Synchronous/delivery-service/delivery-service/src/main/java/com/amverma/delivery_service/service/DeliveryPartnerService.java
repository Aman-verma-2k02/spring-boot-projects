package com.amverma.delivery_service.service;

import com.amverma.delivery_service.dao.DeliveryPartnerDao;
import com.amverma.delivery_service.dto.DTOMapper;
import com.amverma.delivery_service.dto.DeliveryPartnerRequestDTO;
import com.amverma.delivery_service.dto.DeliveryPartnerResponseDTO;
import com.amverma.delivery_service.dto.DeliveryPartnerStatusUpdateDTO;
import com.amverma.delivery_service.entity.DeliveryPartner;
import com.amverma.delivery_service.enums.DeliveryPartnerStatus;
import com.amverma.delivery_service.exception.DeliveryPartnerNotFoundException;
import com.amverma.delivery_service.util.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
public class DeliveryPartnerService {
    @Autowired
    private DeliveryPartnerDao deliveryPartnerDao;
    @Autowired
    private DTOMapper dtoMapper;

    public DeliveryPartner findById(int id) {
        return deliveryPartnerDao.findById(id).orElseThrow(() ->
                new DeliveryPartnerNotFoundException(MessageFormat.format(CommonConstants.DELIVERY_PARTNER_NOT_FOUND, id)));
    }

    public DeliveryPartnerResponseDTO addNewDeliveryPartner(DeliveryPartnerRequestDTO deliveryPartnerRequestDTO) {
        DeliveryPartner deliveryPartner = dtoMapper.toEntity(deliveryPartnerRequestDTO);
        deliveryPartner.setStatus(DeliveryPartnerStatus.AVAILABLE);
        deliveryPartnerDao.save(deliveryPartner);
        return dtoMapper.toDTO(deliveryPartner);
    }

    public List<DeliveryPartnerResponseDTO> addNewDeliveryPartners(List<DeliveryPartnerRequestDTO> deliveryPartnerRequestDTOList) {
        return deliveryPartnerRequestDTOList.stream().map(this::addNewDeliveryPartner).toList();
    }
    public void save(DeliveryPartner deliveryPartner) {
        deliveryPartnerDao.save(deliveryPartner);
    }

    public List<DeliveryPartnerResponseDTO> getAllDeliveryPartners() {
        List<DeliveryPartner> deliveryPartnerList = deliveryPartnerDao.findAll();
        return deliveryPartnerList.stream().map(dtoMapper::toDTO).toList();
    }

    public DeliveryPartnerResponseDTO getDeliveryPartnerById(int id) {
        return dtoMapper.toDTO(findById(id));
    }

    public DeliveryPartnerResponseDTO deleteDeliveryPartnerById(int id) {
        DeliveryPartner deliveryPartner = this.findById(id);
        deliveryPartner.getDeliveryList().size();
        deliveryPartnerDao.delete(deliveryPartner);
        return dtoMapper.toDTO(deliveryPartner);
    }

    public DeliveryPartnerResponseDTO updateDeliveryPartnerById(int id, DeliveryPartnerRequestDTO deliveryPartnerRequestDTO) {
        DeliveryPartner deliveryPartner = this.findById(id);
        dtoMapper.update(deliveryPartnerRequestDTO, deliveryPartner);
        deliveryPartnerDao.save(deliveryPartner);
        return dtoMapper.toDTO(deliveryPartner);
    }

    public DeliveryPartnerResponseDTO updateDeliveryPartnerStatus(int id, DeliveryPartnerStatusUpdateDTO deliveryPartnerStatusUpdateDTO) {
        DeliveryPartner deliveryPartner = this.findById(id);
        DeliveryPartnerStatus status = deliveryPartnerStatusUpdateDTO.getStatus();
        if(status == DeliveryPartnerStatus.BUSY || deliveryPartner.getStatus() == DeliveryPartnerStatus.BUSY) {
            //TODO: error scenario delivery partner can't update the status from BUSY or to BUSY
        }
        deliveryPartner.setStatus(status);
        deliveryPartnerDao.save(deliveryPartner);
        return dtoMapper.toDTO(deliveryPartner);
    }
}
