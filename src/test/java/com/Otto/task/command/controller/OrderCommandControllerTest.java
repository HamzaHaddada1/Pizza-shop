package com.Otto.task.command.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createOrder() throws Exception {
        String data = "[{\"address\":\"SeelingStr 44\",\"price\": 12.0,\"customerId\": \"12\",\"paymentMethod\": \"ONLNE\",\"items\": [{\"pizza\": \"ROMA\"},{\"pizza\": \"NAPOLI\"},{\"pizza\": \"MARGHERITA\"}]}";
        mockMvc.perform(post("/v1/order").content(data).contentType("application/json")).andExpect(status().isOk());
    }

    @Test
    void cancelOrder() {
    }

    @Test
    void deliverOrder() {
    }
}