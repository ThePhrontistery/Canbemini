package es.capgemini.cca.canbemini.userKanbanPermission;

import java.util.List;

import es.capgemini.cca.canbemini.security.NotAuthorizedException;

public interface UserKanbanPermissionService {

    public List<UserKanbanPermission> get();

    // para cuando un usuario crea un kanban nuevo
    public void saveUkp(Long id, Long userId, Long kanbanId, Long permissionId);

    // para cuando se añade un usuario a un kanban ya existente
    public void addUserToUkp(Long id, Long userId, Long kanbanId, Long permissionId);

    public UserKanbanPermission getUkp(Long id);

    public boolean isAuthorized(String permission, Long kanbanId) throws NotAuthorizedException;
}
