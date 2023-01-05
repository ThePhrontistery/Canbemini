package es.capgemini.cca.canbemini;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import es.capgemini.cca.canbemini.kanban.Kanban;
import es.capgemini.cca.canbemini.kanban.KanbanRepository;
import es.capgemini.cca.canbemini.kanban.swimlane.Swimlane;
import es.capgemini.cca.canbemini.kanban.swimlane.SwimlaneRepository;
import es.capgemini.cca.canbemini.kanban.swimlane.note.Note;
import es.capgemini.cca.canbemini.kanban.swimlane.note.NoteRepository;
import es.capgemini.cca.canbemini.kanban.swimlane.note.attachment.Attachment;
import es.capgemini.cca.canbemini.kanban.swimlane.note.attachment.AttachmentRepository;
import es.capgemini.cca.canbemini.permission.Permission;
import es.capgemini.cca.canbemini.permission.PermissionRepository;
import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermission;
import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermissionRepository;
import es.capgemini.cca.canbemini.users.Users;
import es.capgemini.cca.canbemini.users.UsersRepository;

// esta anotación indica que es el punto de entrada
@SpringBootApplication
public class CanbeminiApplication {

    @Autowired
    PasswordEncoder passwordEncoder;

    private static final Logger log = LoggerFactory.getLogger(CanbeminiApplication.class);

    // s el punto de entrada del programa. Este método invoca a
    // SpringApplication.run, lo que inicia la aplicación.
    public static void main(String[] args) {
        SpringApplication.run(CanbeminiApplication.class, args);
    }

    /*
     * método demo annotado con @Bean, que se utiliza para configurar un
     * CommandLineRunner. Un CommandLineRunner es un componente de Spring que se
     * ejecuta justo después de que la aplicación se inicia y antes de que el
     * contenedor se cierre. En este caso, el método demo se utiliza para insertar
     * algunos datos de prueba en las diferentes tablas de la base de datos.
     */
    @Bean
    public CommandLineRunner demo(KanbanRepository kanbanRepository, SwimlaneRepository swimlaneRepository,
            NoteRepository noteRepository, UsersRepository usersRepository, PermissionRepository permissionRepository,
            AttachmentRepository attachmentRepository, UserKanbanPermissionRepository ukpRepository) {
        return (args) -> {
            // save a few customers
            Users user1 = new Users("cesar@email.com", passwordEncoder.encode("123"));
            Users user2 = new Users("mercedes@email.com", passwordEncoder.encode("123"));
            Users user3 = new Users("jacques@email.com", passwordEncoder.encode("123"));
            Users user4 = new Users("raul@email.com", passwordEncoder.encode("123"));
            Users user5 = new Users("fredy@email.com", passwordEncoder.encode("123"));

            usersRepository.save(user1);
            usersRepository.save(user2);
            usersRepository.save(user3);
            usersRepository.save(user4);
            usersRepository.save(user5);

            // save a few Kanbans
            Kanban kanban1 = new Kanban("My first Kanban", "First Kanban Description");
            Kanban kanban2 = new Kanban("A second Kanban", "Second Kanban Description");
            kanbanRepository.save(kanban1);
            kanbanRepository.save(kanban2);

            // save a few Swimlanes
            Swimlane swimlane1 = new Swimlane("TO DO", kanban1, 2L);
            Swimlane swimlane2 = new Swimlane("TO PROGRESS", kanban1, 1L);
            Swimlane swimlane3 = new Swimlane("DONE", kanban1, 3L);
            Swimlane swimlane4 = new Swimlane("TO DO", kanban2, 1L);
            Swimlane swimlane5 = new Swimlane("TO PROGRESS", kanban2, 2L);
            Swimlane swimlane6 = new Swimlane("DONE", kanban2, 3L);
            swimlaneRepository.save(swimlane1);
            swimlaneRepository.save(swimlane2);
            swimlaneRepository.save(swimlane3);
            swimlaneRepository.save(swimlane4);
            swimlaneRepository.save(swimlane5);
            swimlaneRepository.save(swimlane6);

            // save a few Notes
            Note note1 = new Note("content1", swimlane1, 1L);
            Note note2 = new Note("content2", swimlane1, 2L);
            Note note3 = new Note("content3", swimlane2, 1L);
            Note note4 = new Note("content4", swimlane3, 1L);
            Note note5 = new Note("content5", swimlane3, 2L);
            Note note6 = new Note("content6", swimlane4, 1L);
            Note note7 = new Note("content7", swimlane4, 2L);
            Note note8 = new Note("content8", swimlane4, 3L);
            Note note9 = new Note("content9", swimlane5, 1L);
            Note note10 = new Note("content10", swimlane5, 2L);
            noteRepository.save(note1);
            noteRepository.save(note2);
            noteRepository.save(note3);
            noteRepository.save(note4);
            noteRepository.save(note5);
            noteRepository.save(note6);
            noteRepository.save(note7);
            noteRepository.save(note8);
            noteRepository.save(note9);
            noteRepository.save(note10);

            // save a few attachments
            Attachment attach1 = new Attachment(note1, "url1");
            Attachment attach2 = new Attachment(note2, "url2");
            Attachment attach3 = new Attachment(note3, "url3");
            Attachment attach4 = new Attachment(note4, "url4");
            attachmentRepository.save(attach1);
            attachmentRepository.save(attach2);
            attachmentRepository.save(attach3);
            attachmentRepository.save(attach4);

            // save a few permissions
            Permission permission1 = new Permission("Owner");
            Permission permission2 = new Permission("Editor");
            Permission permission3 = new Permission("Collaborator");
            permissionRepository.save(permission1);
            permissionRepository.save(permission2);
            permissionRepository.save(permission3);

            // save user_kanban_permission table data
            UserKanbanPermission ukp1 = new UserKanbanPermission(user1, kanban1, permission1);
            UserKanbanPermission ukp2 = new UserKanbanPermission(user2, kanban1, permission2);
            UserKanbanPermission ukp3 = new UserKanbanPermission(user3, kanban1, permission3);
            UserKanbanPermission ukp4 = new UserKanbanPermission(user4, kanban2, permission1);
            UserKanbanPermission ukp5 = new UserKanbanPermission(user5, kanban2, permission2);
            UserKanbanPermission ukp6 = new UserKanbanPermission(user2, kanban2, permission3);
            ukpRepository.save(ukp1);
            ukpRepository.save(ukp2);
            ukpRepository.save(ukp3);
            ukpRepository.save(ukp4);
            ukpRepository.save(ukp5);
            ukpRepository.save(ukp6);

            // bbuscar todos los Kanbans, se usa un repositorio que se utiliza para acceder
            // a la tabla de kanbans en la base de datos.
            log.info("Kanbans found with findAll():");
            log.info("-------------------------------");
            for (Kanban kanban : kanbanRepository.findAll()) {
                log.info(kanban.toString());
            }
            log.info("");

            // fetch an individual Kanban by ID
            Kanban kanban = kanbanRepository.findById(1L).orElse(null);
            log.info("Kanban found with findById(1L):");
            log.info("--------------------------------");
            log.info(kanban.toString());
            log.info("");

        };
    }

}
