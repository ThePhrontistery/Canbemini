package es.capgemini.cca.canbemini.permission;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermission;

//representa una entidad de base de datos que almacena información sobre los permisos.
@Entity
@Table(name = "Permission")
public class Permission {
    @Id // un identificador único para cada permiso.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // generado automáticamente por la base de datos al momento de
                                                        // crear un nuevo permiso
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "rol", nullable = false, unique = true)
    private String rol;// el nombre del permiso. Debe ser único y no puede ser nulo.

    @OneToMany(mappedBy = "permission") /*
                                         * establecer una relación de uno a muchos entre dos entidades. mappedBy indica
                                         * que la relación es propiedad de la entidad UserKanbanPermission. Esto
                                         * significa que la entidad UserKanbanPermission contiene una columna que se
                                         * refiere a la entidad Permission.
                                         */

    @JsonIgnore // cuando se convierta la entidad Permission a JSON, el atributo
                // user_kanban_permission no se incluirá en el objeto resultante
    private Set<UserKanbanPermission> user_kanban_permission;/*
                                                              * un conjunto de asociaciones entre permisos y usuarios.
                                                              * Cada elemento de este conjunto representa una asociación
                                                              * entre un permiso y un usuario en un determinado kanban.
                                                              */

    public Permission(String role) {
        this.rol = role;
    }

    protected Permission() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Set<UserKanbanPermission> getUser_kanban_permission() {
        return user_kanban_permission;
    }

    public void setUser_kanban_permission(Set<UserKanbanPermission> user_kanban_permission) {
        this.user_kanban_permission = user_kanban_permission;
    }

}
