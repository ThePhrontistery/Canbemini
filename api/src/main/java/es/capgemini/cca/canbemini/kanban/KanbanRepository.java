package es.capgemini.cca.canbemini.kanban;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface KanbanRepository extends CrudRepository<Kanban, Long> {

    List<Kanban> findByTitle(String title);

    @Query("select distinct k from Kanban k JOIN UserKanbanPermission ukp ON k.id = ukp.kanban.id WHERE ukp.users.id = :userId")
    List<Kanban> findUserKanbans(@Param("userId") Long userId);

    @Query("select distinct k from Kanban k JOIN UserKanbanPermission ukp ON k.id = ukp.kanban.id WHERE ukp.users.id = :userId and k.id = :kanbanId")
    List<Kanban> findUserKanbanId(@Param("userId") Long userId, @Param("kanbanId") Long kanbanId);

    Kanban findByCode(String code);
}