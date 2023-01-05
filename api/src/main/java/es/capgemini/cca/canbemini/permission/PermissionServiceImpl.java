package es.capgemini.cca.canbemini.permission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//es una implementación de la interfaz PermissionService
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    // recupera una lista de todos los objetos de tipo Permission de la base de
    // datos.
    @Override
    public List<Permission> findAll() {
        return (List<Permission>) this.permissionRepository.findAll();
    }

    // recupera un objeto de tipo Permission con el id especificado
    @Override
    public Permission findPermission(Long id) {
        return this.permissionRepository.findById(id).orElse(null);
    }

    // elimina un objeto de tipo Permission con el id especificado de la base de
    // datos.
    @Override
    public void deletePermission(Long id) {
        this.permissionRepository.deleteById(id);

    }

    // guarda o actualiza un objeto de tipo Permission en la base de datos
    @Override
    public void savePermission(Long id, PermissionDto permissionDto) {
        Permission permission = null;

        if (id == null)
            permission = new Permission();
        else
            permission = this.findPermission(id);

        permission.setRol(permissionDto.getRol());

        this.permissionRepository.save(permission);

    }

}
