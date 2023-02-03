package com.example.demo.student;


import com.example.demo.student.exception.BadRequestException;
import com.example.demo.student.exception.StudentNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// The service class carries all the business logic

// AllArgs and Service to use dependency injection to enable injection to the controller
@AllArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        // check if email is exists
        Boolean existsEmail = studentRepository.selectExistsEmail(student.getEmail());

        if(existsEmail){
            throw new BadRequestException(
                    "Email " + student.getEmail() + " taken"
            );
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        // check if student doesn't exist throw error,

        if(!studentRepository.existsById(studentId)){
            throw new StudentNotFoundException(
                    "Student with " + studentId + " does not exists"
            );
        }
        studentRepository.deleteById(studentId);
    }


}
