package com.example.demo.mutation;

import com.example.demo.request.CreateStudentRequest;
import com.example.demo.response.StudentResponse;
import com.example.demo.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

//@Controller
@AllArgsConstructor
public class StudentMutation  {

    private final StudentService studentService;

//    @MutationMapping
    public StudentResponse createStudent(@Argument CreateStudentRequest request) {
        return StudentResponse.builder(studentService.create(request)).build();

    }

}
