package es.capgemini.cca.canbemini.kanban.swimlane.note;

import java.util.List;

import es.capgemini.cca.canbemini.security.NotAuthorizedException;

// permite realizar operaciones de lectura y escritura de objetos de tipo Note
public interface NoteService {

    // obtener una lista de todas las notas asociadas a una Swimlane determinada,
    // identificada por su ID.
    List<Note> findAllSwimlaneNotes(Long swimlaneId);

    // obtener una nota determinada a partir de su ID.
    Note getNote(Long id);

    // eliminar una nota determinada a partir de su ID.
    void deleteNote(Long id);

    // guardar una nota. Si la nota ya existe (tiene un ID no nulo), se actualiza.
    // En caso contrario, se crea una nueva nota.
    Note saveNote(Long id, NoteDto noteDto, Long swimlaneId);

    /*
     * comprueba si el usuario actual tiene permiso para realizar una determinada
     * acción (especificada por la cadena permission) sobre una nota determinada
     * (identificada por su ID). Este método lanzará una excepción
     * NotAuthorizedException si el usuario no tiene permiso para realizar la
     * acción.
     */
    boolean isAuthorized(String permission, Long noteId) throws NotAuthorizedException;
}
