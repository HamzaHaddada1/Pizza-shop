package com.Otto.task.query.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetOrderState() throws Exception {
        String data = "{\"address\":\"SeelingStr 44\",\"price\": 12.0,\"customerId\": \"12\",\"paymentMethod\": \"ONLINE\",\"items\": [{\"pizza\": \"ROMA\"},{\"pizza\": \"NAPOLI\"},{\"pizza\": \"MARGHERITA\"}]}";
        MvcResult result = mockMvc.perform(post("/v1/order").content(data).contentType("application/json")).andExpect(status().isCreated()).andReturn();
        mockMvc.perform(get("/v1/order/"+ result.getResponse().getContentAsString()).content(data).contentType("application/json")).andExpect(status().isOk()).andReturn();
    }
}