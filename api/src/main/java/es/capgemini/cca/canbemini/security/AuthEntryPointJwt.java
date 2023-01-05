package es.capgemini.cca.canbemini.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

//El componente AuthEntryPointJwt es una clase que implementa la interfaz AuthenticationEntryPoint de Spring Security.
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    // se encarga de manejar una excepción de autenticación que ocurra durante la
    // validación del token JWT en el sistema
    /*
     * HttpServletRequest request: representa la petición HTTP que ha generado el
     * error de autenticación. HttpServletResponse response: representa la respuesta
     * HTTP que se envía al cliente. AuthenticationException authException:
     * representa la excepción de autenticación que se ha producido.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        logger.error("Unauthorized error: {}", authException.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        final Map<String, Object> body = new HashMap<>();
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED); // el código de estado de la respuesta, en este caso
                                                                 // 401.
        body.put("error", "Unauthorized");// el tipo de error, en este caso "Unauthorized" (no autorizado).
        body.put("message", authException.getMessage()); // el mensaje de la excepción de autenticación
        body.put("path", request.getServletPath());// la ruta de la petición que ha generado el error

        /*
         * convierte el mapa a formato JSON y escribir el resultado en el flujo de
         * salida de la respuesta. De esta forma, cuando el cliente reciba la respuesta,
         * podrá interpretar los datos de error en formato JSON.
         */
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }
}
