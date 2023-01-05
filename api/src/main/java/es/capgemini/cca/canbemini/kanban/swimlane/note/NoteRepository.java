package es.capgemini.cca.canbemini.kanban.swimlane.note;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/*es una interfaz que extiende de CrudRepository y se utiliza para realizar 
 * operaciones de persistencia de datos en una base de datos relacional, explicado
 * en KanbanRepository*/

public interface NoteRepository extends CrudRepository<Note, Long> {

    /*
     * es un método personalizado que utiliza la anotación @Query para especificar
     * una consulta personalizada que se debe ejecutar para recuperar todas las
     * notas asociadas a una swimlane específica. La consulta se ejecuta utilizando
     * el parámetro swimlaneId que se proporciona como entrada al método. El
     * resultado de la consulta se devuelve como una lista de objetos Note.
     */
    @Query("select n from Note n where (n.swimlane.id = :swimlaneId)")
    List<Note> findAllSwimlaneNotes(@Param("swimlaneId") Long swimlaneId);

}
