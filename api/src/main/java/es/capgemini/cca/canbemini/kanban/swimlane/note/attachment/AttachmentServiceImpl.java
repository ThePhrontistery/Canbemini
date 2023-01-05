package es.capgemini.cca.canbemini.kanban.swimlane.note.attachment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.capgemini.cca.canbemini.kanban.swimlane.note.NoteService;
import es.capgemini.cca.canbemini.security.NotAuthorizedException;

//Este es un servicio de atachments que implementa la interfaz AttachmentService.
@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    NoteService noteService;

    // @Value: se utilizan para construir una URL completa a partir de la cual se
    // descargará un archivo adjunto desde el servidor
    @Value("${spring.server.url}")
    private String url;

    @Value("${spring.server.port}")
    private String port;

    // Buscar todos los atachment de una nota específica
    @Override
    public List<Attachment> findAttachmentNotes(Long noteId) {
        return (List<Attachment>) this.attachmentRepository.findAttachmentNotes(noteId);
    }

    // Buscar un attachment específico
    @Override
    public Attachment findAttachment(Long id) {
        return this.attachmentRepository.findById(id).orElse(null);
    }

    // borrarlo
    @Override
    public void deleteAttachment(Long id) {
        this.attachmentRepository.deleteById(id);
    }

    // guarda un attatchemnt
    @Override
    public Attachment saveAttachment(Long noteId, Long id, MultipartFile multipartFile) {
        Attachment attachment = null;

        if (id == null)
            attachment = new Attachment();
        else
            attachment = this.findAttachment(id);

        attachment.setName(multipartFile.getOriginalFilename());
        attachment.setType(multipartFile.getContentType());
        attachment.setNote(noteService.getNote(noteId));

        try {

            byte file[] = multipartFile.getBytes();
            if (file.length > 0)
                attachment.setFile(file);
            this.attachmentRepository.save(attachment);
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromHttpUrl(url + ":" + port + "/api/kanban/swimlane/note/attachment/files")
                    .path("/" + attachment.getId()).toUriString();
            attachment.setDocument_path(fileDownloadUri);
            this.attachmentRepository.save(attachment);
            return attachment;

        } catch (Exception e) {

        }
        return null;
    }

    // descarga un attachment
    @Override
    public ResponseEntity<byte[]> downloadFile(Long id) {
        Attachment attachment = attachmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        byte[] file = attachment.getFile();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + attachment.getName())
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Content-Disposition").body(file);
    }

    // Verifica si un usuario tiene permisos
    @Override
    public Boolean isAuthorized(String permission, Long attachmentId) throws NotAuthorizedException {

        Attachment attachment = this.attachmentRepository.findById(attachmentId).orElse(null);
        return this.noteService.isAuthorized(permission, attachment.getNote().getId());

    }

}
