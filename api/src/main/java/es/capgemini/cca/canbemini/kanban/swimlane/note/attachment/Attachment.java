package es.capgemini.cca.canbemini.kanban.swimlane.note.attachment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.capgemini.cca.canbemini.kanban.swimlane.note.Note;

@Entity // indica que se puede mapear una tabla en una base de datos
@Table(name = "Attachment") // nombre de la tabla
//Los atributos con @column indica que se puede mapear a columnas en la tabla
public class Attachment {
    @Id // el atributo es la clase primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // será generado por la base de datos
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "document_path")
    private String document_path;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @JsonIgnore // Note no se incluirá en la representación JSON del objeto Attachment ni se
                // asignará al objeto Attachment durante la deserialización.
    @ManyToOne // indica que hay una relación muchos-a-uno entre la clase Attachment y la clase
               // Note.
    @JoinColumn(name = "note_id")
    private Note note;

    @Lob // indica que el atributo file es un objeto grande, como un archivo, que se
         // almacenará en una columna de tipo Large Object en la base de datos.
    @JsonIgnore
    private byte[] file;

    public Attachment(Note note, String document_path) {
        this.note = note;
        this.document_path = document_path;
    }

    protected Attachment() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocument_path() {
        return document_path;
    }

    public void setDocument_path(String document_path) {
        this.document_path = document_path;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}