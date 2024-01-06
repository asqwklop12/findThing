package com.findting.controller;

import com.findting.dto.board.CreateBoard;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
public class BoardController {

    @PostMapping()
    public void create(@RequestBody @Validated CreateBoard createBoard) {
    }
}
