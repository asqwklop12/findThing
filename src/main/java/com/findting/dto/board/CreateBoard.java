package com.findting.dto.board;

import jakarta.validation.constraints.*;
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

    @NotBlank(message = "주소는 공백일 수 없습니다.")
    @NotEmpty(message = "주소는 공백일 수 없습니다.")
    @NotNull(message = "주소는 공백일 수 없습니다.")
    private final String address;

    @NotBlank(message = "파일 정보는 필수 입니다.")
    @NotEmpty(message = "파일 정보는 필수 입니다.")
    @NotNull(message = "파일 정보는 필수 입니다.")
    private final String file;
    // 어떤 물건을 찾을지
    private final FindProductInfo product;

    public CreateBoard(String title, String content, String address, String file, FindProductInfo findProductInfo) {
        this.title = title;
        this.content = content;
        this.address = address;
        this.file = file;
        this.product = findProductInfo;
    }
}
