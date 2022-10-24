package com.example.urllessweb.controller;

import gateway.UrlGateway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ShortenerControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public UrlGateway urlGateway;

    @Test
    public void shouldReturn404OnNonExistingUrlTest() throws Exception {
        mockMvc.perform(get("/NON-EXISTING-URL"))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .string("URL does not exist"));
    }

    @Test
    public void shouldReturn301OnExistingUrlTest() throws Exception {
        urlGateway.create("http://some.test", "abcd12");
        mockMvc.perform(get("/abcd12"))
                .andExpect(status().isMovedPermanently())
                .andExpect(header().string("Location", "http://some.test"));
    }
}
