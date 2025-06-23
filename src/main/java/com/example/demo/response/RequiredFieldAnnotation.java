package com.example.demo.response;

import com.example.demo.model.Student;
import lombok.Builder;

@Builder(builderMethodName = "internalBuilder")
public class RequiredFieldAnnotation {

    private String name;
    private String description;

    public static RequiredFieldAnnotationBuilder builder(Student student) {
        return internalBuilder().name(student.getFirstName() + " " + student.getLastName());
    }
}
