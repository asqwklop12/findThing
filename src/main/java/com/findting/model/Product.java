package com.findting.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;
    private String kind;

    private Long boardId;

    @Lob
    private String detail;

    public void addBoardId(Long boardId) {
        this.boardId = boardId;
    }
}
