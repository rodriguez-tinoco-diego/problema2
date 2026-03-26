import java.time.LocalDate;
import java.util.UUID;

public class Prestamo {
    private String idPrestamo;
    private Persona persona;
    private Material material;
    private LocalDate fechaSolicitud;
    private LocalDate fechaDevolucion; // null si aún no se devuelve

    public Prestamo(Persona persona, Material material, LocalDate fechaSolicitud) {
        this.idPrestamo = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.persona = persona;
        this.material = material;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaDevolucion = null;
    }

    public String getIdPrestamo() { return idPrestamo; }
    public Persona getPersona() { return persona; }
    public Material getMaterial() { return material; }
    public LocalDate getFechaSolicitud() { return fechaSolicitud; }
    public LocalDate getFechaDevolucion() { return fechaDevolucion; }

    public void devolver(LocalDate fechaDevolucion) {
        if (this.fechaDevolucion != null) {
            throw new IllegalStateException("Este préstamo ya fue devuelto.");
        }
        this.fechaDevolucion = fechaDevolucion;
        material.devolver(); // cambia estado a DISPONIBLE
    }

    public boolean estaActivo() {
        return fechaDevolucion == null;
    }

    @Override
    public String toString() {
        return String.format("Préstamo %s: %s → %s (%s) - Solicitud: %s, Devolución: %s",
                idPrestamo, persona.getNombre(), material.getTitulo(),
                material.getCodigo(), fechaSolicitud,
                fechaDevolucion != null ? fechaDevolucion : "Pendiente");
    }
}