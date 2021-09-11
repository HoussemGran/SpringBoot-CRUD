package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepo;

    @Autowired
    public StudentService(StudentRepository studentRepo) {

        this.studentRepo = studentRepo;
    }


    public List<Student> findStudentsByName(String name){
        if(studentRepo.findAllByName(name).isEmpty()){
            throw new IllegalStateException("There is no user with the name "+name);
        }
        return studentRepo.findAllByName(name);
    }

    public List<Student> getStudents() {
        if(studentRepo.findAll().isEmpty()){
            throw new IllegalStateException("There is no users");
        }
        return studentRepo.findAll();
    }

    public void addNewStudent(Student student) {

        Optional<Student> studentOptional = studentRepo.findStudentByEmail(student.getEmail());

        if(studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepo.save(student);
    }

    public void deleteStudent(Long studentId) {

        boolean exists = studentRepo.existsById(studentId);

        if(!exists){
            throw new IllegalStateException("user with id "+studentId+" is not exists");
        }
        studentRepo.deleteById(studentId);
    }


    @Transactional
    public void updateStudent(Long studentID , String name ,String email){

        Student student = studentRepo.findById(studentID)
                .orElseThrow(
                ()-> new IllegalStateException("student not found"));

        if(name != null && name.length()> 0 && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(),email)){

            Optional<Student> studentOptional = studentRepo.findStudentByEmail(email);

            if(studentOptional.isPresent()){
                throw new IllegalStateException("Email taken");
            }
            student.setEmail(email);
        }
    }

}
