package es.capgemini.cca.canbemini.security;

import es.capgemini.cca.canbemini.users.UsersDto;

//clase de respuesta para el inicio de sesión en la aplicación
public class LoginReply {
    UsersDto user;// un objeto de la clase UsersDto, que representa al usuario que ha iniciado
                  // sesión.
    String token; // token que se genera para el usuario que inicia sesión

    public UsersDto getUser() {
        return user;
    }

    public void setUser(UsersDto user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
