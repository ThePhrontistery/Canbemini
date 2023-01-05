package es.capgemini.cca.canbemini.kanban;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.capgemini.cca.canbemini.permission.Permission;
import es.capgemini.cca.canbemini.permission.PermissionDto;
import es.capgemini.cca.canbemini.permission.PermissionService;
import es.capgemini.cca.canbemini.security.NotAuthorizedException;
import es.capgemini.cca.canbemini.security.UserDetailsImpl;
import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermissionService;
import es.capgemini.cca.canbemini.users.Users;
import es.capgemini.cca.canbemini.users.UsersDto;
import es.capgemini.cca.canbemini.users.UsersService;

//clase de servicio para la entidad Kanban. Implementa a la interfaz KanbanService
@Service
@Transactional
public class KanbanServiceImpl implements KanbanService {

    @Autowired
    KanbanRepository kanbanRepository;

    @Autowired
    UserKanbanPermissionService ukpService;

    @Autowired
    UsersService userService;

    @Autowired
    PermissionService permissionService;

    // Recuperar una lista de Kanbans asociados a un usuario determinado.
    @Override
    public List<Kanban> findUserKanbans() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userService.findByEmail(auth.getPrincipal().toString());
        Long userId = user.getId();
        return (List<Kanban>) this.kanbanRepository.findUserKanbans(userId);
    }

    // Recuperar una lista de Kanbans asociados a un usuario y a un Kanban concreto.
    @Override
    public List<Kanban> findUserKanbanId(Long userId, Long kanbanId) {
        return this.kanbanRepository.findUserKanbanId(userId, kanbanId);
    }

    // Recuperar un Kanban dado su identificador.
    @Override
    public Kanban getKanban(Long id) {
        return this.kanbanRepository.findById(id).orElse(null);
    }

    // Eliminar un Kanban dado su identificador.
    @Override
    public void deleteKanban(Long id) {
        this.kanbanRepository.deleteById(id);
    }

    // Guardar un Kanban nuevo o actualizar uno existente.

    @Override
    public void saveKanban(Long id, KanbanDto kanbanDto, Long userId) {
        Kanban kanban = null;

        UsersDto userDto = new UsersDto();
        PermissionDto permissionDto = new PermissionDto();

        Users user = userService.findUsers(userId);
        Permission permission = permissionService.findPermission(1L);

        userDto.setId(userId);
        userDto.setEmail(user.getEmail());
        permissionDto.setId(1L);
        permissionDto.setRol(permission.getRol());

        if (id == null)
            kanban = new Kanban();
        else
            kanban = this.getKanban(id);

        kanban.setTitle(kanbanDto.getTitle());
        kanban.setDescription(kanbanDto.getDescription());

        kanbanDto.setId(kanban.getId());

        this.kanbanRepository.save(kanban);

        if (id == null) {
            Long kanbanId = kanban.getId();
            this.ukpService.saveUkp(null, userId, kanbanId, 1L);
        }

    }

    // Recuperar un Kanban dado su código.
    @Override
    public Kanban getByCode(String code) {
        return this.kanbanRepository.findByCode(code);
    }

    // Verificar si un usuario tiene permisos de acceso a un Kanban determinado.
    @Override
    public Boolean isAuthorized(String permission, Long kanbanId) throws NotAuthorizedException {
        return this.ukpService.isAuthorized(permission, kanbanId);
    }

    // Verificar si un usuario es el dueño de un Kanban determinado.
    @Override
    public Boolean verifyUser(Long userId, UserDetailsImpl userDetailsImpl) {
        return this.userService.verifyUser(userId, userDetailsImpl);
    }

}
