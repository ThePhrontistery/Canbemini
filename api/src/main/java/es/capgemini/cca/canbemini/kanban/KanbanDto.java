package es.capgemini.cca.canbemini.kanban;

import java.util.List;

import es.capgemini.cca.canbemini.kanban.swimlane.SwimlaneDto;
import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermissionDto;

/*representa un objeto Kanban en formato de transferencia de datos (Data Transfer Object, DTO).
 *  Es utilizada para enviar y recibir información de una aplicación de Kanban a través de una 
 *  interfaz de programación de aplicaciones (API).
 */
public class KanbanDto {

    private Long id;

    private String title;

    private String description;

    private String code;

    private List<UserKanbanPermissionDto> userKanbanPermission;

    private List<SwimlaneDto> swimlanes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UserKanbanPermissionDto> getUserKanbanPermission() {
        return userKanbanPermission;
    }

    public void setUserKanbanPermission(List<UserKanbanPermissionDto> userKanbanPermission) {
        this.userKanbanPermission = userKanbanPermission;
    }

    public List<SwimlaneDto> getSwimlanes() {
        swimlanes.sort((a, a1) -> a.getOrder().compareTo(a1.getOrder()));
        for (int i = 0; i < swimlanes.size(); i++)
            // getNotes ordena las notas a la vez que las devuelve, por lo que necesitamos
            // que se ordenen todas antes de devolver el array
            swimlanes.get(i).setNotes(swimlanes.get(i).getNotes());
        return swimlanes;
    }

    public void setSwimlanes(List<SwimlaneDto> swimlanes) {
        this.swimlanes = swimlanes;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
