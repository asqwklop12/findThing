package com.findting.dto.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateBoard {
    @NotBlank(message = "제목은 공백일 수 없습니다.")
    @NotEmpty(message = "제목은 공백일 수 없습니다.")
    @NotNull(message = "제목은 공백일 수 없습니다.")
    @Size(min = 4, max = 10, message = "길이를 준수해 주세요.")
    private final String title;
    @NotBlank(message = "내용은 공백일 수 없습니다.")
    @NotEmpty(message = "내용은 공백일 수 없습니다.")
    @NotNull(message = "내용은 공백일 수 없습니다.")
    private final String content;

    public CreateBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
