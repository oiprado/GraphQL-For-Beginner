package com.example.demo.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.demo.configuration.Properties;
import com.example.demo.model.Student;
import com.example.demo.model.SubjectNameFilter;
import com.example.demo.repository.StudentRepository;
import com.example.demo.response.StudentResponse;
import com.example.demo.response.SubjectResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class Query implements GraphQLQueryResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Query.class);
    @Autowired
    private Properties properties;

    private final StudentRepository studentRepository;

    public Query(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

//    @QueryMapping
//    public String firstQuery() {
//        return "First";
//    }
//
//    @QueryMapping
//    public Author findAuthor() {
//        return Author.builder()
//                .firstName("Oscar Ivan")
//                .lastName("Prado Perdomo")
//                .phone("3104749325")
//                .build();
//    }
//
//    public String findObject(Author author) {
//        return author.getFirstName() + " " + author.getLastName();
//    }

//    @QueryMapping
//    public Author findAuthor2(@Argument String firstName, @Argument String lastName, @Argument String phone) {
//        return Author.builder()
//                .firstName(firstName)
//                .lastName(lastName)
//                .phone(phone)
//                .build();
//    }

    @QueryMapping(name = "students")
    public List<StudentResponse> getStudents() {

        LOGGER.info(properties.getSecretKey());

        return studentRepository.findAll()
                .stream()
                .map(student ->  StudentResponse.builder(student).build())
                .toList();
    }

    @QueryMapping(name = "student")
    public StudentResponse getStudent (@Argument Integer id) {

        Optional<Student> studentOptional = studentRepository.findById(id);
        return studentOptional.map(student -> StudentResponse.builder(student).build()).orElse(null);
    }

    @SchemaMapping(field = "learningSubjects", typeName = "StudentResponse")
    public List<SubjectResponse> getLearningSubjects(StudentResponse studentResponse, @Argument SubjectNameFilter subjectNameFilter) {

        List<SubjectResponse> subjectResponses = Collections.emptyList();
        var learningSubjects = studentResponse.getStudent().getLearningSubjects();

        if (!learningSubjects.isEmpty()) {
            var stream = learningSubjects.stream();

            if (!subjectNameFilter.name().equalsIgnoreCase("all")) {
                stream = stream.filter(subject ->
                        subjectNameFilter.name().equalsIgnoreCase(subject.getSubjectName()));
            }

            subjectResponses = stream.map(SubjectResponse::new).toList();
        }
        return subjectResponses;
    }


    @SchemaMapping("fullName")
    public String getFullName(StudentResponse studentResponse) {
        return studentResponse.getFirstName() + " " + studentResponse.getLastName();
    }


}
