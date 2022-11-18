package com.Otto.task.command.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateOrderSuccessful() throws Exception {
        String data = "{\"address\":\"SeelingStr 44\",\"price\": 12.0,\"customerId\": \"12\",\"paymentMethod\": \"ONLINE\",\"items\": [{\"pizza\": \"ROMA\"},{\"pizza\": \"NAPOLI\"},{\"pizza\": \"MARGHERITA\"}]}";
        MvcResult result = mockMvc.perform(post("/v1/order").content(data).contentType("application/json")).andExpect(status().isCreated()).andReturn();
    }

    @Test
    void testCancelOrderSuccess() throws Exception {
        String data = "{\"address\":\"SeelingStr 44\",\"price\": 12.0,\"customerId\": \"12\",\"paymentMethod\": \"ONLINE\",\"items\": [{\"pizza\": \"ROMA\"},{\"pizza\": \"NAPOLI\"},{\"pizza\": \"MARGHERITA\"}]}";
        MvcResult result = mockMvc.perform(post("/v1/order").content(data).contentType("application/json")).andExpect(status().isCreated()).andReturn();
        mockMvc.perform(put("/v1/order/cancel/" + result.getResponse().getContentAsString()).contentType("application/json")).andExpect(status().isOk());
    }

    @Test
    void testDeliverOrderSuccess() throws Exception {
        String data = "{\"address\":\"SeelingStr 44\",\"price\": 12.0,\"customerId\": \"12\",\"paymentMethod\": \"ONLINE\",\"items\": [{\"pizza\": \"ROMA\"},{\"pizza\": \"NAPOLI\"},{\"pizza\": \"MARGHERITA\"}]}";
        MvcResult result = mockMvc.perform(post("/v1/order").content(data).contentType("application/json")).andExpect(status().isCreated()).andReturn();
        mockMvc.perform(put("/v1/order/private/deliver/" + result.getResponse().getContentAsString()).contentType("application/json")).andExpect(status().isOk());
    }

    @Test
    void testCreateOrderFailure() throws Exception {
        String data = "{\"address\":\"SeelingStr 44\",\"price\": 12.0,\"customerId\": \"12\",\"paymentMethod\": \"ONLNE\",\"items\": [{\"pizza\": \"ROMA\"},{\"pizza\": \"NAPOLI\"},{\"pizza\": \"MARGHERITA\"}]}";
        MvcResult result = mockMvc.perform(post("/v1/order").content(data).contentType("application/json")).andExpect(status().is4xxClientError()).andReturn();
        assertEquals("Payment method is not supported", result.getResponse().getContentAsString());
    }

    @Test
    void testCancelOrderFailure() throws Exception {
        String data = "{\"address\":\"SeelingStr 44\",\"price\": 12.0,\"customerId\": \"12\",\"paymentMethod\": \"ONLINE\",\"items\": [{\"pizza\": \"ROMA\"},{\"pizza\": \"NAPOLI\"},{\"pizza\": \"MARGHERITA\"}]}";
        MvcResult result = mockMvc.perform(post("/v1/order").content(data).contentType("application/json")).andExpect(status().isCreated()).andReturn();
        mockMvc.perform(put("/v1/order/private/deliver/" + result.getResponse().getContentAsString()).contentType("application/json")).andExpect(status().isOk());
        mockMvc.perform(put("/v1/order/cancel/" + result.getResponse().getContentAsString()).contentType("application/json")).andExpect(status().is4xxClientError());
    }
}