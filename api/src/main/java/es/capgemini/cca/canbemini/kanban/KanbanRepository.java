package es.capgemini.cca.canbemini.kanban;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/*es un repositorio que se encarga de realizar operaciones de base de datos sobre el modelo de datos 
mplementa la interfaz CrudRepository, que proporciona métodos básicos para realizar operaciones CRUD 
(create, read, update, delete) sobre una entidad en la base de datos.*/
public interface KanbanRepository extends CrudRepository<Kanban, Long> {

    List<Kanban> findByTitle(String title); // devuelve todos los objetos kanban.

    @Query("select distinct k from Kanban k JOIN UserKanbanPermission ukp ON k.id = ukp.kanban.id WHERE ukp.users.id = :userId")
    List<Kanban> findUserKanbans(@Param("userId") Long userId);
    /*
     * Devuelve todos los objetos Kanban a los que tenga acceso un usuario
     * especificado por su identificador. Esto se realiza mediante una consulta que
     * une la tabla Kanban con la tabla UserKanbanPermission en base al campo
     * kanban.id y filtra por el identificador del usuario proporcionado como
     * parámetro.
     */

    @Query("select distinct k from Kanban k JOIN UserKanbanPermission ukp ON k.id = ukp.kanban.id WHERE ukp.users.id = :userId and k.id = :kanbanId")
    List<Kanban> findUserKanbanId(@Param("userId") Long userId, @Param("kanbanId") Long kanbanId);
    /*
     * Devuelve todos los objetos Kanban a los que tenga acceso un usuario
     * especificado por su identificador y que tengan un identificador especificado
     * como parámetro. Esto se realiza mediante una consulta que une la tabla Kanban
     * con la tabla UserKanbanPermission en base al campo kanban.id y filtra por el
     * identificador del usuario y del kanban proporcionados como parámetros.
     */

    Kanban findByCode(String code);// devuelve el objeto Kanban que tenga un código especificado como parámetro
}