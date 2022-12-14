package es.capgemini.cca.canbemini.kanban.swimlane.note;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.capgemini.cca.canbemini.kanban.swimlane.SwimlaneService;
import es.capgemini.cca.canbemini.security.NotAuthorizedException;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    SwimlaneService swimlaneService;

    @Override
    public List<Note> findAllSwimlaneNotes(Long swimlaneId) {
        return (List<Note>) this.noteRepository.findAllSwimlaneNotes(swimlaneId);
    }

    @Override
    public void deleteNote(Long id) {
        this.noteRepository.deleteById(id);
    }

    @Override
    public Note getNote(Long id) {
        return this.noteRepository.findById(id).orElse(null);
    }

    @Override
    public Note saveNote(Long id, NoteDto noteDto, Long swimlaneId) {
        Note note = null;

        if (id == null)
            note = new Note();
        else
            note = this.getNote(id);
        note.setOrder(noteDto.getOrder());
        note.setContent(noteDto.getContent());
        note.setSwimlane(swimlaneService.findSwimlane(swimlaneId));
        //BeanUtils.copyProperties(noteDto, note, "id", "swimlane");

        this.noteRepository.save(note);
        return note;
    }

    @Override
    public boolean isAuthorized(String permission, Long noteId) throws NotAuthorizedException {

        Note note = this.noteRepository.findById(noteId).orElse(null);

        return this.swimlaneService.isAuthorized(permission, note.getSwimlane().getId());
    }

}
