package com.example.demo.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.demo.response.StudentResponse;
import com.example.demo.response.SubjectResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectResolver implements GraphQLResolver<StudentResponse> {
    public List<SubjectResponse> getLearningSubjects(StudentResponse studentResponse) {

        List<SubjectResponse> subjectResponses = new ArrayList<>();

        if(!studentResponse.getStudent().getLearningSubjects().isEmpty()) {
            subjectResponses= studentResponse.getStudent().getLearningSubjects()
                    .stream()
                    .map(SubjectResponse::new)
                    .toList();
        }
        return subjectResponses;
    }
}
