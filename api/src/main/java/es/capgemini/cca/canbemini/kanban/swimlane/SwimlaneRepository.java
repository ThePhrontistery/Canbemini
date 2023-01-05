package es.capgemini.cca.canbemini.kanban.swimlane;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

//Repositorio que se utiliza para realizar operaciones de persistencia en la base de datos
public interface SwimlaneRepository extends CrudRepository<Swimlane, Long> {
    @Query("select s from Swimlane s where (s.kanban.id = :kanbanId)") // consulta que recupera todas las Swimlane que
                                                                       // pertenecen a un kanban con un determinado Id
    List<Swimlane> findAll(@Param("kanbanId") Long kanbanId);// @Param permite especificar un parámetro para la
                                                             // consulta, de modo que puede utilizarse en la consulta
                                                             // con el nombre indicado en la anotación
}
