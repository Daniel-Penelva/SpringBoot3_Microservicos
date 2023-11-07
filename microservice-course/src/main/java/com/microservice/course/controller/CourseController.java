package com.microservice.course.controller;

import com.microservice.course.entities.Course;
import com.microservice.course.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    // Criar um curso - http://localhost:9090/api/course/create
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCourse(@RequestBody Course course) {
        courseService.save(course);
    }

    // Buscar todos os cursos - http://localhost:9090/api/course/all
    @GetMapping("/all")
    public ResponseEntity<?> findAllCourse() {
        return ResponseEntity.ok(courseService.findAll());
    }

    // Buscar um curso por id - http://localhost:9090/api/course/search/2
    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.findById(id));
    }

   // Buscar estudantes em relação ao id de um curso - http://localhost:9090/api/course/search-student/2
    @GetMapping("search-student/{idCourse}")
    public ResponseEntity<?> findStudentsByIdCourse(@PathVariable("idCourse") Long idCourse){
        return ResponseEntity.ok(courseService.findStudentsByIdCourse(idCourse));
    }
}
