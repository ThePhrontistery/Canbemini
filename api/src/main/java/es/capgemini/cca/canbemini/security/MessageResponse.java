package es.capgemini.cca.canbemini.security;

//clase de respuesta utilizada para enviar un mensaje de respuesta a una solicitud
public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
