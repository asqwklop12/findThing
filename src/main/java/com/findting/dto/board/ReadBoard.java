package com.findting.dto.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReadBoard {
    private Long id;
    private String title;
    private String content;
}
