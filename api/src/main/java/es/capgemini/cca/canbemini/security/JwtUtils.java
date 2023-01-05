package es.capgemini.cca.canbemini.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth0.jwt.exceptions.JWTCreationException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

//es un componente de Spring que se encarga de generar y validar tokens
@Component
public class JwtUtils {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${jwt_secret}")
    private String keysecret;

    @Value("${jwt_expirationTime}")
    private Long expirationTime;

    /*
     * Este método se utiliza para generar un token JWT para un usuario específico.
     * Toma como parámetro el correo electrónico del usuario y utiliza una clave
     * secreta y un tiempo de expiración (que se establecen como propiedades de la
     * clase) para crear el token. Si todo sale bien, el método devuelve el token
     * generado.
     */
    public String generateToken(String email) throws IllegalArgumentException, JWTCreationException {
        Map<String, Object> claim = new HashMap<>();
        claim.put("email", email);

        return Jwts.builder().setSubject(email).addClaims(claim).signWith(Keys.hmacShaKeyFor(keysecret.getBytes()))
                .setExpiration(new Date((new Date()).getTime() + expirationTime)).compact();
    }

    /*
     * se utiliza para obtener la información de autenticación de un usuario a
     * partir de un token JWT. Toma como parámetro el token y utiliza la clave
     * secreta para verificar que es válido. Si el token es válido, el método
     * devuelve una instancia de UsernamePasswordAuthenticationToken que contiene la
     * información de autenticación del usuario.
     */
    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        /*
         * En el contexto de la biblioteca JWT (Java Web Token), "claims" son una serie
         * de afirmaciones que se incluyen en un token JWT y que pueden ser utilizadas
         * para transmitir información entre dos sistemas. Los claims pueden ser
         * cualquier cosa, desde la identidad del usuario que autentica el token hasta
         * cualquier otra información que se quiera transmitir. Los claims se incluyen
         * en el cuerpo del token JWT y pueden ser firmados de manera que se garantice
         * que no han sido alterados durante la transmisión.
         */
        Claims claims = Jwts.parserBuilder().setSigningKey(keysecret.getBytes()).build().parseClaimsJws(token)
                .getBody();
        String email = claims.getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(email, null, userDetails.getAuthorities());
    }

    // se utiliza para obtener el correo electrónico del usuario que se encuentra en
    // el token JWT.
    public String getEmailFromJwtToken(String token) {

        /*
         * Crea un parser de JWT con la clave secreta utilizada para firmar el token,
         * verifica la firma del token y devuelve un objeto Claims con los datos del
         * token
         */
        return Jwts.parserBuilder().setSigningKey(keysecret.getBytes()).build().parseClaimsJws(token).getBody()
                .getSubject();
    }

    // Este método se encarga de verificar la validez de un token JWT dado
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(keysecret.getBytes()).build().parseClaimsJws(authToken);// verifica la
                                                                                                       // firma del
                                                                                                       // token JWT y,
                                                                                                       // si es válida,
                                                                                                       // devuelve un
                                                                                                       // objeto Jws que
                                                                                                       // contiene la
                                                                                                       // carga del
                                                                                                       // token (claims)
                                                                                                       // y la cabecera
                                                                                                       // del token.
            return true;
        } catch (SecurityException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());// se lanza si la firma del token no es válida.
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());// se lanza si el formato del token es incorrecto.
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());// se lanza si el token ha expirado.
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());// se lanza si el token utiliza un formato de
                                                                         // firma no soportado.
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());// Se lanza si la cadena de claims del token
                                                                           // está vacía.

        }

        return false;
    }

}
