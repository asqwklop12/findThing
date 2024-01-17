package com.findting.controller;

import com.findting.dto.board.*;
import com.findting.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        return boardService.upload(file);
    }

    @PostMapping()
    public void create(@RequestBody @Validated CreateBoard createBoard) {
        boardService.write(createBoard);
    }


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
    public void update(@RequestBody @Validated UpdateBoard updateBoard, @PathVariable Long id) {
        boardService.edit(updateBoard, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        boardService.remove(id);
    }

}
