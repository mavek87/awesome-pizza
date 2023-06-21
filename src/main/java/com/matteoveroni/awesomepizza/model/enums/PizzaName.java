package com.matteoveroni.awesomepizza.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PizzaName {

    MARGHERITA(1),
    CAPRICCIOSA(2);

    private final int code;
}
