package com.findting.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.findting.dto.board.CreateBoard;
import com.findting.mapper.BoardRepository;
import com.findting.model.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public void write(CreateBoard createBoard) {
        Board board = objectMapper.convertValue(createBoard, Board.class);
        Board save = boardRepository.save(board);
    }
}
