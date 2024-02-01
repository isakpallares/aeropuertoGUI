package persistence.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConection;
import persistence.entity.Pasajero;
import persistence.entity.Vuelo;

public class PasajeroRepository {

    public boolean insertarPasajero(String nombre, String pasaporte, String equipaje, Integer numVuelo,
            Integer puesto, String uuid) {
        VueloRepository repository = new VueloRepository();
        Vuelo vuelo = repository.obtenerVueloPorNumero(numVuelo);
        try (Connection connection = new DBConection().getConnection()) {
            String sql = "INSERT INTO pasajeros(nombre, pasaporte, equipaje, vuelo_id, puesto, uuid) VALUES(?,?,?, ?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nombre);
                statement.setString(2, pasaporte);
                statement.setString(3, equipaje);
                statement.setInt(4, vuelo.getId());
                statement.setInt(5, puesto);
                statement.setString(6, uuid);
                return statement.executeUpdate() > 0;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Ups... Algo salio mal: " + ex.getMessage());
        }

    }

    public List<Pasajero> verPasajeros() {
        try (Connection connection = new DBConection().getConnection()) {
            List<Pasajero> pasajeros = new ArrayList<>();
            String sql = "SELECT * FROM pasajeros";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                    ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    pasajeros.add(new Pasajero(rs.getInt("id"), rs.getString("nombre"),
                            rs.getString("pasaporte"), rs.getInt("equipaje")));
                }
            }
            return pasajeros;
        } catch (SQLException e) {
            throw new RuntimeException("Ups... Algo salio mal: " + e.getMessage());
        }
    }

    public Pasajero verPasajeroPorIdViaje(String idViaje) {
        try (Connection connection = new DBConection().getConnection()) {
            Pasajero pasajero = null;
            String sql = "select p.id, p.nombre, p.pasaporte, p.equipaje, v.num_vuelo, p.puesto from pasajeros p \r\n" + //
                    "left join vuelos v on v.id = p.vuelo_id \r\n" + //
                    "where uuid = '" + idViaje + "'";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                    ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    pasajero = new Pasajero(rs.getInt("id"), rs.getString("nombre"),
                            rs.getString("pasaporte"), rs.getInt("equipaje"), rs.getInt("puesto"),
                            rs.getInt("num_vuelo"));
                }
            }
            return pasajero;
        } catch (SQLException e) {
            throw new RuntimeException("Ups... Algo salio mal: " + e.getMessage());
        }
    }

}
