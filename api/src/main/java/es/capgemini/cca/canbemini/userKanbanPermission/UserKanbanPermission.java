package es.capgemini.cca.canbemini.userKanbanPermission;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.capgemini.cca.canbemini.kanban.Kanban;
import es.capgemini.cca.canbemini.permission.Permission;
import es.capgemini.cca.canbemini.users.Users;

//representa la relación entre un usuario, un kanban y un permiso.
@Entity //entidad que se persistirá en la base de datos
@Table(name = "User_kanban_permission") //nombre de la tabla
public class UserKanbanPermission {
    @Id //clave primaria de la tabla User_kanban_permission
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generada por el sistema
    private Long id;

    /*tiene tres atributos de tipo ManyToOne, que representan
    la relación muchos a uno con la clase Users, Kanban y Permission respectivamente.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")//la tabla Users está relacionada con la tabla User_kanban_permission a través de la columna "user_id".
    private Users users;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "kanban_id")// la tabla Kanban está relacionada con la tabla User_kanban_permission a través de la columna "kanban_id".
    private Kanban kanban;

    @ManyToOne
    @JoinColumn(name = "permission_id") //la tabla Permission está relacionada con la tabla User_kanban_permission a través de la columna "permission_id".
    private Permission permission;

    public UserKanbanPermission(Users user, Kanban kanban, Permission permission) {
        this.users = user;
        this.kanban = kanban;
        this.permission = permission;
    }

    protected UserKanbanPermission() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Kanban getKanban() {
        return kanban;
    }

    public void setKanban(Kanban kanban) {
        this.kanban = kanban;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

}
