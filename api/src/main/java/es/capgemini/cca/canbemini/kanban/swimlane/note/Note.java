package es.capgemini.cca.canbemini.kanban.swimlane.note;

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

import es.capgemini.cca.canbemini.kanban.swimlane.Swimlane;
import es.capgemini.cca.canbemini.kanban.swimlane.note.attachment.Attachment;

/*Representa una nota en la aplicación. Una nota es una entidad que pertenece 
 * a una simlane específica en un tablero Kanban y puede tener contenido y attachment.
 */
@Entity // entidad de base de datos
@Table(name = "Note")
public class Note {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autogenerada
    @Column(name = "id", nullable = false) // especifica que se alacenará en una columna de la tabla "Note"
    private Long id;

    @Column(name = "content", nullable = false, length = 5000)
    private String content;

    /*
     * Se define una propiedad attachment de tipo List<Attachment> para almacenar
     * los archivos adjuntos de la nota. Se le añade la anotación @OneToMany para
     * indicar que hay una relación de uno a muchos entre las entidades Note y
     * Attachment. También se le añade @JsonIgnore para evitar una recursión
     * infinita al serializar la entidad a un formato como JSON.
     */
    @OneToMany(mappedBy = "note", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<Attachment> attachment;

    //
    @Column(name = "ord", nullable = false)
    private Long order;

    @JsonIgnore /*
                 * se utiliza para evitar que el atributo swimlane sea serializado al convertir
                 * un objeto note a una representación JSON.
                 */
    @ManyToOne // para indicar que hay una relación de muchos a uno entre las entidades Note y
               // Swimlane
    @JoinColumn(name = "swimlane_id")
    private Swimlane swimlane;

    public Note(String content, Swimlane swimlane, Long order) {
        this.content = content;
        this.swimlane = swimlane;
        this.order = order;
    }

    /*
     * se utiliza para inicializar el atributo order con el valor 1L cuando se
     * instancie una nueva nota. El valor de order se utilice para determinar el
     * orden en que se muestran las notas en la interfaz de usuario
     */

    protected Note() {
        this.order = 1l;
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

    public Swimlane getSwimlane() {
        return swimlane;
    }

    public void setSwimlane(Swimlane swimlane) {
        this.swimlane = swimlane;
    }

    public List<Attachment> getAttachment() {
        return attachment;
    }

    public void setAttachment(List<Attachment> attachment) {
        this.attachment = attachment;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

}