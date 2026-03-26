public class Audiolibro extends Material {
    private int duracionMinutos;
    private String narrador;
    private String formato; // MP3, WAV, etc.

    public Audiolibro(String codigo, String titulo, String autor,
                      int anioPublicacion, int numPaginas,
                      int duracionMinutos, String narrador, String formato) {
        super(codigo, titulo, autor, anioPublicacion, numPaginas);
        this.duracionMinutos = duracionMinutos;
        this.narrador = narrador;
        this.formato = formato;
    }

    public int getDuracionMinutos() { return duracionMinutos; }
    public void setDuracionMinutos(int duracionMinutos) { this.duracionMinutos = duracionMinutos; }
    public String getNarrador() { return narrador; }
    public void setNarrador(String narrador) { this.narrador = narrador; }
    public String getFormato() { return formato; }
    public void setFormato(String formato) { this.formato = formato; }

    @Override
    public String toString() {
        return super.toString() + String.format(" - Duración: %d min - Narrador: %s - Formato: %s",
                duracionMinutos, narrador, formato);
    }
}
