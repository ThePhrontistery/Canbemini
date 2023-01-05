package es.capgemini.cca.canbemini.mapppers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermission;
import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermissionDto;

//es un mapeador (mapper) que utiliza la librería MapStruct para realizar conversiones entre tipos de datos
//permite convertir objetos de tipo UserKanbanPermission a objetos de tipo UserKanbanPermisionDto y viceversa.
@Mapper(componentModel = "spring", uses = { SwimlaneMapper.class, NoteMapper.class, PermissionMapper.class,
        UsersMapper.class, AttachmentMapper.class }) // utilizará esos mapeadores para la conversión

public interface UserKanbanPermissionMapper {

    // Los mappings especifican que se ignorará el atributo kanban
    @Mapping(target = "kanban", ignore = true)
    @Mapping(source = "users", target = "user")//salía un warning que pedia mapear user
    UserKanbanPermission userKanbanPermissionDtoToUserKanbanPermission(
            UserKanbanPermissionDto userKanbanPermissionDto);/*
                                                              * convierte un objeto de tipo UsserKanbanPermissionDto en
                                                              * un objeto de tipo UserKanbanPermision
                                                              */

    @Mapping(target = "kanban", ignore = true)
    UserKanbanPermissionDto userKanbanPermissionToUserKanbanPermissionDto(
            UserKanbanPermission userKanbanPermission);/*
                                                        * convierte un objeto de tipo UserKanbanPermission en un objeto
                                                        * de tipo UserKanbanPermissionDto
                                                        */

    /*
     * userKanbanPermissionListDtoToUserKanbanPermissionList y
     * userKanbanPermissionListToUserKanbanPermissionListDto son métodos que se
     * encargan de realizar la conversión entre una lista de objetos
     * UserKanbanPermission y una lista de objetos UserKanbanPermissionDto, y
     * viceversa.
     */
    List<UserKanbanPermission> userKanbanPermissionListDtoToUserKanbanPermissionList(
            List<UserKanbanPermissionDto> kanbanListDto);

    List<UserKanbanPermissionDto> userKanbanPermissionListToUserKanbanPermissionListDto(
            List<UserKanbanPermission> kanbanList);
}
