package biblioteca;
import java.time.LocalDate;
import java.time.LocalTime;
public class Reserva {
    private final String codigo;
    private final Estudiante estudiante;
    private final LocalDate fecha;
    private final LocalTime horaInicio;
    private final int duracionHoras;
    private final int CantidadPersonas;
    private final Sala sala;
    private EstadoReserva estado;

    Reserva(String codigo, Estudiante estudiante, LocalDate fecha, LocalTime horaInicio, int duracionHoras, int CantidadPersonas, Sala sala) {
        if(codigo == null || codigo.isBlank() || estudiante == null || fecha == null || horaInicio == null || duracionHoras <= 0 || CantidadPersonas <= 0 || sala == null) {
            throw new IllegalArgumentException("Datos de la reserva invalidos");
        }
        this.codigo = codigo;
        this.estudiante = estudiante;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.duracionHoras = duracionHoras;
        this.CantidadPersonas = CantidadPersonas;
        this.sala = sala;
        this.estado = new Pendiente(); // Estado inicial
    }

    public String getCodigo() {
        return codigo;
    }
    public Estudiante getEstudiante() {
        return estudiante;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public LocalTime getHoraInicio() {
        return horaInicio;
    }
    public int getDuracionHoras() {
        return duracionHoras;
    }
    public int getCantidadPersonas() {
        return CantidadPersonas;
    }
    public Sala getSala() {
        return sala;
    }
    public String getNombreEstado() {
        return estado.nombre();
    }

    public void confirmar() {
        estado.confirmar(this);
    }
    public void cancelar() {
        estado.cancelar(this);
    }
    public void finalizar() {
        estado.finalizar(this);
    }

    void cambiarEstado(EstadoReserva nuevoEstado) {
        this.estado = nuevoEstado;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "codigo='" + codigo + '\'' +
                ", estudiante=" + estudiante.getNombre() +
                ", fecha=" + fecha +
                ", horaInicio=" + horaInicio +
                ", duracionHoras=" + duracionHoras +
                ", CantidadPersonas=" + CantidadPersonas +
                ", sala=" + sala.getNombre() +
                ", estado=" + estado.nombre() +
                '}';
    }
}
