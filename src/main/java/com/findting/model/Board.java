package com.findting.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(value = {AuditingEntityListener.class})
@ToString
public class Board {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @Lob
    private String content;

    @NotNull
    private String address;

    @NotNull
    private String file;

    @CreatedDate
    private LocalDate createdDate;

    public void addId(Long id) {
        this.id = id;
    }

    public void originAddress(String address) {
        this.address = address;
    }

//    public void readProduct(Product product) {
//        this.product = product;
//    }
}
