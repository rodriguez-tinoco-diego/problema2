import java.time.LocalDate;

public abstract class Material {
    private String codigo;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private int numPaginas;
    private EstadoMaterial estado;


    protected Material(String codigo, String titulo, String autor,
                       int anioPublicacion, int numPaginas) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.numPaginas = numPaginas;
        this.estado = EstadoMaterial.DISPONIBLE;
    }


    public String getCodigo() { return codigo; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAnioPublicacion() { return anioPublicacion; }
    public int getNumPaginas() { return numPaginas; }
    public EstadoMaterial getEstado() { return estado; }
    protected void setEstado(EstadoMaterial estado) { this.estado = estado; }


    public boolean prestar() {
        if (estado == EstadoMaterial.DISPONIBLE) {
            estado = EstadoMaterial.PRESTADO;
            return true;
        }
        return false;
    }

    public boolean devolver() {
        if (estado == EstadoMaterial.PRESTADO) {
            estado = EstadoMaterial.DISPONIBLE;
            return true;
        }
        return false;
    }

    public boolean estaDisponible() {
        return estado == EstadoMaterial.DISPONIBLE;
    }

    @Override
    public String toString() {
        return String.format("%s [%s] - %s (%d) - %d págs. - %s",
                getClass().getSimpleName(), codigo, titulo, anioPublicacion,
                numPaginas, estado);
    }
}