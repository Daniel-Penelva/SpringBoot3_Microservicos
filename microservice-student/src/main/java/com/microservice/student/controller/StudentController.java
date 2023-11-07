package com.microservice.student.controller;

import com.microservice.student.entities.Student;
import com.microservice.student.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    // Criar um estudante - http://localhost:8090/api/student/create
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStudent(@RequestBody Student student) {
        studentService.save(student);
    }

    // Buscar todos os estudantes - http://localhost:8090/api/student/all
    @GetMapping("/all")
    public ResponseEntity<?> findAllStudent() {
        return ResponseEntity.ok(studentService.findall());
    }

    // Buscar estudante por id - http://localhost:8090/api/student/search/1
    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findById(id));
    }

    // Buscar estudantes por id do curso - http://localhost:8090/api/student/search-ny-course/2
    @GetMapping("/search-ny-course/{idCourse}")
    public ResponseEntity<?> findByIdCourse(@PathVariable("idCourse") Long idCourse){
        return ResponseEntity.ok(studentService.findByIdCourse(idCourse));
    }

}
