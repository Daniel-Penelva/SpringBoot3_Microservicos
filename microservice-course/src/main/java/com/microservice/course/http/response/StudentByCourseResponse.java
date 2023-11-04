package com.microservice.course.http.response;

import com.microservice.course.dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentByCourseResponse {

    private String courseName;
    private String teacher;

    List<StudentDTO> studentDTOList;

}

/**
 * Vai mapear a resposta que vai ser gerado ao client que está consultando o microserviço. A resposta vai ser devolvido o nome do curso, o professor
 * e uma lista de estudantes DTO (as propriedades name, lastname e email).
 */