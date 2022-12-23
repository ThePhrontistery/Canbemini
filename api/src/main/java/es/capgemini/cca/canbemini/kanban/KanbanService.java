package es.capgemini.cca.canbemini.kanban;

import java.util.List;

public interface KanbanService {

    List<Kanban> findUserKanbans(Long userId);

    List<Kanban> findUserKanbanId(Long userId, Long kanbanId);

    Kanban getKanban(Long id);

    void deleteKanban(Long id);

    void saveKanban(Long id, KanbanDto kanbanDto, Long userId);

    Kanban getByCode(String code);

}
