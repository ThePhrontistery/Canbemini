package es.capgemini.cca.canbemini.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermission;
import es.capgemini.cca.canbemini.users.Users;

/* es una implementación de la interfaz UserDetails de Spring Security. 
 * Esta interfaz define un modelo de usuario y sus detalles. La implementación 
 * UserDetailsImpl se usa para proporcionar información detallada del usuario a la seguridad de Spring.
 */
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Users user;
    private String ROLE_USER = "ROLE_";

    private Collection<? extends GrantedAuthority> authorities; // son los permisos otorgados al usuario.

    // inicializa una instancia de la clase con un objeto de tipo Users y una
    // colección de objetos de tipo GrantedAuthority
    public UserDetailsImpl(Users user, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    /*
     * para crear una instancia de UserDetailsImpl a partir de un objeto de tipo
     * Users. En este método se recorre la colección de objetos UserKanbanPermission
     * del usuario y se crea una nueva instancia de SimpleGrantedAuthority por cada
     * objeto UserKanbanPermission, utilizando como rol el valor del campo rol del
     * objeto Permission asociado a cada UserKanbanPermission, y como identificador
     * del kanban el valor del campo id del objeto Kanban asociado a cada
     * UserKanbanPermission
     */
    public static UserDetailsImpl build(Users user) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (UserKanbanPermission ukp : user.getUser_kanban_permission()) {
            authorities.add(new SimpleGrantedAuthority(ukp.getPermission().getRol() + ukp.getKanban().getId()));

        }
        return new UserDetailsImpl(user, authorities);
    }

    // obetenr el id
    public Long getId() {
        return user.getId();
    }

    // obtener el email
    public String getEmail() {
        return user.getEmail();
    }

    // obtener el pasword
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // obtiene el username que es el email
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    /*
     * isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired y isEnabled
     * siempre devuelven true, lo que significa que la cuenta del usuario no ha
     * caducado, no está bloqueada, las credenciales no han caducado y la cuenta
     * está habilitada.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /*
     * se usa para comparar dos instancias de UserDetailsImpl y determinar si son
     * iguales. El método comparará los IDs de los usuarios y devolverá true si son
     * iguales, y false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) o;
        return Objects.equals(user.getId(), userDetailsImpl.getId());
    }

    // devuelve la lista de GrantedAuthority para el usuario.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (UserKanbanPermission ukp : user.getUser_kanban_permission()) {
            authorities.add(
                    new SimpleGrantedAuthority(ROLE_USER + ukp.getPermission().getRol() + ukp.getKanban().getId()));

        }
        return authorities;
    }
}