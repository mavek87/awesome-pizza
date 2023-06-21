package com.matteoveroni.awesomepizza.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PizzaName {

    MARGHERITA(1, "Pizza margherita"),
    CAPRICCIOSA(2, "Pizza capricciosa");

    private final int code;
    private final String name;
}
