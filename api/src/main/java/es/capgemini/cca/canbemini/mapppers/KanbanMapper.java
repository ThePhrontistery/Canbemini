package es.capgemini.cca.canbemini.mapppers;

import java.util.List;

import org.mapstruct.Mapper;

import es.capgemini.cca.canbemini.kanban.Kanban;
import es.capgemini.cca.canbemini.kanban.KanbanDto;

//es un mapeador (mapper) que utiliza la librería MapStruct para realizar conversiones entre tipos de datos
//permite convertir objetos de tipo Kanban a objetos de tipo KanbanDto y viceversa.
@Mapper(componentModel = "spring", uses = { UserKanbanPermissionMapper.class, SwimlaneMapper.class, NoteMapper.class,
        UsersMapper.class, AttachmentMapper.class }) // utiliza todos esos mapeadores para la conversion
public interface KanbanMapper {

    Kanban KanbanDtoToKanban(KanbanDto dto);// convierte un objeto de tipo KanbanDto en un objeto
    // de tipo Kanban

    KanbanDto KanbanToKanbanDto(Kanban kanban);// convierte un objeto de tipo Kanban en un objeto de
    // tipo KanbanDto

    List<KanbanDto> KanbanListToKanbaListDto(List<Kanban> kanbanList); // convierten listas de
    // objetos de KanbanDto

    List<Kanban> KanbanListDtoToKanbaList(List<KanbanDto> kanbanListDto); // convierte listas de objetos de Kanban

}
