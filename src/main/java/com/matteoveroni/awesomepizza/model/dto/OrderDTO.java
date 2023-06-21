package com.matteoveroni.awesomepizza.model.dto;

import com.matteoveroni.awesomepizza.model.OrderState;
import java.util.Date;
import java.util.List;

public record OrderDTO(Long id, List<OrderItemDTO> orderItems, OrderState orderState, Date date, String notes) {
}
