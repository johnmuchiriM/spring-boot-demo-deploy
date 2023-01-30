package com.example.demo.student;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// The service class carries all the business logic

// AllArgs and Service to use dependecy injection to enable injection to the controller
@AllArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        // check if email is taken throw error
        studentRepository.save(student);
    }
}
