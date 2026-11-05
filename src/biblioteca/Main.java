package biblioteca;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RepositorioSalasMemoria salas = new RepositorioSalasMemoria();
        RepositorioReservasMemoria reservas = new RepositorioReservasMemoria();
        List<ObservadorReserva> observadores = List.of(
            new NotificadorEstudiante(),
            new RegistroEventos()
        );
        ReservaFacade fachada = new ReservaFacade(salas, reservas, observadores);

        System.out.println("=== 1. Confirmar una reserva pendiente ===");
        Reserva reserva = fachada.realizarReserva(
            new Estudiante("E001", "Laura"),
            LocalDate.now().plusDays(7),
            LocalTime.of(14, 0),
            2,
            4,
            new SeleccionPorCapacidad()
        ).orElseThrow();
        System.out.println("Estado: " + reserva.getNombreEstado());

        System.out.println("\n=== 2. Cancelar una reserva confirmada ===");
        reserva.cancelar();
        System.out.println("Estado: " + reserva.getNombreEstado());

        System.out.println("\n=== 3. Intentar confirmar una reserva cancelada ===");
        try {
            reserva.confirmar();
        } catch (IllegalStateException ex) {
            System.out.println("Operación rechazada: " + ex.getMessage());
        }
        System.out.println("Estado: " + reserva.getNombreEstado());

        System.out.println("\n=== 4. Finalizar otra reserva confirmada ===");
        Reserva otra = fachada.realizarReserva(
            new Estudiante("E004", "Samuel"),
            LocalDate.now().plusDays(10),
            LocalTime.of(11, 0),
            1,
            2,
            new SeleccionPorCapacidad()
        ).orElseThrow();
        otra.finalizar();
        System.out.println("Estado: " + otra.getNombreEstado());

        System.out.println("\n=== 5. Intentar cancelar una reserva finalizada ===");
        try {
            otra.cancelar();
        } catch (IllegalStateException ex) {
            System.out.println("Operación rechazada: " + ex.getMessage());
        }
        System.out.println("Estado: " + otra.getNombreEstado());
    }
}

