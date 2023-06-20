package com.matteoveroni.awesomepizza.model.converters;

import com.matteoveroni.awesomepizza.model.OrderState;
import jakarta.persistence.AttributeConverter;

public class OrderStateEnumConverter implements AttributeConverter<OrderState, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrderState orderState) {
        return switch (orderState) {
            case TO_PREPARE -> OrderState.TO_PREPARE.getCode();
            case IN_PREPARATION -> OrderState.IN_PREPARATION.getCode();
            case READY -> OrderState.READY.getCode();
        };
    }

    @Override
    public OrderState convertToEntityAttribute(Integer orderStateCode) {
        if (OrderState.TO_PREPARE.getCode().equals(orderStateCode)) {
            return OrderState.TO_PREPARE;
        } else if (OrderState.IN_PREPARATION.getCode().equals(orderStateCode)) {
            return OrderState.IN_PREPARATION;
        } else if (OrderState.READY.getCode().equals(orderStateCode)) {
            return OrderState.READY;
        } else {
            throw new IllegalStateException("Unexpected orderState code: " + orderStateCode);
        }
    }
}
