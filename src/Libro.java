public class Libro extends Material {
    private String genero;
    private String editorial;

    public Libro(String codigo, String titulo, String autor,
                 int anioPublicacion, int numPaginas,
                 String genero, String editorial) {
        super(codigo, titulo, autor, anioPublicacion, numPaginas);
        this.genero = genero;
        this.editorial = editorial;
    }

    // Getters y setters
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public String getEditorial() { return editorial; }
    public void setEditorial(String editorial) { this.editorial = editorial; }

    @Override
    public String toString() {
        return super.toString() + String.format(" - Género: %s - Editorial: %s", genero, editorial);
    }
}
