package es.capgemini.cca.canbemini.userKanbanPermission;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

//es una interface que extiende de la interface CrudRepository
public interface UserKanbanPermissionRepository extends CrudRepository<UserKanbanPermission, Long> {

    /*permite encontrar un objeto de tipo UserKanbanPermission utilizando el id del usuario
    y el id del kanban como parámetros de búsqueda.
     */
    @Query("select ukp from UserKanbanPermission ukp where ukp.users.id = :userId AND ukp.kanban.id = :kanbanId")
    UserKanbanPermission findByUserIdAndKanbanId(Long userId, Long kanbanId);

    /*Este método permite encontrar un objeto de tipo UserKanbanPermission utilizando el id del usuario
    y el id del kanban como parámetros de búsqueda, exactamente igual que el anterior REVISAR
     */
    @Query("select ukp FROM UserKanbanPermission ukp WHERE ukp.users.id = :userId and ukp.kanban.id = :kanbanId")
    UserKanbanPermission findUkpByUserAndKanban(@Param("kanbanId") Long kanbanId, @Param("userId") Long userId);
}
