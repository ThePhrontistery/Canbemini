package es.capgemini.cca.canbemini.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.capgemini.cca.canbemini.mapppers.UsersMapper;
import es.capgemini.cca.canbemini.users.Users;
import es.capgemini.cca.canbemini.users.UsersRepository;
import es.capgemini.cca.canbemini.users.UsersService;

//se encarga de gestionar la autenticación de usuarios en la aplicación
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsersRepository userRepository;

    @Autowired
    UsersService userService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UsersMapper userMapper;

    @Autowired
    JwtUtils jwtUtils;

    // permite a un usuario iniciar sesión proporcionando su dirección de correo
    // electrónico y su contraseña
    @PostMapping("/login")
    public LoginReply authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate( // validar las credenciales del usuario. Si
                                                                            // las credenciales son válidas, se crea un
                                                                            // token JWT (JSON Web Token)
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.generateToken(loginRequest.getEmail());// para autorizar al usuario en el resto de la
                                                                       // aplicación

        LoginReply reply = new LoginReply();// es una clase que se utiliza para devolver una respuesta al cliente cuando
                                            // este envía una solicitud de inicio de sesión. La respuesta incluirá un
                                            // token de autenticación
        reply.setToken(token);

        Users user = userService.findByEmail(loginRequest.getEmail());// Se busca el usuario en la base de datos
                                                                      // utilizando el email proporcionado.

        reply.setUser(userMapper.UsersToUsersDto(user)); // se mapea a un objeto de tipo UsersDto utilizando la clase
                                                         // UsersMapper y se envía en la respuesta al usuario

        return reply; //
    }

}
