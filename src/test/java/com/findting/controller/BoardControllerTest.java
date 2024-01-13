package com.findting.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.findting.dto.board.CreateBoard;
import com.findting.dto.board.FindProductInfo;
import com.findting.dto.board.UpdateBoard;
import com.findting.mapper.BoardRepository;
import com.findting.model.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BoardRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void defaultTest() throws Exception {
        FindProductInfo findProductInfo = new FindProductInfo("물건", "곰");
        String json = objectMapper.writeValueAsString(new CreateBoard("title", "content", "서울시", findProductInfo));
        mockMvc.perform(post("/board")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void blankTest() throws Exception {
        FindProductInfo findProductInfo = new FindProductInfo("물건", "곰");
        String json = objectMapper.writeValueAsString(new CreateBoard(null, "content", "서울시", findProductInfo));
        mockMvc.perform(post("/board")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 코드입니다."))
                .andExpect(jsonPath("$.validation.title").value("제목은 공백일 수 없습니다."))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addressNullTest() throws Exception {
        FindProductInfo findProductInfo = new FindProductInfo("물건", "곰");
        String json = objectMapper.writeValueAsString(new CreateBoard("title", "content", null, findProductInfo));
        mockMvc.perform(post("/board")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 코드입니다."))
                .andExpect(jsonPath("$.validation.address").value("주소는 공백일 수 없습니다."))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addressBlankTest() throws Exception {
        FindProductInfo findProductInfo = new FindProductInfo("물건", "곰");
        String json = objectMapper.writeValueAsString(new CreateBoard("title", "content", "", findProductInfo));
        mockMvc.perform(post("/board")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 코드입니다."))
                .andExpect(jsonPath("$.validation.address").value("주소는 공백일 수 없습니다."))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addressOneWordTest() throws Exception {
        FindProductInfo findProductInfo = new FindProductInfo("물건", "곰");
        String json = objectMapper.writeValueAsString(new CreateBoard("title", "content", "1", findProductInfo));
        mockMvc.perform(post("/board")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 코드입니다."))
                .andExpect(jsonPath("$.validation.address").value("주소는 1자리를 넘어야 합니다."))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void listReadTest() throws Exception {
        FindProductInfo findProductInfo = new FindProductInfo("물건", "곰");
        objectMapper.writeValueAsString(new CreateBoard(null, "content", "서울시", findProductInfo));
        mockMvc.perform(get("/board")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void editTest() throws Exception {
        FindProductInfo findProductInfo = new FindProductInfo("물건", "곰");
        String json = objectMapper.writeValueAsString(new CreateBoard("title", "content", "서울시", findProductInfo));
        mockMvc.perform(post("/board")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk());

        List<Board> boards = repository.findAll();
        Board board = boards.get(0);

        String editJson = objectMapper.writeValueAsString(new UpdateBoard("title2", "content2"));
        mockMvc.perform(patch("/board")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(editJson)
                        .pathInfo(board.getId().toString()))
                .andDo(print())
                .andExpect(status().isOk());


    }
}