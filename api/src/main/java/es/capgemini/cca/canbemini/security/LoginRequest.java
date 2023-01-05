package es.capgemini.cca.canbemini.security;

import javax.validation.constraints.NotBlank;

//se utiliza para representar la solicitud de inicio de sesión que se envía al servidor
public class LoginRequest {
    @NotBlank // se utiliza para asegurar que este campo no esté vacío o contenga solo
              // espacios en blanco
    private String email;

    @NotBlank
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
