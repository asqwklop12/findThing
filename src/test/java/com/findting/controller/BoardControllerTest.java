package com.findting.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.findting.dto.board.CreateBoard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void defaultTest() throws Exception {
        String json = objectMapper.writeValueAsString(new CreateBoard("title", "content"));
        mockMvc.perform(post("/board")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void blankTest() throws Exception {
        String json = objectMapper.writeValueAsString(new CreateBoard(null, "content"));
        mockMvc.perform(post("/board")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 코드입니다."))
                .andExpect(jsonPath("$.validation.title").value("제목은 공백일 수 없습니다."))
                .andExpect(status().isBadRequest());
    }
}