package com.gestioncursos.main.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestioncursos.main.model.Curso;
import com.gestioncursos.main.model.dto.CursoDto;
import com.gestioncursos.main.model.entity.CursoEntity;
import com.gestioncursos.main.repository.CursoRepository;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    
    public String crearCurso(Curso curso){

        try{
            Boolean estado = cursoRepository.existsByIdCurso(curso.getIdCurso());
            if (!estado){
                CursoEntity cursoNuevo = new CursoEntity();
                cursoNuevo.setIdCurso(curso.getIdCurso());
                cursoNuevo.setNombre(curso.getNombre());
                cursoNuevo.setDescripcion(curso.getDescripcion());
                cursoNuevo.setFechaInicio(curso.getFechaInicio());
                cursoNuevo.setFechaFin(curso.getFechaFin());
                cursoRepository.save(cursoNuevo);
                return "Curso creado correctamente";
            }
                return "El curso ya existe";
            }
        catch (Exception e){
            return "Error al crear el curso: " + e.getMessage();
        }
    }

    public Curso obtenerCurso(String idCurso){
        try{
            CursoEntity curso = cursoRepository.findByIdCurso(idCurso);
            if (curso != null){
                Curso cursoRead = new Curso();
                cursoRead.setIdCurso(curso.getIdCurso());
                cursoRead.setNombre(curso.getNombre());
                cursoRead.setDescripcion(curso.getDescripcion());
                cursoRead.setFechaInicio(curso.getFechaInicio());
                cursoRead.setFechaFin(curso.getFechaFin());
                return cursoRead;
            }
            return null;
        }
        catch (Exception e){
            return null;
        }
    }
    public String modificarCurso(Curso curso){
        try{
            CursoEntity cursoExistente = cursoRepository.findByIdCurso(curso.getIdCurso());
            if (cursoExistente != null){
                cursoExistente.setNombre(curso.getNombre());
                cursoExistente.setDescripcion(curso.getDescripcion());
                cursoExistente.setFechaInicio(curso.getFechaInicio());
                cursoExistente.setFechaFin(curso.getFechaFin());
                cursoRepository.save(cursoExistente);
                return "Curso modificado correctamente";
            }
            return "El curso no existe";
        }
        catch (Exception e){
            return "Error al modificar el curso: " + e.getMessage();
        }
    }
    public String listarCursos(){
        try{
            StringBuilder cursos = new StringBuilder();
            for (CursoEntity curso : cursoRepository.findAll()){
                cursos.append("ID: ").append(curso.getIdCurso()).append(", Nombre: ").append(curso.getNombre()).append(", Descripcion: ").append(curso.getDescripcion()).append(", Fecha Inicio: ").append(curso.getFechaInicio()).append(", Fecha Fin: ").append(curso.getFechaFin()).append("\n");
            }
            return cursos.toString();
        }
        catch (Exception e){
            return "Error al listar los cursos: " + e.getMessage();
        }
    }

    public String eliminarCurso(String idCurso){
        try{
            CursoEntity curso = cursoRepository.findByIdCurso(idCurso);
            if (curso != null){
                cursoRepository.delete(curso);
                return "Curso eliminado correctamente";
            }
            return "El curso no existe";
        }
        catch (Exception e){
            return "Error al eliminar el curso: " + e.getMessage();
        }
    }
    public CursoDto obtenerCursoDto(String idCurso){
        try{
            CursoEntity curso = cursoRepository.findByIdCurso(idCurso);
            CursoDto nuevoCursoDto = new CursoDto(
                curso.getNombre(),
                curso.getDescripcion(),
                curso.getFechaInicio(),
                curso.getFechaFin()
            );
            return nuevoCursoDto;
        }catch (Exception e){
            return null;
            }
    }
}
