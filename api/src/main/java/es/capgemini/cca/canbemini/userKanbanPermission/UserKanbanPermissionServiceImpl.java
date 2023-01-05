package es.capgemini.cca.canbemini.userKanbanPermission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import es.capgemini.cca.canbemini.kanban.Kanban;
import es.capgemini.cca.canbemini.permission.Permission;
import es.capgemini.cca.canbemini.permission.PermissionService;
import es.capgemini.cca.canbemini.security.NotAuthorizedException;
import es.capgemini.cca.canbemini.users.Users;
import es.capgemini.cca.canbemini.users.UsersService;

//es una implementación de la interfaz UserKanbanPermissionService
@Service
public class UserKanbanPermissionServiceImpl implements UserKanbanPermissionService {

    @Autowired
    UserKanbanPermissionRepository userKanbanPermissionRepository;

    @Autowired
    UsersService userService;

    @Autowired
    PermissionService permissionService;

    //devuelve la lista de UKP
    @Override
    public List<UserKanbanPermission> get() {
        return (List<UserKanbanPermission>) this.userKanbanPermissionRepository.findAll();
    }

    /* se utiliza para crear una nueva asociación entre un usuario y un kanban.
    Se debe implementar la parte de editarlo
    */
    @Override
    public void saveUkp(Long id, Long userId, Long kanbanId, Long permissionId) {

        UserKanbanPermission ukp = null;

        if (id == null)
            ukp = new UserKanbanPermission();
        else {
            // falta editar

        }

        Users user = userService.findUsers(userId);
        Permission permission = permissionService.findPermission(permissionId);

        Kanban kanban = new Kanban("", "");
        kanban.setId(kanbanId);
        ukp.setPermission(permission);
        ukp.setUsers(user);
        ukp.setKanban(kanban);

        this.userKanbanPermissionRepository.save(ukp);
    }

    //devuelve el ukp con el ID especificado
    @Override
    public UserKanbanPermission getUkp(Long id) {
        return userKanbanPermissionRepository.findById(id).orElse(null);
    }

    //se utiliza para añadir un usuario a un kanban existente
    @Override
    public void newUserInKanban(Long userId, Long kanbanId, Long permissionId) {

        UserKanbanPermission ukp = this.userKanbanPermissionRepository.findByUserIdAndKanbanId(userId, kanbanId);

        if (ukp == null) {
            ukp = new UserKanbanPermission();
        }

        Kanban kanban = new Kanban("", "");
        kanban.setId(kanbanId);
        Users user = userService.findUsers(userId);
        Permission permission = permissionService.findPermission(permissionId);
        ukp.setPermission(permission);
        ukp.setUsers(user);
        ukp.setKanban(kanban);

        this.userKanbanPermissionRepository.save(ukp);

    }

    //se utiliza para verificar si un usuario tiene permiso para realizar una acción específica en un kanban
    @Override
    public boolean isAuthorized(String permission, Long kanbanId) throws NotAuthorizedException {
        String opc = permission;
        Boolean ok = false;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Users user = this.userService.findByEmail(authentication.getPrincipal().toString());
        Long usersId = user.getId();

        UserKanbanPermission ukp = userKanbanPermissionRepository.findUkpByUserAndKanban(kanbanId, usersId);
        if (ukp != null) {
            switch (opc) {
            case "Owner":
                if (ukp.getPermission().getRol().equals("Owner")) {
                    ok = true;
                }
                break;

            case "Editor":
                if (ukp.getPermission().getRol().equals("Owner") || ukp.getPermission().getRol().equals("Editor")) {
                    ok = true;
                }
                break;

            case "Collaborator":
                if (ukp.getPermission().getRol().equals("Owner") || ukp.getPermission().getRol().equals("Editor")
                        || ukp.getPermission().getRol().equals("Collaborator")) {
                    ok = true;
                }
                break;
            }
            return ok;
        } else {
            throw new NotAuthorizedException("This user is not a member of this kanban!");

        }

    }
}
