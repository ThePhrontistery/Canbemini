package es.capgemini.cca.canbemini.kanban;

import java.util.List;

import es.capgemini.cca.canbemini.security.NotAuthorizedException;
import es.capgemini.cca.canbemini.security.UserDetailsImpl;

//La clase KanbanService es una interface que define un conjunto de métodos
public interface KanbanService {

    List<Kanban> findUserKanbans(); // este método devuelve una lista de Kanban que pertenecen al usuario actual.

    List<Kanban> findUserKanbanId(Long userId, Long kanbanId); // este método devuelve una lista de Kanban que
                                                               // pertenecen al usuario con ID userId y que tienen el ID
                                                               // kanbanId.

    Kanban getKanban(Long id); // devuelve el kanban con el ID que se le especifique

    void deleteKanban(Long id);// elimina el kanban por ID

    void saveKanban(Long id, KanbanDto kanbanDto, Long userId);// guarda un Kanban en la base de datos. Si el Kanban ya
                                                               // existe (tiene un ID válido), se actualiza. Si el
                                                               // Kanban es nuevo (tiene un ID nulo), se crea uno nuevo.

    Kanban getByCode(String code);// devuelve el Kanban con el código especificado.

    public Boolean isAuthorized(String permission, Long kanbanId) throws NotAuthorizedException; // verifica si el
                                                                                                 // usuario actual tiene
                                                                                                 // la permisión
                                                                                                 // especificada para el
                                                                                                 // Kanban con ID
                                                                                                 // kanbanId.

    public Boolean verifyUser(Long userId, UserDetailsImpl userDetailsImpl);// verifica si el usuario con ID userId es
                                                                            // el usuario actual.
}
