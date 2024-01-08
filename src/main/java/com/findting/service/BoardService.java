package com.findting.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.findting.dto.board.*;
import com.findting.exception.notFound.BoardNotFoundException;
import com.findting.mapper.BoardRepository;
import com.findting.model.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public void write(CreateBoard createBoard) {
        Board board = objectMapper.convertValue(createBoard, Board.class);
        boardRepository.save(board);
    }

    public ReadBoard read(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(BoardNotFoundException::new);
        return objectMapper.convertValue(board, ReadBoard.class);
    }

    public ReadBoardList listRead(BoardCondition condition) {
        List<Board> boards = boardRepository.findAll();
        List<BoardList> boardList = objectMapper.convertValue(boards, new TypeReference<>() {
        });
        return new ReadBoardList(boardList, condition, boards.size());
    }

    public void edit(CreateBoard updateBoard, Long id) {
        Board board = objectMapper.convertValue(updateBoard, Board.class);
        board.addId(id);
        boardRepository.save(board);
    }

    public void remove(Long id) {
        Board board = objectMapper.convertValue(boardRepository.findById(id), Board.class);
        boardRepository.delete(board);
    }
}
