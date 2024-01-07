package com.findting.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BoardCondition {
    private Long id;
    private String title;
    private String content;
    private int page;
    private int current;
}
