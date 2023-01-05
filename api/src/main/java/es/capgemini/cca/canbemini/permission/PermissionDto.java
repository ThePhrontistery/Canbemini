package es.capgemini.cca.canbemini.permission;

//es una clase que se utiliza para transferir datos entre el servidor y la aplicación cliente
public class PermissionDto {
    private Long id; // identificador de un permiso

    private String rol; // contiene el nombre del rol asociado al permiso

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
