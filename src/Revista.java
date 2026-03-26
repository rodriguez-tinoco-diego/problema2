public class Revista extends Material {
    private int numeroEdicion;
    private String mesPublicacion;

    public Revista(String codigo, String titulo, String autor,
                   int anioPublicacion, int numPaginas,
                   int numeroEdicion, String mesPublicacion) {
        super(codigo, titulo, autor, anioPublicacion, numPaginas);
        this.numeroEdicion = numeroEdicion;
        this.mesPublicacion = mesPublicacion;
    }

    public int getNumeroEdicion() { return numeroEdicion; }
    public void setNumeroEdicion(int numeroEdicion) { this.numeroEdicion = numeroEdicion; }
    public String getMesPublicacion() { return mesPublicacion; }
    public void setMesPublicacion(String mesPublicacion) { this.mesPublicacion = mesPublicacion; }

    @Override
    public String toString() {
        return super.toString() + String.format(" - Nº Edición: %d - Mes: %s",
                numeroEdicion, mesPublicacion);
    }
}