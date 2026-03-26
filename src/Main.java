import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        // Registrar personas
        Persona p1 = new Persona("P001", "Ana López", "INE123", "555-1111", "Calle 1");
        Persona p2 = new Persona("P002", "Juan Pérez", "INE456", "555-2222", "Calle 2");
        biblioteca.registrarPersona(p1);
        biblioteca.registrarPersona(p2);

        // Registrar materiales
        Libro libro = new Libro("L001", "Cien años de soledad", "García Márquez", 1967, 432, "Novela", "Sudamericana");
        Revista revista = new Revista("R001", "National Geographic", "Varios", 2023, 80, 345, "Marzo");
        Audiolibro audio = new Audiolibro("A001", "El Principito", "Saint-Exupéry", 1943, 96, 120, "Pedro Ruiz", "MP3");

        biblioteca.registrarMaterial(libro);
        biblioteca.registrarMaterial(revista);
        biblioteca.registrarMaterial(audio);

        // Realizar préstamos
        biblioteca.prestarMaterial("P001", "L001", LocalDate.now());
        biblioteca.prestarMaterial("P001", "R001", LocalDate.now());
        biblioteca.prestarMaterial("P001", "A001", LocalDate.now()); // debería ser ok (3 activos)
        biblioteca.prestarMaterial("P001", "L001", LocalDate.now()); // ya no disponible
        biblioteca.prestarMaterial("P001", "L001", LocalDate.now()); // debería fallar por límite

        // Consultar activos
        System.out.println("\nPréstamos activos de Ana López:");
        biblioteca.consultarPrestamosActivos("P001").forEach(System.out::println);

        // Devolver uno
        biblioteca.devolverMaterial("P001", "L001", LocalDate.now());

        // Mostrar histórico
        System.out.println("\nHistorial completo de préstamos:");
        biblioteca.mostrarPrestamosCompletos();
    }
}
