package es.capgemini.cca.canbemini.kanban.swimlane;

import java.util.List;

import es.capgemini.cca.canbemini.security.NotAuthorizedException;

public interface SwimlaneService {

    List<Swimlane> findAll(Long id);// recupera una lista de todos los Swimlanes asociados a un determinado Kanban.

    Swimlane findSwimlane(Long id);// recupera un swimlane a partir de su id

    void deleteSwimlane(Long id);// elimina un swimlane a partir de su id

    Swimlane saveSwimlane(Long id, SwimlaneDto swimlaneDto, Long kanbanId);
    /*
     * guarda un Swimlane dado en la base de datos. Si el ID del Swimlane es null,
     * se crea un nuevo Swimlane; de lo contrario, se actualiza el Swimlane
     * existente con el mismo ID./*
     */

    boolean isAuthorized(String permission, Long swimlaneId) throws NotAuthorizedException;
    // verifica si el usuario actual tiene un determinado permiso para acceder a un
    // Swimlane específico.
}
