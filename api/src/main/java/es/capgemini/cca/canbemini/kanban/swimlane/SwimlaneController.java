package es.capgemini.cca.canbemini.kanban.swimlane;

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

import es.capgemini.cca.canbemini.mapppers.SwimlaneMapper;

/*el controlador está mapeado a la ruta "/api/kanban/swimlane" y maneja varias solicitudes 
 * HTTP, como GET, PUT y DELETE. Cada método del controlador está asociado con una solicitud
 * HTTP específica y se encarga de procesarla y devolver la respuesta adecuada.*/

@RequestMapping(value = "/api/kanban/swimlane")
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@EnableWebSecurity
public class SwimlaneController {
    @Autowired
    SwimlaneService swimlaneService;// se encarga de realizar tareas más complejas, como acceder a la base de datos
                                    // para obtener o guardar entidades Swimlane.

    @Autowired
    SwimlaneMapper swimlaneMapper;// que se encarga de convertir entidades Swimlane en objetos DTO

    public SwimlaneController() {

    }

    /*
     * Maneja una solicitud GET y recibe como parámetro el ID de una entidad
     * Swimlane. Utiliza el servicio SwimlaneService para obtener la entidad
     * Swimlane de la base de datos y luego utiliza el mapeador SwimlaneMapper para
     * convertirla en un objeto DTO. El método devuelve el DTO como respuesta a la
     * solicitud.
     */

    @RequestMapping(path = "/get/{swimlaneId}", method = RequestMethod.GET)
    @PreAuthorize("@swimlaneServiceImpl.isAuthorized('Collaborator',#swimlaneId)")
    public SwimlaneDto getSwimlane(@PathVariable("swimlaneId") Long swimlaneId) {
        return swimlaneMapper.SwimlaneToSwimlaneDto(swimlaneService.findSwimlane(swimlaneId));
    }

    /*
     * obtener una lista de swimlanes asociados a un Kanban específico, identificado
     * por su id (kanbanId). Para ello, se hace uso del servicio swimlaneService y
     * su método findAll, que recibe como parámetro el id del Kanban y devuelve una
     * lista de objetos de tipo Swimlane
     */

    @RequestMapping(path = "/{kanbanId}", method = RequestMethod.GET)
    @PreAuthorize("@kanbanServiceImpl.isAuthorized('Collaborator',#kanbanId)")
    public List<SwimlaneDto> getAllKanbanSwimlanes(@PathVariable("kanbanId") Long kanbanId) {
        return swimlaneMapper.swimlaneListToSwimlaneListDto(swimlaneService.findAll(kanbanId));
    }
    /*
     * Maneja una solicitud PUT y recibe como parámetros el ID de una entidad
     * Swimlane (si existe), un objeto DTO y el ID de una entidad Kanban. Utiliza el
     * mapeador SwimlaneMapper para convertir el DTO en una entidad Swimlane y luego
     * utiliza el servicio SwimlaneService para guardar la entidad en la base de
     * datos.
     */

    @RequestMapping(path = { "/save/{kanbanId}", "/save/{kanbanId}/{swimlaneId}" }, method = RequestMethod.PUT)
    @PreAuthorize("@kanbanServiceImpl.isAuthorized('Editor',#kanbanId)")
    public void save(@PathVariable(name = "swimlaneId", required = false) Long swimlaneId,
            @RequestBody SwimlaneDto swimlane, @PathVariable(name = "kanbanId", required = true) Long kanbanId) {
        swimlaneService.saveSwimlane(swimlaneId, swimlane, kanbanId);
    }

    /*
     * maneja una solicitud DELETE y recibe como parámetro el ID de una entidad
     * Swimlane. Utiliza el servicio SwimlaneService para eliminar la entidad de la
     * base de datos. El método no devuelve ninguna respuesta.
     */
    @RequestMapping(path = "/{swimlaneId}", method = RequestMethod.DELETE)
    @PreAuthorize("@swimlaneServiceImpl.isAuthorized('Editor',#swimlaneId)")
    public void delete(@PathVariable("swimlaneId") Long swimlaneId) {
        swimlaneService.deleteSwimlane(swimlaneId);
    }

    /*
     * maneja una solicitud PUT y recibe como parámetro una lista de objetos DTO y
     * el ID de una entidad Kanban. Utiliza el mapeador Swimlane para convertir
     * objetos de la clase Swimlane en objetos de la clase SwimlaneDto
     */

    @RequestMapping(path = "/updateLanesOrder/{kanbanId}", method = RequestMethod.PUT)
    @PreAuthorize("@kanbanServiceImpl.isAuthorized('Collaborator',#kanbanId)")
    public void updateSwimlanesOrder(@RequestBody List<SwimlaneDto> swimlanes,
            @PathVariable("kanbanId") Long kanbanId) {
        for (int i = 0; i < swimlanes.size(); i++)
            swimlaneService.saveSwimlane(swimlanes.get(i).getId(), swimlanes.get(i), kanbanId);
    }
}
