package com.gestioncursos.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestioncursos.main.model.Curso;
import com.gestioncursos.main.model.dto.CursoDto;
import com.gestioncursos.main.service.CursoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private CursoService cursoService;
    
    @Operation(summary = "Este endpoint permite crear un curso")
    @PostMapping("/crearCurso")
    public ResponseEntity<String> ObtenerCurso(@RequestBody Curso curso) {
        return ResponseEntity.ok(cursoService.crearCurso(curso));
    }

    @Operation(summary = "Este endpoint permite obtener un curso por su id de curso")
    @GetMapping("/obtenerCurso/{idCurso}")
    public ResponseEntity<Curso> obtenerCurso(@PathVariable String idCurso) {
        Curso curso = cursoService.obtenerCurso(idCurso);
        if (curso != null) {
            return ResponseEntity.ok(curso);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Este endpoint permite modificar un curso")
    @PostMapping("/modificarCurso")
    public ResponseEntity<String> modificarCurso(@RequestBody Curso curso) {
        return ResponseEntity.ok(cursoService.modificarCurso(curso));
    }

    @Operation(summary = "Este endpoint permite listar todos los cursos")
    @GetMapping("/listarCursos")
    public ResponseEntity<String> listarCursos() {
        return ResponseEntity.ok(cursoService.listarCursos());
    }

    @Operation(summary = "Este endpoint permite eliminar un curso por su id de curso")
    @PostMapping("/eliminarCurso/{idCurso}")
    public ResponseEntity<String> eliminarCurso(@PathVariable String idCurso) {
        return ResponseEntity.ok(cursoService.eliminarCurso(idCurso));
    }

    @Operation(summary = "Este endpoint permite obtener un curso DTO por su id de curso")
    @GetMapping("/obtenerCursoDTO/{idCurso}")
    public ResponseEntity<CursoDto> obtenerCursoDTO(@PathVariable String idCurso) {
        if (cursoService.obtenerCursoDto(idCurso) != null) {
            return ResponseEntity.ok(cursoService.obtenerCursoDto(idCurso));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
