package com.amverma.delivery_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DeliveryPartnerAssignmentFailedException extends RuntimeException{
    public DeliveryPartnerAssignmentFailedException(String message) {
        super(message);
    }
}
