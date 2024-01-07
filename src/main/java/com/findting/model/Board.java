package com.findting.model;

import com.findting.dto.board.CreateBoard;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Board {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @Lob
    private String content;

}
