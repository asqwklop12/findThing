package com.findting.controller;

import com.findting.dto.board.CreateBoard;
import com.findting.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    @PostMapping()
    public void create(@RequestBody @Validated CreateBoard createBoard) {
        boardService.write(createBoard);
    }
}
