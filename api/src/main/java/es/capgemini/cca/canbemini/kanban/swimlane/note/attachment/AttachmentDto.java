package es.capgemini.cca.canbemini.kanban.swimlane.note.attachment;

import com.fasterxml.jackson.annotation.JsonIgnore;

//Representa a un objeto de transferencia de datos (DTO) para la entidad Attatchment
public class AttachmentDto {

    private Long id; // identificador único

    private String document_path; // ruta donde se encuentra el archivo adjunto

    private String name; // nombre del archivo

    private String type; // tipo de archivo: .pdf, .txt, .jpeg

    @JsonIgnore // este atributo no debe incluirse en la serialización a formato JSON.
    private byte[] file; // contiene el contenido del archivo adjunto.

    public AttachmentDto(String document_path) {

        this.document_path = document_path;
    }

    protected AttachmentDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocument_path() {
        return document_path;
    }

    public void setDocument_path(String document_path) {
        this.document_path = document_path;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
