package com.example.demo.model;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class Author {
    private String firstName;
    private String lastName;
    private String phone;
}
