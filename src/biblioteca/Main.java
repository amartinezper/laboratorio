package biblioteca;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        RepositorioSalasMemoria salas = new RepositorioSalasMemoria();
        RepositorioReservasMemoria reservas = new RepositorioReservasMemoria();
        ReservaFacade fachada = new ReservaFacade(salas, reservas);

        System.out.println("=== Reserva por capacidad (4 personas) ===");
        fachada.realizarReserva(
            new Estudiante("E001", "Laura"),
            LocalDate.now().plusDays(7),
            LocalTime.of(14, 0),
            2,
            4,
            new SeleccionPorCapacidad()
        ).ifPresent(System.out::println);

        System.out.println("\n=== Sala preferida C (4 personas) ===");
        fachada.realizarReserva(
            new Estudiante("E002", "Andres"),
            LocalDate.now().plusDays(8),
            LocalTime.of(10, 0),
            2,
            4,
            new SeleccionSalaPreferida("C")
        ).ifPresent(System.out::println);

        System.out.println("\n=== Sin sala adecuada (10 personas) ===");
        fachada.realizarReserva(
            new Estudiante("E003", "Camila"),
            LocalDate.now().plusDays(9),
            LocalTime.of(9, 0),
            2,
            10,
            new SeleccionPorCapacidad()
        ).ifPresent(System.out::println);

        System.out.println("\nReservas guardadas: " + reservas.obtenerTodas().size());
    }
}

