package com.example.demo.request;

import lombok.Data;

@Data
public class CreateSubjectRequest {
    private String subjectName;
    private Double marksObtained;
}
