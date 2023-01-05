package es.capgemini.cca.canbemini.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*extiende de la clase Exception de Java y es usada para representar una 
 * excepción que se lanzará cuando un usuario intente acceder a un recurso 
 * del sistema para el cual no tiene autorización
 */

@SuppressWarnings("serial") // se está suprimiendo la advertencia relacionada con la implementación de la
                            // serialización.
/*
 * lo que significa que cuando esta excepción sea lanzada, se enviará una
 * respuesta HTTP con el código de estado 403 (FORBIDDEN) y un mensaje
 * personalizado
 */
@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "This user is not a member of this kanban!")
public class NotAuthorizedException extends Exception {
    public String msgException; // puede ser usada para almacenar un mensaje de error específico que se desea
                                // enviar al cliente.

    public NotAuthorizedException(String msgException) {
        this.setMsgException(msgException);
    }

    public String getMsgException() {
        return msgException;
    }

    public void setMsgException(String msgException) {
        this.msgException = msgException;
    }
}
