package persistence.entity;

public class Aeropuerto {
    private Integer id;
    private String nombre;
    private String ubicacion;

    public Aeropuerto(Integer id, String nombre, String ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + " Ubicacion: " + ubicacion;
    }
}
