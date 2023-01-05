package es.capgemini.cca.canbemini.kanban.swimlane;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.capgemini.cca.canbemini.kanban.KanbanDto;
import es.capgemini.cca.canbemini.kanban.swimlane.note.NoteDto;

//Representa a un objeto de transferencia de datos (DTO) para la entidad Swimlane
public class SwimlaneDto {
    private Long id;// identificador del swimlane

    @JsonIgnore // indica que kanban no se incluirá en la serialización a formato JSON. Es como
                // evitamos los bucles infinitos
    private KanbanDto kanban;// representa el objeto Kanban al que pertenece el Swimlane

    private Long order;

    private List<NoteDto> notes;// lista de notas que perftenecen al swimlane

    private String title;

    public SwimlaneDto(String title) {
        this.title = title;
    }

    protected SwimlaneDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public KanbanDto getKanban() {
        return kanban;
    }

    public void setKanban(KanbanDto kanban) {
        this.kanban = kanban;
    }

    public List<NoteDto> getNotes() {
        notes.sort((a, a1) -> a.getOrder().compareTo(a1.getOrder()));
        return notes;
    }

    public void setNotes(List<NoteDto> notes) {
        this.notes = notes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

}
