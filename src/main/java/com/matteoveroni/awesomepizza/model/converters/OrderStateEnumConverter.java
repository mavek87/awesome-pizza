package com.matteoveroni.awesomepizza.model.converters;

import com.matteoveroni.awesomepizza.model.enums.OrderState;
import jakarta.persistence.AttributeConverter;

public class OrderStateEnumConverter implements AttributeConverter<OrderState, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrderState orderState) {
        return switch (orderState) {
            case TO_BE_PREPARED -> OrderState.TO_BE_PREPARED.getCode();
            case IN_PREPARATION -> OrderState.IN_PREPARATION.getCode();
            case READY -> OrderState.READY.getCode();
        };
    }

    @Override
    public OrderState convertToEntityAttribute(Integer orderStateCode) {
        return OrderState.forValue(orderStateCode);
    }
}