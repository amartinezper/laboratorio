package biblioteca;

public class Estudiante {
    private final String identificador;
    private final String nombre;

    public Estudiante(String identificador, String nombre) {
        if(identificador == null || identificador.isBlank() || nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El identificador y el nombre no pueden ser nulos o vacíos");
        }
        this.identificador = identificador;
        this.nombre = nombre;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getNombre() {
        return nombre;
    }
}
