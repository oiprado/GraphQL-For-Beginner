package com.example.demo.model;

import com.example.demo.request.CreateStudentRequest;
import com.example.demo.response.StudentResponse;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
@Builder(builderMethodName = "internalBuilder")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "student")
    private List<Subject> learningSubjects;


    public static Student.StudentBuilder builder(CreateStudentRequest request) {

        return internalBuilder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail());
    }

}
