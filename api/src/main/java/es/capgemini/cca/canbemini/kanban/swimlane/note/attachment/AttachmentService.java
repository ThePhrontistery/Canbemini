package es.capgemini.cca.canbemini.kanban.swimlane.note.attachment;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import es.capgemini.cca.canbemini.security.NotAuthorizedException;

public interface AttachmentService {

    List<Attachment> findAttachmentNotes(Long noteId); // recuperar una lista de Attachments asociados a una nota
                                                       // específica

    Attachment findAttachment(Long id);// recuperar un Attachment por id

    void deleteAttachment(Long id); // borrar un Attachment por id

    Attachment saveAttachment(Long noteId, Long id, MultipartFile multipartFile); // guardar un Attachment en la base de
                                                                                  // datos
    // La clase MultipartFile permite la carga de archivos mediante peticiones HTTP

    ResponseEntity<byte[]> downloadFile(Long id); // permite la descarga de archivos

    public Boolean isAuthorized(String permission, Long kanbanId) throws NotAuthorizedException; // Para verificar si un
                                                                                                 // usuario tiene
                                                                                                 // permisos para
                                                                                                 // realizar una
                                                                                                 // determinada acción
                                                                                                 // con un Attachment
                                                                                                 // específico
}
