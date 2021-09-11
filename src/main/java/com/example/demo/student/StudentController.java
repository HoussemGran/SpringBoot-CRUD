package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {


    @Autowired
    private  StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {

        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){

        return this.studentService.getStudents();
    }

    @GetMapping(path = "{studentName}")
    public List<Student> getAllByName(@PathVariable("studentName") String name){
        return studentService.findStudentsByName(name);
    }


    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        this.studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){

            studentService.updateStudent(studentId,name,email);

        }

}
