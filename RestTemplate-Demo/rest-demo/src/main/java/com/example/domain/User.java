package com.example.domain;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class User implements Serializable {
    private Integer id;
    private String name;
    private Double price;
}
