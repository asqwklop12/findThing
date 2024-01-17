package com.findting.service;

import com.findting.dto.board.CreateBoard;
import com.findting.dto.board.FindProductInfo;
import com.findting.dto.board.ReadBoard;
import com.findting.dto.board.UpdateBoard;
import com.findting.exception.notFound.NotFondException;
import com.findting.mapper.BoardRepository;
import com.findting.mapper.ProductRepository;
import com.findting.model.Board;
import com.findting.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void create() {
        FindProductInfo findProductInfo = new FindProductInfo("물건", "곰");
        boardService.write(new CreateBoard("title", "content", "서울시", "file1", findProductInfo));

        List<Board> boards = repository.findAll();

        // 첫번째꺼 가져온다.
        Board board = boards.get(0);

        Assertions.assertEquals(board.getTitle(), "title");

    }

    @Test
    public void createByProduct() {
        FindProductInfo findProductInfo = new FindProductInfo("물건", "곰");
        boardService.write(new CreateBoard("title", "content", "서울시", "file1", findProductInfo));

        List<Board> boards = repository.findAll();

        // 첫번째꺼 가져온다.
        Board board = boards.get(0);

        List<Product> productList = productRepository.findAll();
        Product product = productList.get(0);
        Assertions.assertEquals(board.getId(), product.getBoardId());

    }

    @Test
    public void readOne() {
        FindProductInfo findProductInfo = new FindProductInfo("물건", "곰");
        boardService.write(new CreateBoard("title", "content", "서울시", "file1", findProductInfo));

        List<Board> boards = repository.findAll();


        // 첫번째꺼 가져온다.
        Board board = boards.get(0);

        ReadBoard readBoard = boardService.read(board.getId());
        Long id = board.getId();

        Assertions.assertEquals(readBoard.getId(), id);
    }

    @Test
    public void readOneValidation() {
        FindProductInfo findProductInfo = new FindProductInfo("물건", "곰");
        boardService.write(new CreateBoard("title", "content", "서울시", "file1", findProductInfo));

        List<Board> boards = repository.findAll();
        // 첫번째꺼 가져온다.
        Board board = boards.get(0);

        NotFondException exception = Assertions.assertThrows(NotFondException.class, () -> boardService.read(board.getId() + 1));

        Assertions.assertEquals(exception.getMessage(), "해당 글은 찾을 수가 없습니다.");
    }

    @Test
    public void createTimeTest() {
        FindProductInfo findProductInfo = new FindProductInfo("물건", "곰");
        boardService.write(new CreateBoard("title", "content", "서울시", "file1", findProductInfo));

        List<Board> boards = repository.findAll();
        // 첫번째꺼 가져온다.
        Board board = boards.get(0);

        Assertions.assertEquals(board.getCreatedDate(), LocalDate.now());
    }

    @Test
    public void updateAddressCheck() {
        FindProductInfo findProductInfo = new FindProductInfo("물건", "곰");
        boardService.write(new CreateBoard("title", "content", "서울시", "file1", findProductInfo));

        List<Board> boards = repository.findAll();
        // 첫번째꺼 가져온다.
        Board board = boards.get(0);

        UpdateBoard updateBoard = new UpdateBoard("물건1", "물건2","file2");

        boardService.edit(updateBoard, board.getId());

        Board findBoard = repository.findById(board.getId()).orElse(null);
        Assertions.assertEquals(Objects.requireNonNull(findBoard).getAddress(), "서울시");
    }
}