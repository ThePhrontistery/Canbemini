package es.capgemini.cca.canbemini.security;

// se utiliza para almacenar la información de un usuario
public class UserInfoResponse {
    private String email;

    public UserInfoResponse(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
