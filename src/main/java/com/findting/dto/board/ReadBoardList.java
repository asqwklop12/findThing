package com.findting.dto.board;

import lombok.Getter;

import java.util.List;

@Getter
public class ReadBoardList {
    private final int total;
    private final int page;
    private final int current;
    private final List<BoardList> boardList;

    public ReadBoardList(List<BoardList> boardList, BoardCondition condition, int total) {
        this.total = total;
        this.boardList = boardList;
        this.page = condition.getPage();
        this.current = condition.getCurrent();
    }
}
