package com.example.demo.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.demo.model.SubjectNameFilter;
import com.example.demo.response.StudentResponse;
import com.example.demo.response.SubjectResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class SubjectResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectResolver.class);

    @SchemaMapping(field = "learningSubjects", typeName = "StudentResponse")
    public List<SubjectResponse> getLearningSubjects(StudentResponse studentResponse, @Argument SubjectNameFilter subjectNameFilter) {

        LOGGER.info("getLearningSubjects resolver invoked");

        List<SubjectResponse> subjectResponses = Collections.emptyList();
        var learningSubjects = studentResponse.getStudent().getLearningSubjects();

        if(learningSubjects != null) {
            if (!learningSubjects.isEmpty()) {
                var stream = learningSubjects.stream();

                if (!subjectNameFilter.name().equalsIgnoreCase("all")) {
                    stream = stream.filter(subject ->
                            subjectNameFilter.name().equalsIgnoreCase(subject.getSubjectName()));
                }

                subjectResponses = stream.map(SubjectResponse::new).toList();
            }
        }

        return subjectResponses;
    }

    @SchemaMapping("fullName")
    public String getFullName(StudentResponse studentResponse) {
        LOGGER.info("getFullName resolver invoked");
        return studentResponse.getFirstName() + " " + studentResponse.getLastName();
    }

}
