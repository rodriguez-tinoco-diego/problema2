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


    public void registrarPersona(Persona persona) {
        if (persona == null) throw new IllegalArgumentException("Persona no puede ser nula");
        personas.add(persona);
    }

    public void registrarMaterial(Material material) {
        if (material == null) throw new IllegalArgumentException("Material no puede ser nulo");
        catalogo.add(material);
    }


    public boolean prestarMaterial(String idPersona, String codigoMaterial, LocalDate fecha) {

        Persona persona = buscarPersona(idPersona);
        if (persona == null) {
            System.out.println("Persona no encontrada.");
            return false;
        }


        Material material = buscarMaterial(codigoMaterial);
        if (material == null) {
            System.out.println("Material no encontrado.");
            return false;
        }


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


        material.prestar(); // cambia estado a PRESTADO
        Prestamo prestamo = new Prestamo(persona, material, fecha);
        prestamos.add(prestamo);
        System.out.println("Préstamo realizado: " + prestamo.getIdPrestamo());
        return true;
    }


    public boolean devolverMaterial(String idPersona, String codigoMaterial, LocalDate fecha) {

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


    public List<Prestamo> consultarPrestamosActivos(String idPersona) {
        return prestamos.stream()
                .filter(p -> p.getPersona().getIdPersona().equals(idPersona) && p.estaActivo())
                .toList();
    }


    public void mostrarPrestamosCompletos() {
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
            return;
        }
        for (Prestamo p : prestamos) {
            System.out.println(p);
        }
    }


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
