package persistence.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConection;
import persistence.entity.Aeropuerto;
import persistence.entity.Vuelo;

public class VueloRepository {

    public boolean crearVuelo(int numVuelo, String aerolinea, String horaSalida, String destino, int capacidadMaxima,
            String nombreAirIda, String nombreAirDestino) {
        AeropuertoRepository aeropuertoRepository = new AeropuertoRepository();
        Aeropuerto airIda = aeropuertoRepository.buscarAeropuertoPorNombre(nombreAirIda);
        Aeropuerto airDest = aeropuertoRepository.buscarAeropuertoPorNombre(nombreAirDestino);
        try (Connection connection = new DBConection().getConnection()) {
            String sql = "INSERT INTO vuelos(num_vuelo, aerolinea, hora_salida, destino, capacidad_max, aeropuerto_id, aeropuerto_destino_id) VALUES(?,?,?,?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, numVuelo);
                statement.setString(2, aerolinea);
                statement.setString(3, horaSalida);
                statement.setString(4, destino);
                statement.setInt(5, capacidadMaxima);
                statement.setInt(6, airIda.getId());
                statement.setInt(7, airDest.getId());
                return statement.executeUpdate() > 0;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Ups... Algo salio mal: " + ex.getMessage());
        }
    }

    public List<Vuelo> seguimientoVuelos() {
        try (Connection connection = new DBConection().getConnection()) {
            List<Vuelo> vuelos = new ArrayList<>();
            String sql = "SELECT * FROM vuelos";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                    ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    // vuelos.add(new Vuelo(rs.getInt("id"), rs.getInt("numVuelo"),
                    // rs.getString("aerolinea"),
                    // rs.getString("horaSalida"), rs.getString("Destino"),
                    // rs.getInt("capacidadMax")));
                }
            }
            return vuelos;
        } catch (SQLException e) {
            throw new RuntimeException("Ups... Algo salio mal: " + e.getMessage());
        }
    }

    public List<String> vuelosDisponibles() {
        try (Connection connection = new DBConection().getConnection()) {
            List<String> vuelos = new ArrayList<>();
            String sql = "SELECT num_vuelo FROM vuelos";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                    ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    vuelos.add(rs.getString("num_vuelo"));
                }
            }
            return vuelos;
        } catch (SQLException e) {
            throw new RuntimeException("Ups... Algo salio mal: " + e.getMessage());
        }
    }

    public Vuelo getVueloPorNumVuelo(Integer numVuelo) {
        AeropuertoRepository aeropuertoRepository = new AeropuertoRepository();
        try (Connection connection = new DBConection().getConnection()) {
            Vuelo vuelo = null;
            String sql = "SELECT * FROM vuelos where num_vuelo = " + numVuelo;
            try (PreparedStatement statement = connection.prepareStatement(sql);
                    ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    vuelo = new Vuelo(rs.getInt("id"), rs.getInt("num_vuelo"), rs.getString("aerolinea"),
                            rs.getString("hora_salida"), rs.getString("destino"), rs.getInt("capacidad_max"),
                            aeropuertoRepository.aeropuertoPorId(rs.getInt("aeropuerto_id")),
                            aeropuertoRepository.aeropuertoPorId(rs.getInt("aeropuerto_destino_id")));
                }
            }
            return vuelo;
        } catch (SQLException e) {
            throw new RuntimeException("Ups... Algo salio mal: " + e.getMessage());
        }
    }

    public Vuelo obtenerVueloPorNumero(Integer numVuelo) {
        try (Connection connection = new DBConection().getConnection()) {
            Vuelo vuelo = null;
            String sql = "SELECT * FROM vuelos where num_vuelo = " + numVuelo;
            try (PreparedStatement statement = connection.prepareStatement(sql);
                    ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    vuelo = new Vuelo(rs.getInt("id"), rs.getInt("num_vuelo"), rs.getString("aerolinea"),
                            rs.getString("hora_salida"), rs.getString("destino"), rs.getInt("capacidad_max"));
                }
            }
            return vuelo;
        } catch (SQLException e) {
            throw new RuntimeException("Ups... Algo salio mal: " + e.getMessage());
        }
    }

    public Integer obtenerCantidadVuelosPorAir(Integer idAeropuerto) {
        try (Connection connection = new DBConection().getConnection()) {
            Integer numVuelos = 0;
            String sql = "select count(1) as num_vuelos from vuelos v where v.aeropuerto_id = " + idAeropuerto;
            try (PreparedStatement statement = connection.prepareStatement(sql);
                    ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    numVuelos = rs.getInt("num_vuelos");
                }
            }
            return numVuelos;
        } catch (SQLException e) {
            throw new RuntimeException("Ups... Algo salio mal: " + e.getMessage());
        }
    }
}
