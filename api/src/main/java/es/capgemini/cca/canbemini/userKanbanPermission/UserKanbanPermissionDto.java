package es.capgemini.cca.canbemini.userKanbanPermission;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.capgemini.cca.canbemini.kanban.KanbanDto;
import es.capgemini.cca.canbemini.permission.PermissionDto;
import es.capgemini.cca.canbemini.users.UsersDto;

//representa un objeto que se usa para intercambiar información entre la aplicación y la base de datos
public class UserKanbanPermissionDto {
    private Long id; //Es la clave primaria del objeto y se utiliza para identificar de manera única a un objeto de este tipo en la base de datos.

    private UsersDto users; // Es un objeto de tipo UsersDto que representa a un usuario.

    @JsonIgnore //excluye la propiedad "kanban" de la serialización y deserialización en JSON.
    private KanbanDto kanban; //Es un objeto de tipo KanbanDto que representa a un kanban.

    private PermissionDto permission; //Es un objeto de tipo PermissionDto que representa a un permiso.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsersDto getUsers() {
        return users;
    }

    public void setUsers(UsersDto users) {
        this.users = users;
    }

    public KanbanDto getKanban() {
        return kanban;
    }

    public void setKanban(KanbanDto kanban) {
        this.kanban = kanban;
    }

    public PermissionDto getPermission() {
        return permission;
    }

    public void setPermission(PermissionDto permission) {
        this.permission = permission;
    }
}