package com.amverma.delivery_service.dto;

import com.amverma.delivery_service.entity.Delivery;
import com.amverma.delivery_service.entity.DeliveryPartner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DTOMapper {
    public DeliveryPartner toEntity(DeliveryPartnerRequestDTO deliveryPartnerRequestDTO);
    public Delivery toEntity(DeliveryRequestDTO deliveryRequestDTO);

    @Mapping(source = "id", target = "deliveryPartnerId")
    public DeliveryPartnerResponseDTO toDTO(DeliveryPartner deliveryPartner);

    @Mapping(source = "id", target = "deliveryId")
    @Mapping(source = "deliveryPartner.id", target = "deliveryPartnerId")
    public DeliveryResponseDTO toDTO(Delivery delivery);

    default List<Integer> convertDeliveryList(List<Delivery> deliveryList) {
        return deliveryList.stream().map(Delivery::getId).toList();
    }

    public void update(DeliveryPartnerRequestDTO deliveryPartnerRequestDTO, @MappingTarget DeliveryPartner deliveryPartner);
}
