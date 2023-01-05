package es.capgemini.cca.canbemini.kanban.swimlane.note.attachment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.capgemini.cca.canbemini.kanban.swimlane.note.NoteService;
import es.capgemini.cca.canbemini.mapppers.AttachmentMapper;

//Se trata del controlador de la clase Atatchemnt

@RequestMapping(value = "/api/kanban/swimlane/note/attachment") // indica que todas las solicitudes a la URL
                                                                // especificada deben ser manejadas por el controlador
@RestController // indica que la clase es un controlador y que los métodos deben devolver datos
                // en lugar de una vista
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*") // permite que la aplicación web pueda ser
                                                                      // accedida desde ese origen
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    NoteService noteService;

    @Autowired
    AttachmentMapper attachmentMapper;

    @Autowired
    AttachmentRepository AttachmentRepository;

    public AttachmentController() {

    }

    // @PreAuthorize: para proteger los métodos y garantizar que solo los usuarios
    // autorizados puedan acceder a ellos

    // getAttatchemnt: Este método recupera un adjunto específico y lo devuelve al
    // cliente
    @RequestMapping(path = "/get/{attachmentId}", method = RequestMethod.GET)
    @PreAuthorize("@attachmentServiceImpl.isAuthorized('Collaborator',#attachmentId)")
    public AttachmentDto getAttachment(@PathVariable("attachmentId") Long attachmentId) {
        return attachmentMapper.AttachmentToAttachmentDto(attachmentService.findAttachment(attachmentId));
    }

    /*
     * se encarga de devolver una lista de objetos AttachmentDto (un DTO es una
     * clase que se utiliza para transferir datos entre distintas capas de una
     * aplicación). La lista se obtiene a través del método findAttachmentNotes del
     * servicio attachmentService, al que se le pasa como parámetro el ID de una
     * nota (noteId).
     */
    @RequestMapping(path = "/{noteId}", method = RequestMethod.GET)
    @PreAuthorize("@noteServiceImpl.isAuthorized('Collaborator',#noteId)")
    public List<AttachmentDto> getAllAttachmentNotes(@PathVariable("noteId") Long noteId) {
        return attachmentMapper.map(attachmentService.findAttachmentNotes(noteId));
    }

    // Guarda un adjunto especifico en una nota
    @RequestMapping(path = { "/{noteId}", "/{noteId}/{attachmentId}" }, method = RequestMethod.PUT)
    @PreAuthorize("@noteServiceImpl.isAuthorized('Editor',#noteId)")
    public Attachment save(@PathVariable(name = "noteId", required = true) Long noteId,
            @PathVariable(name = "attachmentId", required = false) Long attachmentId,
            @RequestParam MultipartFile multipartFile) {
        return attachmentService.saveAttachment(noteId, attachmentId, multipartFile);
    }

    // borra un adjunto específico en una nota
    @RequestMapping(path = "/{attachmentId}", method = RequestMethod.DELETE)
    @PreAuthorize("@attachmentServiceImpl.isAuthorized('Editor',#attachmentId)")
    public void delete(@PathVariable("attachmentId") Long attachmentId) {
        attachmentService.deleteAttachment(attachmentId);
    }

    // permite descargar un archivo de la base de datos a partir de su id
    @GetMapping(value = "/files/{fileId}") // indica que se trata de una petición HTTP GET y que se debe mapear a este
                                           // método.
    @PreAuthorize("@attachmentServiceImpl.isAuthorized('Collaborator',#fileId)")
    ResponseEntity<byte[]> downloadFile(@PathVariable Long fileId) {
        return attachmentService.downloadFile(fileId);
    }
}
