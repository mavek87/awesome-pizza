package com.matteoveroni.awesomepizza.model.enums;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderState {
    TO_BE_PREPARED(1),
    IN_PREPARATION(2),
    READY(3);

    private static final Map<Integer, OrderState> typesByCode = new HashMap<>();

    static {
        for (OrderState type : OrderState.values()) {
            typesByCode.put(type.code, type);
        }
    }

    private final Integer code;

    public static OrderState forValue(Integer code) {
        if (code != null && typesByCode.containsKey(code)) {
            return typesByCode.get(code);
        } else {
            throw new IllegalArgumentException("Unknown orderState code: " + code);
        }
    }
}