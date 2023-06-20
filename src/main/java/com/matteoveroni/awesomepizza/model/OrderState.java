package com.matteoveroni.awesomepizza.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderState {
    TO_PREPARE(1),
    IN_PREPARATION(2),
    READY(3);

    private final Integer code;
}