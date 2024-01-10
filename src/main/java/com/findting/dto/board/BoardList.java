package com.findting.dto.board;

import com.findting.model.Board;
import lombok.Getter;

@Getter
public class BoardList {
    private Long id;
    private String title;


    public BoardList(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
    }
}
