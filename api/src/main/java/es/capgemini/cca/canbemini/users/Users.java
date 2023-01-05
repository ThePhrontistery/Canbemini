package es.capgemini.cca.canbemini.users;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermission;

@Entity// indica que se puede mapear una tabla en una base de datos
@Table(name = "Users") // especifica el nombre de la tabla en la base de datos
public class Users {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)//autogenerada
    private Long id;

    @Column(name = "username") //especifica que se alacenará el email en una columna de la tabla llamaba username
    private String email;

    @Column(name = "password") // se alamacenará el password en la columna correspondiente
    private String password;

    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<UserKanbanPermission> user_kanban_permission;

    public Users(String email, String password) {
        this.email = email;
        this.password = password;
    }

    protected Users() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserKanbanPermission> getUser_kanban_permission() {
        return user_kanban_permission;
    }

    public void setUser_kanban_permission(Set<UserKanbanPermission> user_kanban_permission) {
        this.user_kanban_permission = user_kanban_permission;
    }

}
