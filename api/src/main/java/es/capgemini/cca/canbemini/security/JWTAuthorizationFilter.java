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

//es un filtro que se ejecuta en cada solicitud para verificar si hay un token de autorización en la cabecera de la solicitud.
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String bearerToken = request.getHeader("Authorization");
        /*
         * Si hay un token, se extrae eliminando la cadena "Bearer " y se pasa al método
         * getAuthentication de JwtUtils para verificar y decodificar el token. Si el
         * token es válido, se crea una nueva instancia de
         * UsernamePasswordAuthenticationToken con la información del usuario contenida
         * en el token y se establece en el contexto de seguridad de la aplicación
         */
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.replace("Bearer ", "");
            UsernamePasswordAuthenticationToken usernamePAT = jwtUtils.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(usernamePAT);
        }
        /*
         * una vez que se ha procesado la solicitud y se ha establecido la
         * autenticación, se permite que la solicitud continúe su camino a través de la
         * cadena de filtros y finalmente se envíe al cliente.
         */
        filterChain.doFilter(request, response);
    }

}
