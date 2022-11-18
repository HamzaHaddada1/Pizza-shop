package com.Otto.task.query.controller;

import com.Otto.task.lib.entity.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetOrderCreatedState() throws Exception {
        String data = "{\"address\":\"SeelingStr 44\",\"price\": 12.0,\"customerId\": \"12\",\"paymentMethod\": \"ONLINE\",\"items\": [{\"pizza\": \"ROMA\"},{\"pizza\": \"NAPOLI\"},{\"pizza\": \"MARGHERITA\"}]}";
        MvcResult result = mockMvc.perform(post("/v1/order").content(data).contentType("application/json")).andExpect(status().isCreated()).andReturn();
        result = mockMvc.perform(get("/v1/order/"+ result.getResponse().getContentAsString()).contentType("application/json")).andExpect(status().isOk()).andReturn();
        assertEquals(State.CREATED.toString(), result.getResponse().getContentAsString());
    }

    @Test
    void testGetOrderCanceledState() throws Exception {
        String data = "{\"address\":\"SeelingStr 44\",\"price\": 12.0,\"customerId\": \"12\",\"paymentMethod\": \"ONLINE\",\"items\": [{\"pizza\": \"ROMA\"},{\"pizza\": \"NAPOLI\"},{\"pizza\": \"MARGHERITA\"}]}";
        MvcResult result = mockMvc.perform(post("/v1/order").content(data).contentType("application/json")).andExpect(status().isCreated()).andReturn();
        mockMvc.perform(put("/v1/order/cancel/" + result.getResponse().getContentAsString()).contentType("application/json")).andExpect(status().isOk());
        result = mockMvc.perform(get("/v1/order/"+ result.getResponse().getContentAsString()).contentType("application/json")).andExpect(status().isOk()).andReturn();
        assertEquals(State.CANCELED.toString(), result.getResponse().getContentAsString());
    }

    @Test
    void testGetOrderDeliveredState() throws Exception {
        String data = "{\"address\":\"SeelingStr 44\",\"price\": 12.0,\"customerId\": \"12\",\"paymentMethod\": \"ONLINE\",\"items\": [{\"pizza\": \"ROMA\"},{\"pizza\": \"NAPOLI\"},{\"pizza\": \"MARGHERITA\"}]}";
        MvcResult result = mockMvc.perform(post("/v1/order").content(data).contentType("application/json")).andExpect(status().isCreated()).andReturn();
        mockMvc.perform(put("/v1/order/private/deliver/" + result.getResponse().getContentAsString()).contentType("application/json")).andExpect(status().isOk());
        result = mockMvc.perform(get("/v1/order/"+ result.getResponse().getContentAsString()).contentType("application/json")).andExpect(status().isOk()).andReturn();
        assertEquals(State.DELIVERED.toString(), result.getResponse().getContentAsString());
    }
}