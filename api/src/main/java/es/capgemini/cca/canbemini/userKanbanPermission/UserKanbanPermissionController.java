package es.capgemini.cca.canbemini.userKanbanPermission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//es un controlador de Spring que maneja solicitudes HTTP para la entidad UserKanbanPermission
@RequestMapping(value = "/api/ukp/")
@RestController
@CrossOrigin(origins = "*")
public class UserKanbanPermissionController {

    @Autowired
    UserKanbanPermissionService ukpService;

    /*Este método permite agregar un usuario a un kanban con un permiso específico.
    Recibe dos parámetros de ruta: el id del kanban y el id del usuario.
    El permiso se establece como 3L de manera predeterminada.
     */
    @RequestMapping(path = { "{kanbanId}/{userId}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "kanbanId") Long kanbanId, @PathVariable(name = "userId") Long userId) {
        ukpService.newUserInKanban(userId, kanbanId, 3L);
    }
    /* Este método permite agregar un usuario a un kanban con un permiso específico.
    Recibe tres parámetros de ruta: el id del kanban, el id del usuario y el id del permiso.
    Hacen lo mismo, cambia la ruta REVISAR
     */
    @RequestMapping(path = { "save/{kanbanId}/{userId}/{permissionId}" }, method = RequestMethod.PUT)
    public void saveUkp(@PathVariable(name = "kanbanId") Long kanbanId, @PathVariable(name = "userId") Long userId, @PathVariable(name = "permissionId") Long permissionId) {
        ukpService.newUserInKanban(userId, kanbanId, permissionId);
    }
}
