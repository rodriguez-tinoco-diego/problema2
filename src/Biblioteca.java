import java.time.LocalDate;
import java.util.*;

public class Biblioteca {
    private List<Persona> personas;
    private List<Material> catalogo;
    private List<Prestamo> prestamos;

    public Biblioteca() {
        this.personas = new ArrayList<>();
        this.catalogo = new ArrayList<>();
        this.prestamos = new ArrayList<>();
    }

    // Registro
    public void registrarPersona(Persona persona) {
        if (persona == null) throw new IllegalArgumentException("Persona no puede ser nula");
        personas.add(persona);
    }

    public void registrarMaterial(Material material) {
        if (material == null) throw new IllegalArgumentException("Material no puede ser nulo");
        catalogo.add(material);
    }

    // Préstamo
    public boolean prestarMaterial(String idPersona, String codigoMaterial, LocalDate fecha) {
        // Buscar persona
        Persona persona = buscarPersona(idPersona);
        if (persona == null) {
            System.out.println("Persona no encontrada.");
            return false;
        }

        // Buscar material
        Material material = buscarMaterial(codigoMaterial);
        if (material == null) {
            System.out.println("Material no encontrado.");
            return false;
        }

        // Verificar disponibilidad
        if (!material.estaDisponible()) {
            System.out.println("El material no está disponible.");
            return false;
        }


        long activos = prestamos.stream()
                .filter(p -> p.getPersona().getIdPersona().equals(idPersona) && p.estaActivo())
                .count();
        if (activos >= 3) {
            System.out.println("La persona ya tiene 3 préstamos activos. No se puede prestar más.");
            return false;
        }

        // Realizar préstamo
        material.prestar(); // cambia estado a PRESTADO
        Prestamo prestamo = new Prestamo(persona, material, fecha);
        prestamos.add(prestamo);
        System.out.println("Préstamo realizado: " + prestamo.getIdPrestamo());
        return true;
    }

    // Devolución
    public boolean devolverMaterial(String idPersona, String codigoMaterial, LocalDate fecha) {
        // Buscar préstamo activo que coincida con persona y material
        Prestamo prestamo = prestamos.stream()
                .filter(p -> p.getPersona().getIdPersona().equals(idPersona) &&
                        p.getMaterial().getCodigo().equals(codigoMaterial) &&
                        p.estaActivo())
                .findFirst()
                .orElse(null);

        if (prestamo == null) {
            System.out.println("No se encontró un préstamo activo para esta persona y material.");
            return false;
        }

        prestamo.devolver(fecha);
        System.out.println("Devolución registrada.");
        return true;
    }

    // Consulta de préstamos activos de una persona
    public List<Prestamo> consultarPrestamosActivos(String idPersona) {
        return prestamos.stream()
                .filter(p -> p.getPersona().getIdPersona().equals(idPersona) && p.estaActivo())
                .toList();
    }

    // Mostrar todos los préstamos (histórico)
    public void mostrarPrestamosCompletos() {
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
            return;
        }
        for (Prestamo p : prestamos) {
            System.out.println(p);
        }
    }

    // Métodos auxiliares privados
    private Persona buscarPersona(String idPersona) {
        return personas.stream()
                .filter(p -> p.getIdPersona().equals(idPersona))
                .findFirst()
                .orElse(null);
    }

    private Material buscarMaterial(String codigoMaterial) {
        return catalogo.stream()
                .filter(m -> m.getCodigo().equals(codigoMaterial))
                .findFirst()
                .orElse(null);
    }
}
