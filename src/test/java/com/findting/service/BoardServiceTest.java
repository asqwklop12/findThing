package com.findting.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.findting.FindTingApplication;
import com.findting.dto.board.CreateBoard;
import com.findting.mapper.BoardRepository;
import com.findting.model.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = FindTingApplication.class)
@AutoConfigureMockMvc
class BoardServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private BoardRepository repository;

    @Test
    public void create() throws Exception {
        String json = objectMapper.writeValueAsString(new CreateBoard("title", "content"));
        mockMvc.perform(post("/board")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk());

        List<Board> boards = repository.findAll();

        // 첫번째꺼 가져온다.
        Board board = boards.get(0);

        Assertions.assertEquals(board.getTitle(), "title");

    }
}