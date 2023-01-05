package es.capgemini.cca.canbemini.kanban.swimlane.note;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.capgemini.cca.canbemini.kanban.swimlane.SwimlaneDto;
import es.capgemini.cca.canbemini.kanban.swimlane.note.attachment.AttachmentDto;

/*Es una clase que representa un objeto "nota" en forma de objeto Java. 
Se utiliza para transferir información entre componentes de la aplicación, 
como la base de datos y la interfaz de usuario.*/
public class NoteDto {

    private Long id;

    private String content;

    private Long order;

    private Set<AttachmentDto> attachment;

    @JsonIgnore // como hemos dicho en otras clases, cuando se convierta un objeto de tipo
                // NoteDto a una cadena JSON, la propiedad swimlane no se incluirá en la cadena
                // resultante.
    private SwimlaneDto swimlane;

    public NoteDto(String content, SwimlaneDto swimlane) {
        this.content = content;
        this.swimlane = swimlane;
    }

    protected NoteDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<AttachmentDto> getAttachment() {
        return attachment;
    }

    public void setAttachment(Set<AttachmentDto> attachment) {
        this.attachment = attachment;
    }

    public SwimlaneDto getSwimlane() {
        return swimlane;
    }

    public void setSwimlane(SwimlaneDto swimlane) {
        this.swimlane = swimlane;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

}