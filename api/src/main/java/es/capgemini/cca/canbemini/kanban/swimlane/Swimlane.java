package es.capgemini.cca.canbemini.kanban.swimlane;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.capgemini.cca.canbemini.kanban.Kanban;
import es.capgemini.cca.canbemini.kanban.swimlane.note.Note;

@Entity // indica que puede estar mapeada a una tabla de la base de datos
@Table(name = "Swimlane")
public class Swimlane {

    @Id // clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // el valor del id se generará automáticamente
    // @Column significa que cada uno será mapeado a una columna en la tabla
    // Swimlane
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "ord", nullable = false)
    private Long order;

    @JsonIgnore // se utiliza para evitar que el atributo kanban sea serializado al convertir un
                // objeto Swimlane a una representación JSON.
    @ManyToOne // tiene una relación de muchos a uno con la clase Kanban, lo que significa que
               // cada simlane pertenece a un tablero específico.
    @JoinColumn(name = "kanban_id")
    private Kanban kanban;

    @OneToMany(mappedBy = "swimlane", orphanRemoval = true, cascade = CascadeType.PERSIST) // cada swimlane puede tener
                                                                                           // varias notas.
    private List<Note> notes;

    public Swimlane(String title, Kanban kanban, Long order) {
        this.title = title;
        this.kanban = kanban;
        this.order = order;
    }

    // contructor protegido, cuando se crea una nueva instancia de Swimlane, el
    // valor de order será 1 de manera predeterminada.
    protected Swimlane() {
        this.order = 1L;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Kanban getKanban() {
        return kanban;
    }

    public void setKanban(Kanban kanban) {
        this.kanban = kanban;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return String.format("Swimlane[id=%d, title='%s']", id, title);
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }
}
