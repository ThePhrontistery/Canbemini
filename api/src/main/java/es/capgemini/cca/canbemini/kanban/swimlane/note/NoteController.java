package es.capgemini.cca.canbemini.kanban.swimlane.note;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.capgemini.cca.canbemini.mapppers.NoteMapper;

//Es un controlador que se encarga de gestionar las peticiones HTTP que llegan al servidor.
@RequestMapping(value = "/api/kanban/swimlane/note")
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*") // Puede recibir peticiones desde la aplicación
                                                                      // que se encuentra en ese dominio
@EnableWebSecurity // habilita la seguridad web de Spring
public class NoteController {
    @Autowired
    NoteService noteService;

    @Autowired
    NoteMapper noteMapper;

    public NoteController() {

    }

    // @PathVariable se utiliza para obtener un parámetro de la ruta de la petición
    // @PreAuthorize indica que se debe comprobar si el usuario que realiza la
    // petición tiene los permisos necesarios para ejecutar el método.

    /*
     * e encarga de obtener una nota específica a partir de su identificador.
     * Utiliza el servicio de NoteService y el mapeador de NoteMapper para realizar
     * esta operación.
     */
    @RequestMapping(path = "/get/{noteId}", method = RequestMethod.GET)
    @PreAuthorize("@noteServiceImpl.isAuthorized('Collaborator',#noteId)")
    public NoteDto getNote(@PathVariable("noteId") Long noteId) {
        return noteMapper.noteToNoteDto(noteService.getNote(noteId));
    }

    // Obtiene todas las notas de una swimlane específica. Utiliza el servicio de
    // NoteService y el mapeador de NoteMapper para realizar esta operación.
    @RequestMapping(path = "/{swimlaneId}", method = RequestMethod.GET)
    @PreAuthorize("@swimlaneServiceImpl.isAuthorized('Collaborator',#swimlaneId)")
    public List<NoteDto> getAllNotes(@PathVariable("swimlaneId") Long swimlaneId) {
        return noteMapper.map(noteService.findAllSwimlaneNotes(swimlaneId));
    }

    // guarda una nota nueva o de actualizar una nota existente. Utiliza el servicio
    // de NoteService para realizar esta operación.
    @RequestMapping(path = { "/{swimlaneId}", "/{swimlaneId}/{noteId}" }, method = RequestMethod.PUT)
    @PreAuthorize("@swimlaneServiceImpl.isAuthorized('Editor',#swimlaneId)")
    public Note save(@PathVariable(name = "noteId", required = false) Long noteId,
            @PathVariable(name = "swimlaneId", required = true) Long swimlaneId, @RequestBody NoteDto note) {
        return noteService.saveNote(noteId, note, swimlaneId);
    }

    // elimina una nota a partir de su identificador. Utiliza el servicio de
    // NoteService para realizar esta operación.
    @RequestMapping(path = "/{noteId}", method = RequestMethod.DELETE)
    @PreAuthorize("@noteServiceImpl.isAuthorized('Editor',#noteId)")
    public void delete(@PathVariable("noteId") Long noteId) {
        noteService.deleteNote(noteId);
    }

    // actualiza el orden de las notas de una swimlane específica. Utiliza el
    // servicio de NoteService para realizar esta operación.
    // @RequestBody se utiliza para obtener el cuerpo de la petición en formato JSON
    // y convertirlo a un objeto Java.
    @RequestMapping(path = "/updateNotesOrder/{swimlaneId}", method = RequestMethod.PUT)
    @PreAuthorize("@swimlaneServiceImpl.isAuthorized('Collaborator',#swimlaneId)")
    public void updateNotesOrder(@RequestBody List<NoteDto> notes, @PathVariable("swimlaneId") Long swimlaneId) {
        for (int i = 0; i < notes.size(); i++)
            noteService.saveNote(notes.get(i).getId(), notes.get(i), swimlaneId);
    }
}
