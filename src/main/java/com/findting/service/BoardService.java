package com.findting.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.findting.dto.board.*;
import com.findting.exception.notFound.BoardNotFoundException;
import com.findting.mapper.BoardRepository;
import com.findting.mapper.FileRepository;
import com.findting.mapper.ProductRepository;
import com.findting.model.Board;
import com.findting.model.Product;
import com.findting.model.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final ProductRepository productRepository;
    private final FileRepository fileRepository;
    private final ObjectMapper objectMapper;
    private final static String UPLOAD_URL = "/Users/yonghun/upload";

    @Transactional
    public void write(CreateBoard createBoard) {
        Board board = objectMapper.convertValue(createBoard, Board.class);
        boardRepository.save(board);
        FindProductInfo findProductInfo = createBoard.getProduct();
        Product product = objectMapper.convertValue(findProductInfo, Product.class);
        product.addBoardId(board.getId());
        productRepository.save(product);

    }

    public ReadBoard read(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(BoardNotFoundException::new);

        Product product = productRepository.findByBoardId(id).orElse(null);

        ReadBoard readBoard = objectMapper.convertValue(board, ReadBoard.class);
        ReadProduct readProduct = objectMapper.convertValue(product, ReadProduct.class);
        readBoard.addProduct(readProduct);
        return readBoard;
    }

    public ReadBoardList listRead(BoardCondition condition) {
        List<Board> findAll = boardRepository.findAll();
        PageRequest pageRequest = PageRequest.of(condition.getPage() - 1, condition.getCurrent());
        Page<Board> boards = boardRepository.findAll(pageRequest);
        List<BoardList> boardList = boards.stream().map(BoardList::new).collect(Collectors.toList());
        return new ReadBoardList(boardList, condition, findAll.size());
    }

    public void edit(UpdateBoard updateBoard, Long id) {
        Board board = objectMapper.convertValue(updateBoard, Board.class);
        Board findBoard = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        board.addId(id);
        board.originAddress(findBoard.getAddress());
        boardRepository.save(board);
    }

    public void remove(Long id) {
        Board board = objectMapper.convertValue(boardRepository.findById(id), Board.class);
        boardRepository.delete(board);
        // 상품을 조회한다.
        Product product = productRepository.findByBoardId(id).orElse(null);
        productRepository.delete(Objects.requireNonNull(product));
    }

    public void upload(MultipartFile file) throws IOException {
        UploadFile uploadFile = new UploadFile(file);
        fileRepository.save(uploadFile);
        String id = uploadFile.getId();
        file.transferTo(new File(UPLOAD_URL + "/" + id + file.getOriginalFilename()));
    }
}
