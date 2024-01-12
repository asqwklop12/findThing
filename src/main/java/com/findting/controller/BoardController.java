package com.findting.controller;

import com.findting.dto.board.*;
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

    // 보류
    @GetMapping()
    public ReadBoardList list(@RequestParam(required = false) String title,
                              @RequestParam(required = false) String content,
                              @RequestParam(required = false, defaultValue = "1") Integer page,
                              @RequestParam(required = false) Integer perPage) {
        return boardService.listRead(BoardCondition
                .builder()
                .title(title)
                .content(content)
                .page(page)
                .current(perPage)
                .build());
    }

    @GetMapping("/{id}")
    public ReadBoard read(@PathVariable Long id) {
        return boardService.read(id);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody @Validated CreateBoard createBoard, @PathVariable Long id) {
        boardService.edit(createBoard, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        boardService.remove(id);
    }

}
