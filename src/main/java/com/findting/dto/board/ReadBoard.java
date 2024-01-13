package com.findting.dto.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.findting.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReadBoard {
    private Long id;
    private String title;
    private String content;
    private ReadProduct product;

    public void addProduct(ReadProduct product) {
        this.product = product;
    }
}
