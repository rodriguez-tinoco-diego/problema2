public class Persona {
    private String idPersona;
    private String nombre;
    private String identificacion; // INE, pasaporte, etc.
    private String celular;
    private String direccion;

    public Persona(String idPersona, String nombre, String identificacion,
                   String celular, String direccion) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.celular = celular;
        this.direccion = direccion;
    }


    public String getIdPersona() {
        return idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return String.format("Persona{id='%s', nombre='%s', ident='%s', cel='%s', dir='%s'}",
                idPersona, nombre, identificacion, celular, direccion);
    }
}
