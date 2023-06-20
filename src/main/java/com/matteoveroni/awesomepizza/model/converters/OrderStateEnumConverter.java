package com.matteoveroni.awesomepizza.model.converters;

import com.matteoveroni.awesomepizza.model.OrderState;
import jakarta.persistence.AttributeConverter;

public class OrderStateEnumConverter implements AttributeConverter<OrderState, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrderState orderState) {
        return switch (orderState) {
            case TO_PREPARE -> 1;
            case IN_PREPARATION -> 2;
            case READY -> 3;
        };
    }

    @Override
    public OrderState convertToEntityAttribute(Integer orderStateCode) {
        return switch (orderStateCode) {
            case 1 -> OrderState.TO_PREPARE;
            case 2 -> OrderState.IN_PREPARATION;
            case 3 -> OrderState.READY;
            default -> throw new IllegalStateException("Unexpected orderState code: " + orderStateCode);
        };
    }
}
