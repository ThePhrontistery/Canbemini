package es.capgemini.cca.canbemini.userKanbanPermission;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserKanbanPermissionRepository extends CrudRepository<UserKanbanPermission, Long> {

    @Query("select ukp FROM UserKanbanPermission ukp WHERE ukp.users.id = :userId and ukp.kanban.id = :kanbanId")
    UserKanbanPermission findUkpByUserAndKanban(@Param("kanbanId") Long kanbanId, @Param("userId") Long userId);
}
