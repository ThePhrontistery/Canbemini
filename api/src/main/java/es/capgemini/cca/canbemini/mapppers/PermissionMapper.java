package es.capgemini.cca.canbemini.mapppers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.capgemini.cca.canbemini.permission.Permission;
import es.capgemini.cca.canbemini.permission.PermissionDto;

//es un mapeador (mapper) que utiliza la librería MapStruct para realizar conversiones entre tipos de datos
//permite convertir objetos de tipo Permission a objetos de tipo PermissionDto y viceversa.
@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission PermissionDtoToPermission(PermissionDto dto);// convierte un objeto de tipo PermissionDto en un objeto
    // de tipo Permission

    PermissionDto PermissionToPermissionDto(
            Permission permission); /*
                                     * convierte un objeto de tipo Permission en un objeto de tipo PermissionDto
                                     */

    @Mapping(source = "user_kanban_permission", target = "user_kanban_permission", ignore = true) // ignorará
                                                                                                  // user_kanban_permission
    List<PermissionDto> permissionListToPermissionListDto(List<Permission> permissionList); // toma una lista de objetos
                                                                                            // Permission y devuelve una
                                                                                            // lista de objetos
                                                                                            // PermissionDto

}
