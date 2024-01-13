package com.findting.dto.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class FindProductInfo {
    @NotBlank(message = "종류를 입력해주세요.")
    @NotEmpty(message = "종류를 입력해주세요.")
    @NotNull(message = "종류를 입력해주세요.")
    private String kind;

    @NotBlank(message = "특징을 설명해주세요.")
    @NotEmpty(message = "특징을 설명해주세요.")
    @NotNull(message = "특징을 설명해주세요.")
    private String detail;

    public FindProductInfo(@NotNull(message = "종류를 입력해주세요.") String kind, @NotNull(message = "특징을 설명해주세요.") String detail) {
        this.kind = kind;
        this.detail = detail;
    }
}
