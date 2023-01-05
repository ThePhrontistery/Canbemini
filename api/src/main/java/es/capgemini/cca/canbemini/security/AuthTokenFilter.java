package es.capgemini.cca.canbemini.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;

//Este filtro se encarga de verificar que haya un token válido en la cabecera de autenticación de cada petición que se realiza al backend.
public class AuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;

    // se ejecuta en cada solicitud que llega al servidor
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // obtiene el valor de la cabecera Authorization de la solicitud y lo almacena
        // en la variable authHeader.
        String authHeader = request.getHeader("Authorization");
        /*
         * Si authHeader no es nulo y comienza con "Bearer ", entra al bloque de código
         * que sigue. De lo contrario, el filtro simplemente continúa el flujo de la
         * solicitud hacia el siguiente componente del sistema.
         */
        if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);// extrae el token JWT de authHeader eliminando la cadena "Bearer " al
                                                 // principio.
            /*
             * Si el token JWT es nulo o está vacío, envía un error HTTP 400 (solicitud
             * inválida) con el mensaje "Invalid JWT Token in Bearer Header". De lo
             * contrario, continúa el procesamiento del token
             */
            if (jwt == null || jwt.isBlank()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token in Bearer Header");
            } else {
                try {
                    UsernamePasswordAuthenticationToken authToken = jwtUtils
                            .getAuthentication(jwt);/*
                                                     * encargado de validar el token JWT y devolver un objeto
                                                     * UsernamePasswordAuthenticationToken con la información del
                                                     * usuario autenticado
                                                     */
                    if (SecurityContextHolder.getContext().getAuthentication() == null)
                    // Si el contexto de seguridad actual es nulo, establece la autenticación del
                    // token en el contexto de seguridad.
                    {
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                } catch (JWTVerificationException exc) {
                    // Si la validación del token JWT falla, envía un error HTTP 400 (solicitud
                    // inválida) con el mensaje "Invalid JWT Token".
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token");
                }
            }
        }

        filterChain.doFilter(request, response);
    }

}
