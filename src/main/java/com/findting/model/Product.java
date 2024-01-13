package com.findting.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String productId;
    private String kind;

    private Long boardId;

    @Lob
    private String detail;

    private int count;

    public void addBoardId(Long boardId) {
        this.boardId = boardId;
    }
}
