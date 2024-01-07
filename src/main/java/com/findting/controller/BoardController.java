package com.findting.controller;

import com.findting.dto.board.BoardCondition;
import com.findting.dto.board.CreateBoard;
import com.findting.dto.board.ReadBoard;
import com.findting.dto.board.ReadBoardList;
import com.findting.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    @PostMapping()
    public void create(@RequestBody @Validated CreateBoard createBoard) {
        boardService.write(createBoard);
    }

    @GetMapping()
    public ReadBoardList list(BoardCondition condition) {
        return boardService.listRead(condition);
    }

    @GetMapping("/{idx}")
    public ReadBoard read(@PathVariable Long idx) {
        return boardService.read(idx);
    }
}
