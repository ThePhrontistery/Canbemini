package es.capgemini.cca.canbemini.mapppers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.capgemini.cca.canbemini.kanban.swimlane.note.attachment.Attachment;
import es.capgemini.cca.canbemini.kanban.swimlane.note.attachment.AttachmentDto;

//es un mapeador (mapper) que utiliza la librería MapStruct para realizar conversiones entre tipos de datos
//permite convertir objetos de tipo Attachment a objetos de tipo AttachmentDto y viceversa.
@Mapper(componentModel = "spring", uses = { UserKanbanPermissionMapper.class, UsersMapper.class }) // especifica que
                                                                                                   // utiliza los
                                                                                                   // mapeadores
                                                                                                   // UserKanbanPermissionMapper
                                                                                                   // y UsersMapper.
public interface AttachmentMapper {
    @Mapping(target = "note", ignore = true) // ignora el atributo note
    Attachment AttachmentDtoToAttachment(AttachmentDto dto); // convierte un objeto de tipo AttachmentDto en un objeto
                                                             // de tipo Attachment

    AttachmentDto AttachmentToAttachmentDto(Attachment kanban);// convierte un objeto de tipo Attachment en un objeto de
                                                               // tipo AttachmentDto

    List<AttachmentDto> map(List<Attachment> attachmentList); // convierte listas de objetos de Attachment

    List<Attachment> AttachmentDtoListToAttachmentList(List<AttachmentDto> attachmentList);// convierten listas de
                                                                                           // objetos de AttachmentDto
}