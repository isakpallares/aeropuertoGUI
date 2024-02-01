package persistence.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConection;
import persistence.entity.Aeropuerto;

public class AeropuertoRepository {

    public boolean insertarAeropuerto(String nombre, String ubicacion) {
        try (Connection connection = new DBConection().getConnection()) {
            String sql = "INSERT INTO aeropuerto(nombre, ubicacion) VALUES(?,?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nombre);
                statement.setString(2, ubicacion);
                return statement.executeUpdate() > 0;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Ups... Algo salio mal: " + ex.getMessage());
        }

    }
    
    public boolean eliminarAeropuerto(String nombre) {
        try (Connection connection = new DBConection().getConnection()) {
            String sql = "DELETE FROM aeropuerto WHERE nombre = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nombre);
                return statement.executeUpdate() > 0;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Ups... Algo salio mal: " + ex.getMessage());
        }

    }
    
    

    public List<Aeropuerto> verAeropuerto() {
        try (Connection connection = new DBConection().getConnection()) {
            List<Aeropuerto> aeropuertos = new ArrayList<>();
            String sql = "SELECT * FROM aeropuerto";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                    ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    aeropuertos.add(new Aeropuerto(rs.getInt("id"), rs.getString("nombre"),
                            rs.getString("ubicacion")));
                }
            }
            return aeropuertos;
        } catch (SQLException e) {
            throw new RuntimeException("Ups... Algo salio mal: " + e.getMessage());
        }
    }

    public List<String> aeropuertos() {
        try (Connection connection = new DBConection().getConnection()) {
            List<String> aeropuertos = new ArrayList<>();
            String sql = "SELECT nombre FROM aeropuerto";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                    ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    aeropuertos.add(rs.getString("nombre"));
                }
            }
            return aeropuertos;
        } catch (SQLException e) {
            throw new RuntimeException("Ups... Algo salio mal: " + e.getMessage());
        }
    }

    public Aeropuerto buscarAeropuertoPorNombre(String nombre) {
        try (Connection connection = new DBConection().getConnection()) {
            String sql = "SELECT * FROM aeropuerto where nombre = '" + nombre + "'";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                    ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    return new Aeropuerto(rs.getInt("id"), rs.getString("nombre"), rs.getString("ubicacion"));
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Ups... Algo salio mal: " + e.getMessage());
        }
    }

    public String aeropuertoPorId(Integer id) {
        try (Connection connection = new DBConection().getConnection()) {
            String aeropuertos = null;
            String sql = "SELECT nombre FROM aeropuerto where id = " + id;
            try (PreparedStatement statement = connection.prepareStatement(sql);
                    ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    aeropuertos = rs.getString("nombre");
                }
            }
            return aeropuertos;
        } catch (SQLException e) {
            throw new RuntimeException("Ups... Algo salio mal: " + e.getMessage());
        }
    }

}
