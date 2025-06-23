package com.example.demo.query;

import com.example.demo.request.CreateStudentRequest;
import com.example.demo.response.StudentResponse;
import com.example.demo.service.StudentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class StudentQuery {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentQuery.class);
    private final StudentService studentService;

    @QueryMapping(name = "students")
    public List<StudentResponse> getStudents() {
        LOGGER.info("getStudents method invoked");
        return studentService.findAll();
    }

    @QueryMapping(name = "student")
    public StudentResponse getStudent (@Argument Integer id) {

        LOGGER.info("getStudentsById method invoked {}", id);
        return studentService.getStudentById(id);
    }

    @MutationMapping
    public StudentResponse createStudent(@Argument CreateStudentRequest request) {
        return studentService.create(request);
    }

}
