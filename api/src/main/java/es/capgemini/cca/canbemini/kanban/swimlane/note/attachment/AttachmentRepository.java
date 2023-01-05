package es.capgemini.cca.canbemini.kanban.swimlane.note.attachment;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/*es una interfaz que extiende de CrudRepository y está destinada a ser utilizada para
 *  la manipulación de datos de la entidad Attachment en una base de datos.
 */
public interface AttachmentRepository extends CrudRepository<Attachment, Long> {
    @Query("select a from Attachment a where(a.note.id = :noteId)")
    List<Attachment> findAttachmentNotes(@Param("noteId") Long noteId); // se utiliza para buscar todos los Attachment
                                                                        // que pertenecen a una nota específica
}
