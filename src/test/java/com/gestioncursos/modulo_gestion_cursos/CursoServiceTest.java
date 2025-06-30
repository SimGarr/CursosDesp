package com.gestioncursos.modulo_gestion_cursos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gestioncursos.main.model.Curso;
import com.gestioncursos.main.model.entity.CursoEntity;
import com.gestioncursos.main.repository.CursoRepository;
import com.gestioncursos.main.service.CursoService;

@ExtendWith(MockitoExtension.class)
class CursoServiceTest {
	
	@Mock
	private CursoRepository cursoRepository;
	@InjectMocks
	private CursoService cursoService;

	// Aquí puedes agregar los métodos de prueba para CursoService
	// Por ejemplo, podrías probar métodos como crearCurso, obtenerCursoPorId, etc.
	// Asegúrate de usar Mockito para simular el comportamiento del repositorio y verificar interacciones.
	// Ejemplo de un método de prueba:

	@Test
	void testCrearCurso() {
		// Arrange
		CursoEntity cursoEntity = new CursoEntity();
		cursoEntity.setIdCurso("123");
		when(cursoRepository.save(any(CursoEntity.class))).thenReturn(cursoEntity);

		// Map CursoEntity to Curso (assuming a simple mapping for test)
		Curso curso = new Curso();
		curso.setIdCurso("123");

		// Act
		String resultado = cursoService.crearCurso(curso);

		// Assert
		assertNotNull(resultado);
		assertEquals("Curso creado correctamente", resultado);
		verify(cursoRepository, times(1)).save(any(CursoEntity.class));
	}
	@Test
	void testObtenerCurso() {
		// Arrange
		CursoEntity cursoEntity = new CursoEntity();
		cursoEntity.setIdCurso("123");
		cursoEntity.setNombre("Curso de Prueba");
		when(cursoRepository.findByIdCurso("123")).thenReturn(cursoEntity);

		// Act
		Curso curso = cursoService.obtenerCurso("123");

		// Assert
		assertNotNull(curso);
		assertEquals("123", curso.getIdCurso());
		assertEquals("Curso de Prueba", curso.getNombre());
		verify(cursoRepository, times(1)).findByIdCurso("123");
	}
	@Test
	void testModificarCurso() {
		// Arrange
		CursoEntity cursoEntity = new CursoEntity();
		cursoEntity.setIdCurso("123");
		cursoEntity.setNombre("Curso Antiguo");
		when(cursoRepository.findByIdCurso("123")).thenReturn(cursoEntity);

		Curso cursoModificado = new Curso();
		cursoModificado.setIdCurso("123");
		cursoModificado.setNombre("Curso Modificado");

		// Act
		String resultado = cursoService.modificarCurso(cursoModificado);

		// Assert
		assertNotNull(resultado);
		assertEquals("Curso modificado correctamente", resultado);
		verify(cursoRepository, times(1)).findByIdCurso("123");
		verify(cursoRepository, times(1)).save(any(CursoEntity.class));
	}
	@Test
	void testListarCursos() {
		// Arrange
		CursoEntity cursoEntity = new CursoEntity();
		cursoEntity.setIdCurso("123");
		cursoEntity.setNombre("Curso de Prueba");
		cursoEntity.setDescripcion("descripcion");
		cursoEntity.setFechaInicio(LocalDate.of(2025, 1, 1));
		cursoEntity.setFechaFin(LocalDate.of(2025, 12, 31));
		when(cursoRepository.findAll()).thenReturn(java.util.Collections.singletonList(cursoEntity));

		// Act
		String resultado = cursoService.listarCursos();

		// Assert
		assertNotNull(resultado);
		assertEquals("ID: 123, Nombre: Curso de Prueba, Descripcion: descripcion, Fecha Inicio: 2025-01-01, Fecha Fin: 2025-12-31", resultado.trim());
		verify(cursoRepository, times(1)).findAll();
	}
	@Test
	void testEliminarCurso() {
		// Arrange
		CursoEntity cursoEntity = new CursoEntity();
		cursoEntity.setIdCurso("123");
		when(cursoRepository.findByIdCurso("123")).thenReturn(cursoEntity);

		// Act
		String resultado = cursoService.eliminarCurso("123");

		// Assert
		assertNotNull(resultado);
		assertEquals("Curso eliminado correctamente", resultado);
		verify(cursoRepository, times(1)).findByIdCurso("123");
		verify(cursoRepository, times(1)).delete(cursoEntity);
	}
	@Test
	void testCrearCursoConFecha() {
		// Arrange
		CursoEntity cursoEntity = new CursoEntity();
		cursoEntity.setIdCurso("123");
		cursoEntity.setFechaInicio(LocalDate.of(2025, 1, 1));
		cursoEntity.setFechaFin(LocalDate.of(2025, 12, 31));
		when(cursoRepository.save(any(CursoEntity.class))).thenReturn(cursoEntity);

		Curso curso = new Curso();
		curso.setIdCurso("123");
		curso.setFechaInicio(LocalDate.of(2025, 1, 1));
		curso.setFechaFin(LocalDate.of(2025, 12, 31));

		// Act
		String resultado = cursoService.crearCurso(curso);

		// Assert
		assertNotNull(resultado);
		assertEquals("Curso creado correctamente", resultado);
		verify(cursoRepository, times(1)).save(any(CursoEntity.class));
	}
}
