package com.findting.service;

import com.findting.dto.board.CreateBoard;
import com.findting.mapper.BoardRepository;
import com.findting.model.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository repository;

    @Test
    public void create() {
        boardService.write(new CreateBoard("title", "content"));

        List<Board> boards = repository.findAll();

        // 첫번째꺼 가져온다.
        Board board = boards.get(0);

        Assertions.assertEquals(board.getTitle(), "title");

    }
}