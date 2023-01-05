package es.capgemini.cca.canbemini.mapppers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.capgemini.cca.canbemini.kanban.swimlane.note.Note;
import es.capgemini.cca.canbemini.kanban.swimlane.note.NoteDto;

//es un mapeador (mapper) que utiliza la librería MapStruct para realizar conversiones entre tipos de datos
//permite convertir objetos de tipo Note a objetos de tipo NoteDto y viceversa.
@Mapper(componentModel = "spring", uses = { PermissionMapper.class, UsersMapper.class, AttachmentMapper.class })
public interface NoteMapper {

    /*
     * con estos @Mapping se está especificando que la propiedad swimlane del objeto
     * Note debe ser ignorada al realizar la conversión desde un objeto NoteDto.
     */
    @Mapping(target = "swimlane", ignore = true)
    Note noteDtoToNote(NoteDto dto);// convierte un objeto de tipo NoteDto en un objeto
    // de tipo Note

    @Mapping(target = "swimlane", ignore = true)
    NoteDto noteToNoteDto(Note note);// convierte un objeto de tipo Note en un objeto de
    // tipo NoteDto

    List<NoteDto> map(List<Note> noteList);// Convierte listas de objetos de Note

}
