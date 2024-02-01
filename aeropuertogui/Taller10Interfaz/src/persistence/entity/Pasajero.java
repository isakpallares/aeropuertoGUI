package persistence.entity;

public class Pasajero {
    private int id;
    private String nombre;
    private String numeroPasaporte;
    private double cantidadEquipaje;
    private int puesto;
    private int numVuelo;

    public Pasajero(int id, String nombre, String numeroPasaporte, Integer cantidadEquipaje) {
        this.id = id;
        this.nombre = nombre;
        this.numeroPasaporte = numeroPasaporte;
        this.cantidadEquipaje = cantidadEquipaje;
    }

    public Pasajero(int id, String nombre, String numeroPasaporte, Integer cantidadEquipaje, int puesto, int numVuelo) {
        this.id = id;
        this.nombre = nombre;
        this.numeroPasaporte = numeroPasaporte;
        this.cantidadEquipaje = cantidadEquipaje;
        this.puesto = puesto;
        this.numVuelo = numVuelo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumeroPasaporte() {
        return numeroPasaporte;
    }

    public double getCantidadEquipaje() {
        return cantidadEquipaje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumeroPasaporte(String numeroPasaporte) {
        this.numeroPasaporte = numeroPasaporte;
    }

    public void setCantidadEquipaje(double cantidadEquipaje) {
        this.cantidadEquipaje = cantidadEquipaje;
    }

    public int getPuesto() {
        return puesto;
    }

    public void setPuesto(int puesto) {
        this.puesto = puesto;
    }

    public int getNumVuelo() {
        return numVuelo;
    }

    public void setNumVuelo(int numVuelo) {
        this.numVuelo = numVuelo;
    }

}
