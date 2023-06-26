package com.matteoveroni.awesomepizza.controllers;

import com.matteoveroni.awesomepizza.model.dto.OrderDTO;
import com.matteoveroni.awesomepizza.services.OrdersService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PizzaMakerOrderController.class)
@AutoConfigureMockMvc(addFilters = false)
class PizzaMakerOrderControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private OrdersService ordersService;

    private final OrderDTO orderDTO = new OrderDTO(1L, null, null, null, null);

    @Test
    public void get_all_orders() throws Exception {
        when(ordersService.getAllOrders()).thenReturn(List.of(orderDTO));

        mockMvc.perform(get("/api/pizza_maker/orders")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void get_existing_order_by_id() throws Exception {
        when(ordersService.getOrder(1L)).thenReturn(Optional.of(orderDTO));

        MvcResult mvcResult = mockMvc.perform(get("/api/pizza_maker/orders/" + 1)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        assertTrue(contentAsString.contains("\"id\":1"));
    }

    @Test
    public void get_not_existing_order_by_id() throws Exception {
        when(ordersService.getOrder(2L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/pizza_maker/orders/" + 2)
                        .contentType("application/json"))
                .andExpect(status().is(404));
    }

    @Test
    public void get_next_order_to_prepare_with_no_next_order_available() throws Exception {
        when(ordersService.getNextOrderToPrepare()).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/pizza_maker/orders/next")
                        .contentType("application/json"))
                .andExpect(status().is(404));
    }

    @Test
    public void get_next_order_to_prepare_with_new_next_order_available() throws Exception {
        when(ordersService.getNextOrderToPrepare()).thenReturn(Optional.of(orderDTO));

        MvcResult mvcResult = mockMvc.perform(get("/api/pizza_maker/orders/next")
                        .contentType("application/json"))
                .andExpect(status().is(200))
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        assertTrue(contentAsString.contains("\"id\":1"));
    }

    @Test
    public void complete_existing_order() throws Exception {
        when(ordersService.completeOrder(1L)).thenReturn(Optional.of(orderDTO));

        MvcResult mvcResult = mockMvc.perform(post("/api/pizza_maker/orders/complete/" + 1)
                        .contentType("application/json"))
                .andExpect(status().is(200))
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        assertTrue(contentAsString.contains("\"id\":1"));
    }

    @Test
    public void complete_not_existing_order() throws Exception {
        when(ordersService.completeOrder(1L)).thenReturn(Optional.empty());

        mockMvc.perform(post("/api/pizza_maker/orders/complete/" + 1)
                        .contentType("application/json"))
                .andExpect(status().is(404));
    }

}