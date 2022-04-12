package com.example.telda.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Directory {
    private Long id;
    private String name;
    private String abbreviation;
}
