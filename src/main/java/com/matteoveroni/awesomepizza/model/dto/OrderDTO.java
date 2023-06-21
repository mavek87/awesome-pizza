package com.matteoveroni.awesomepizza.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.matteoveroni.awesomepizza.model.enums.OrderState;
import java.util.Date;
import java.util.List;

public record OrderDTO(
        @JsonProperty("id") Long id,
        @JsonProperty("order_items") List<OrderItemDTO> orderItems,
        @JsonProperty("order_state") OrderState orderState,
        @JsonProperty("date") Date date,
        @JsonProperty("notes") String notes) {
}