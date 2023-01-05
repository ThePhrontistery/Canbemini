package es.capgemini.cca.canbemini.mapppers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.capgemini.cca.canbemini.users.Users;
import es.capgemini.cca.canbemini.users.UsersDto;

/*es un mapeador (mapper) que utiliza la librería MapStruct para realizar conversiones entre tipos de datos
permite convertir objetos de tipo Users a objetos de tipo UsersDto y viceversa.
*/
@Mapper(componentModel = "spring", uses = { SwimlaneMapper.class, NoteMapper.class, AttachmentMapper.class,
        PermissionMapper.class }) // utilizará esos mappers
public interface UsersMapper {
    // @Mapping indica que se ignorará el atributo user_kanban_permision
    @Mapping(target = "user_kanban_permission", ignore = true)
    Users UsersDtoToUsers(UsersDto dto); /*
                                          * convierte un objeto de tipo UsersDto en un objeto de tipo Users
                                          */

    @Mapping(target = "user_kanban_permission", ignore = true)
    UsersDto UsersToUsersDto(Users users);/*
                                           * convierte un objeto de tipo Users en un objeto de tipo UsersDto
                                           */
    /*
     * Los métodos usersListToUsersListDto y usersListDtoToUsersList son métodos que
     * se encargan de realizar la conversión entre una lista de objetos Swimlane y
     * una lista de objetos SwimlaneDto, y viceversa.
     */

    List<UsersDto> usersListToUsersListDto(List<Users> usersList);

    List<Users> usersListDtoToUsersList(List<UsersDto> usersListDto);

}
