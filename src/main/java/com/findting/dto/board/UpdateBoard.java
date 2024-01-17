package com.findting.dto.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateBoard {
    @NotBlank(message = "제목은 공백일 수 없습니다.")
    @NotEmpty(message = "제목은 공백일 수 없습니다.")
    @NotNull(message = "제목은 공백일 수 없습니다.")
    @Size(min = 4, max = 10, message = "길이를 준수해 주세요.")
    private final String title;
    @NotBlank(message = "내용은 공백일 수 없습니다.")
    @NotEmpty(message = "내용은 공백일 수 없습니다.")
    @NotNull(message = "내용은 공백일 수 없습니다.")
    private String content;

    @NotBlank(message = "파일은 필수 입니다.")
    @NotEmpty(message = "파일은 필수 입니다.")
    @NotNull(message = "파일은 필수 입니다.")
    private String file;
}
