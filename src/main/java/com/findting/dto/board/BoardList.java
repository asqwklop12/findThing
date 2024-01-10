package com.findting.dto.board;

import com.findting.model.Board;
import lombok.Getter;

@Getter
public class BoardList {
    private final Long id;
    private final String title;


    public BoardList(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
    }
}
