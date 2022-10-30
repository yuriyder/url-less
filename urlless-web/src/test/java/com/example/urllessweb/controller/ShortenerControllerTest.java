package com.example.urllessweb.controller;

import com.example.urllessweb.controller.dto.CreateUrlRequest;
import com.example.urllessweb.controller.dto.CreateUrlResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import gateway.UrlGateway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import shortener.ShortenedUrl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Test
    public void shouldReturn201OnCreatingUrlTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        CreateUrlRequest request = new CreateUrlRequest("http//some.url");
        String json = objectMapper.writeValueAsString(request);

        MvcResult mvcResult = mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andReturn();
        CreateUrlResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), CreateUrlResponse.class);
        ShortenedUrl expected = urlGateway.getAll().get(0);

        assertEquals(expected.getUrl(), response.getOriginalUrl());
        assertEquals("http://urle.ss/" + expected.getId(), response.getUrl());
    }
}
