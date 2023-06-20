package com.matteoveroni.awesomepizza.model;

import jakarta.persistence.Embeddable;

@Embeddable
public enum OrderState {
    TO_PREPARE,
    IN_PREPARATION,
    READY
}