package es.capgemini.cca.canbemini.permission;

import java.util.List;

/*esta interfaz define un conjunto de métodos que se encargan de llevar 
 * a cabo operaciones relacionadas con los permisos (objetos de tipo Permission) 
 * en la aplicación
 */
public interface PermissionService {
    List<Permission> findAll();// obtiene una lista con todos los permisos almacenados en la aplicación

    Permission findPermission(Long id); // permite obtener un permiso específico a partir de su id.

    void deletePermission(Long id);// permite eliminar un permiso específico a partir de su id.

    void savePermission(Long id, PermissionDto permissionDto); // permite crear o actualizar un permiso en la
                                                               // aplicación.
}
