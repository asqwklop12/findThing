package com.findting.dto.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReadBoard {
    private Long id;
    private String title;
    private String address;
    private String content;
    private ReadProduct product;

    public void addProduct(ReadProduct product) {
        this.product = product;
    }
}
