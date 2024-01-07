package com.findting.service;

import com.findting.dto.board.CreateBoard;
import com.findting.dto.board.ReadBoard;
import com.findting.mapper.BoardRepository;
import com.findting.model.Board;
import com.findting.exception.notFound.NotFondException;
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

    @Test
    public void readOne() {
        boardService.write(new CreateBoard("title", "content"));

        List<Board> boards = repository.findAll();


        // 첫번째꺼 가져온다.
        Board board = boards.get(0);

        ReadBoard readBoard = boardService.read(board.getId());
        Long id = board.getId();

        Assertions.assertEquals(readBoard.getId(), id);
    }

    @Test
    public void readOneValidation() {
        boardService.write(new CreateBoard("title", "content"));

        List<Board> boards = repository.findAll();
        // 첫번째꺼 가져온다.
        Board board = boards.get(0);

        NotFondException exception = Assertions.assertThrows(NotFondException.class, () -> boardService.read(board.getId() + 1));

        Assertions.assertEquals(exception.getMessage(),"해당 글은 찾을 수가 없습니다.");
    }
}