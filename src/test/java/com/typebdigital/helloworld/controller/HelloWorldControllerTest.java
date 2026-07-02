package com.typebdigital.helloworld.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void lowercaseNameInFirstHalf_returnsOkWithGreeting() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "alice"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\": \"Hello Alice\"}"));
    }

    @Test
    void uppercaseNameInFirstHalf_returnsOkWithGreeting() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "Mason"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\": \"Hello Mason\"}"));
    }

    @Test
    void nameStartingWithBoundaryLetterM_returnsOk() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "mike"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\": \"Hello Mike\"}"));
    }

    @Test
    void nameStartingWithBoundaryLetterA_returnsOk() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "aaron"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\": \"Hello Aaron\"}"));
    }

    @Test
    void allCapsName_isNormalizedInResponse() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "BOB"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\": \"Hello Bob\"}"));
    }

    @Test
    void lowercaseNameInSecondHalf_returnsBadRequest() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "nancy"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\": \"Invalid Input\"}"));
    }

    @Test
    void uppercaseNameInSecondHalf_returnsBadRequest() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "Zack"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\": \"Invalid Input\"}"));
    }

    @Test
    void nameStartingWithBoundaryLetterN_returnsBadRequest() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "nina"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\": \"Invalid Input\"}"));
    }

    @Test
    void missingNameParam_returnsBadRequest() throws Exception {
        mockMvc.perform(get("/hello-world"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\": \"Invalid Input\"}"));
    }

    @Test
    void emptyNameParam_returnsBadRequest() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", ""))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\": \"Invalid Input\"}"));
    }

    @Test
    void blankNameParam_returnsBadRequest() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "   "))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\": \"Invalid Input\"}"));
    }

    @Test
    void nameWithLeadingWhitespace_isTrimmedAndProcessed() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "  alice"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\": \"Hello Alice\"}"));
    }

    @Test
    void nameStartingWithNonLetterCharacter_returnsBadRequest() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "1alice"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\": \"Invalid Input\"}"));
    }

    @Test
    void singleLetterNameInFirstHalf_returnsOk() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "a"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\": \"Hello A\"}"));
    }

}
