package es.capgemini.cca.canbemini.mapppers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.capgemini.cca.canbemini.kanban.swimlane.Swimlane;
import es.capgemini.cca.canbemini.kanban.swimlane.SwimlaneDto;

//es un mapeador (mapper) que utiliza la librería MapStruct para realizar conversiones entre tipos de datos
//permite convertir objetos de tipo Swimlane a objetos de tipo SwimlaneDto y viceversa.
@Mapper(componentModel = "spring", uses = { UserKanbanPermissionMapper.class, PermissionMapper.class, UsersMapper.class,
        AttachmentMapper.class, NoteMapper.class }) // especifica que utiliza esos mapeadores
public interface SwimlaneMapper {
    // los mappings especifican que ignorarán el atributo Kanban
    @Mapping(target = "kanban", ignore = true)
    Swimlane SwimlaneDtoToSwimlane(SwimlaneDto dto); // convierte un objeto de tipo SwimlaneDto en un objeto
    // de tipo Swimlane

    @Mapping(target = "kanban", ignore = true)
    SwimlaneDto SwimlaneToSwimlaneDto(Swimlane swimlane);// convierte un objeto de tipo Swimlane en un objeto de
    // tipo SwimlaneDto

    /*
     * Los métodos swimlaneListDtoToSwimlaneList y swimlaneListToSwimlaneListDto son
     * métodos que se encargan de realizar la conversión entre una lista de objetos
     * Swimlane y una lista de objetos SwimlaneDto, y viceversa.
     */
    List<Swimlane> swimlaneListDtoToSwimlaneList(List<SwimlaneDto> swimlaneListDto);

    List<SwimlaneDto> swimlaneListToSwimlaneListDto(List<Swimlane> swimlaneList);
}
