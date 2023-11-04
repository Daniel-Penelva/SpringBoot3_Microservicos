package com.microservice.student.persistence;

import com.microservice.student.entities.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    /**
     * Este método busca todos os estudantes que tem o id curso.
     */
    @Query("SELECT s FROM Student s WHERE s.courseId = :idCourse")
    List<Student> findAllStudents(Long idCourse);
}

/**
 * Ou pode fazer assim:
 * List<Student> findAllByCourseId(Long idCourse);
 */