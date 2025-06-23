package com.example.demo.response;

import com.example.demo.model.Student;
import com.example.demo.model.Subject;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(builderMethodName = "internalBuilder")
public class StudentResponse {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String street;
    private String city;
    private List<Subject> learningSubjects;
    private Student student;

    public static StudentResponse.StudentResponseBuilder builder(Student student) {

        return internalBuilder()
                .student(student)
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .street(student.getAddress().getStreet())
                .city(student.getAddress().getCity());
    }

}
