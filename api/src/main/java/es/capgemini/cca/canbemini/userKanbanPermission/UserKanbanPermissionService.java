package es.capgemini.cca.canbemini.userKanbanPermission;

import java.util.List;

import es.capgemini.cca.canbemini.security.NotAuthorizedException;

//es una interface que define un conjunto de métodos
public interface UserKanbanPermissionService {

    public List<UserKanbanPermission> get(); //devuelve una lista con todos los objetos UserKanbanPermission.

    //guarda un nuevo objeto UserKanbanPermission en el sistema con los parámetros especificados.
    public void saveUkp(Long id, Long userId, Long kanbanId, Long permissionId);

    //devuelve el objeto UserKanbanPermission con el identificador especificado
    public UserKanbanPermission getUkp(Long id);

    //agrega un nuevo usuario a un kanban ya existente
    public void newUserInKanban(Long userId, Long kanbanId, Long permissionId);

    /*verifica si el usuario actual tiene permisos para realizar una acción específica
    en el kanban especificado. Si no tiene permisos, lanza una excepción
     */
    public boolean isAuthorized(String permission, Long kanbanId) throws NotAuthorizedException;
}
