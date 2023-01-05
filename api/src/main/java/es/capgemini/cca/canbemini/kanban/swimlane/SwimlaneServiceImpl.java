package es.capgemini.cca.canbemini.kanban.swimlane;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.capgemini.cca.canbemini.kanban.KanbanService;
import es.capgemini.cca.canbemini.security.NotAuthorizedException;

// Es una implementación de la interfaz SwimlaneService
@Service
public class SwimlaneServiceImpl implements SwimlaneService {

    @Autowired
    SwimlaneRepository swimlaneRepository;

    @Autowired
    KanbanService kanbanService;

    /*
     * recibe como parámetro un id de kanban y retorna una lista de objetos de tipo
     * Swimlane. Esta lista se obtiene a través del método findAll del repositorio
     * SwimlaneRepository, que a su vez ejecuta una consulta de selección de todos
     * los objetos de tipo Swimlane cuyo campo kanban tenga un id igual al que se
     * pasa como parámetro.
     */
    @Override
    public List<Swimlane> findAll(Long kanbanId) {
        return (List<Swimlane>) this.swimlaneRepository.findAll(kanbanId);
    }

    /*
     * recibe un id de swimlane y elimina el objeto de tipo Swimlane cuyo campo id
     * coincide con el valor del parámetro.
     */

    @Override
    public void deleteSwimlane(Long id) {
        this.swimlaneRepository.deleteById(id);
    }

    /*
     * recibe un id de swimlane y retorna el objeto de tipo Swimlane cuyo campo id
     * coincide con el valor del parámetro. Si no se encuentra ningún objeto con ese
     * id, se retorna null.
     */
    @Override
    public Swimlane findSwimlane(Long id) {
        return this.swimlaneRepository.findById(id).orElse(null);
    }

    /*
     * recibe un id de swimlane, un objeto de tipo SwimlaneDto y un id de kanban. Si
     * el id de swimlane es null, se crea un nuevo objeto de tipo Swimlane, en caso
     * contrario se obtiene el objeto de tipo Swimlane cuyo campo id coincide con el
     * valor del parámetro. A continuación, se establecen los campos title, kanban y
     * order del objeto de tipo Swimlane y se guardan los cambios en la base de
     * datos a través del repositorio SwimlaneRepository.
     */
    @Override
    public Swimlane saveSwimlane(Long id, SwimlaneDto swimlaneDto, Long kanbanId) {
        Swimlane swimlane = null;

        if (id == null)
            swimlane = new Swimlane();
        else
            swimlane = this.findSwimlane(id);

        swimlane.setTitle(swimlaneDto.getTitle());
        swimlane.setKanban(kanbanService.getKanban(kanbanId));
        swimlane.setOrder(swimlaneDto.getOrder());

        BeanUtils.copyProperties(swimlaneDto, swimlane, "id", "kanban");

        this.swimlaneRepository.save(swimlane);

        return swimlane;
    }

    /*
     * Recibe un permission y un id de swimlane y verifica si el usuario que está
     * realizando la solicitud tiene un permiso especificada para el kanban al que
     * pertenece el swimlane con el id especificado. Si el usuario no tiene la
     * permission requerida, se lanza una excepción de tipo NotAuthorizedException.
     */
    @Override
    public boolean isAuthorized(String permission, Long swimlaneId) throws NotAuthorizedException {
        Swimlane swimlane = this.swimlaneRepository.findById(swimlaneId).orElse(null);
        return this.kanbanService.isAuthorized(permission, swimlane.getKanban().getId());
    }
}
