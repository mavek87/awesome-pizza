package com.matteoveroni.awesomepizza.integration_tests.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matteoveroni.awesomepizza.model.Order;
import com.matteoveroni.awesomepizza.model.Pizza;
import com.matteoveroni.awesomepizza.model.dto.OrderDTO;
import com.matteoveroni.awesomepizza.model.dto.OrderItemDTO;
import com.matteoveroni.awesomepizza.model.dto.PizzaDTO;
import com.matteoveroni.awesomepizza.model.enums.OrderState;
import com.matteoveroni.awesomepizza.model.enums.PizzaName;
import com.matteoveroni.awesomepizza.repositories.OrdersRepository;
import com.matteoveroni.awesomepizza.services.PizzaCatalogService;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class CustomerOrderControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private OrdersRepository ordersRepository;
    @Autowired private PizzaCatalogService pizzaCatalogService;

    @AfterEach
    public void tearDown() {
        ordersRepository.deleteAll();
    }

    @Test
    public void customer_order_with_correct_data_input() throws Exception {
        final OrderState orderState = OrderState.TO_BE_PREPARED;
        final Pizza pizzaMargherita = pizzaCatalogService.getPizza(PizzaName.MARGHERITA);
        final Pizza pizzaCapricciosa = pizzaCatalogService.getPizza(PizzaName.MARGHERITA);
        final PizzaDTO pizzaMargheritaDTO = new PizzaDTO(null, pizzaMargherita.getName(), pizzaMargherita.getDescription(), pizzaMargherita.getPrice());
        final PizzaDTO pizzaCapricciosaDTO = new PizzaDTO(null, pizzaCapricciosa.getName(), pizzaCapricciosa.getDescription(), pizzaCapricciosa.getPrice());
        final OrderItemDTO orderItem1 = new OrderItemDTO(null, pizzaMargheritaDTO, 1);
        final OrderItemDTO orderItem2 = new OrderItemDTO(null, pizzaCapricciosaDTO, 1);
        final String note = "Just a note";
        final OrderDTO orderDTO = new OrderDTO(null, List.of(orderItem1, orderItem2), orderState, null, note);

        String json = objectMapper.writeValueAsString(orderDTO);

        MvcResult mvcResult = mockMvc.perform(post("/api/customer/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        Long id = Long.parseLong(contentAsString);

        Order savedOrder = ordersRepository.findById(id).orElseThrow(() -> new IllegalStateException("Id not found as expected"));
        assertEquals(orderDTO.orderState(), savedOrder.getOrderState(), "Order state is different from expectations");
        assertEquals(orderDTO.orderItems().size(), savedOrder.getOrderItems().size(), "Order items are different from expectations");
        assertEquals(orderDTO.notes(), savedOrder.getNotes(), "Order notes are different from expectations");
        assertNotNull(savedOrder.getId(), "Order id is null");
        assertNotNull(savedOrder.getDate(), "Order date is null");
    }

    @Test
    public void customer_order_with_incorrect_data_input() throws Exception {
        mockMvc.perform(post("/api/customer/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "fake_key_1": "hello world",
                                "fake_key_2": 25
                            }
                        """))
                .andExpect(status().is(400));
    }

}