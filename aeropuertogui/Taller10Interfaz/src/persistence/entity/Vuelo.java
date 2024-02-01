package persistence.entity;

public class Vuelo {
    private int id;
    private int numeroVuelo;
    private String aerolinea;
    private String horaSalida;
    private String destino;
    private int capacidadMaxima;
    private String nombreAirIda;
    private String nombreAirDest;

    public Vuelo(int id, int numeroVuelo, String aerolinea, String horaSalida, String destino, int capacidadMaxima) {
        this.id = id;
        this.numeroVuelo = numeroVuelo;
        this.aerolinea = aerolinea;
        this.horaSalida = horaSalida;
        this.destino = destino;
        this.capacidadMaxima = capacidadMaxima;
    }

    public Vuelo(int id, int numeroVuelo, String aerolinea, String horaSalida, String destino, int capacidadMaxima,
            String nombreAirIda, String nombreAirDest) {
        this.id = id;
        this.numeroVuelo = numeroVuelo;
        this.aerolinea = aerolinea;
        this.horaSalida = horaSalida;
        this.destino = destino;
        this.capacidadMaxima = capacidadMaxima;
        this.nombreAirDest = nombreAirDest;
        this.nombreAirIda = nombreAirIda;
    }

    public int getNumeroVuelo() {
        return numeroVuelo;
    }

    public void setNumeroVuelo(int numeroVuelo) {
        this.numeroVuelo = numeroVuelo;
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreAirIda() {
        return nombreAirIda;
    }

    public void setNombreAirIda(String nombreAirIda) {
        this.nombreAirIda = nombreAirIda;
    }

    public String getNombreAirDest() {
        return nombreAirDest;
    }

    public void setNombreAirDest(String nombreAirDest) {
        this.nombreAirDest = nombreAirDest;
    }

}
