package com.example.demo.service;

import com.example.demo.model.Address;
import com.example.demo.model.Student;
import com.example.demo.model.Subject;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.request.CreateStudentRequest;
import com.example.demo.response.StudentResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private final AddressRepository addressRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public List<StudentResponse> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(student ->  StudentResponse.builder(student).build())
                .toList();
    }

    public StudentResponse getStudentById(Integer id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        return studentOptional.map(student -> StudentResponse.builder(student).build()).orElse(null);
    }

    public Student create(CreateStudentRequest request) {

        Student student = Student.builder(request).build();

        Address address = new Address();
        address.setCity(request.getCity());
        address.setStreet(request.getStreet());

        addressRepository.save(address);

        student.setAddress(address);
        studentRepository.save(student);

        if(!request.getSubjectsLearning().isEmpty()) {

            List<Subject> subjects = request.getSubjectsLearning()
                    .stream()
                    .map(createSubjectRequest -> Subject.builder(createSubjectRequest)
                            .student(student)
                            .build())
                    .toList();
            subjectRepository.saveAll(subjects);
        }

        return student;
    }

}
