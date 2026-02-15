package fx.pressurefx.dao;

import fx.pressurefx.entity.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOPatientImpl implements DAOPatient {

    protected Connection conn = new DBConnect().getConnection();

    @Override
    public List<Patient> getAll() {
        String sql = "SELECT id, name, birth FROM patient ORDER BY name";
        List<Patient> allPatient = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                allPatient.add(new Patient(rs.getInt(1), rs.getString(2), rs.getDate(3).toLocalDate()));
            }

            if (!rs.isClosed())
                rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allPatient;
    }

    @Override
    public Patient getOne(int id) {
        String sql = "SELECT id, name, birth FROM patient WHERE id = " + id;

        Patient patient = null;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                patient = new Patient(rs.getInt(1), rs.getString(2), rs.getDate(3).toLocalDate());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return patient;
    }

    @Override
    public int insert(Patient patient) {
        String sql = "INSERT INTO patient (id, name, birth) VALUES (DEFAULT, ?, ?)";
        int numRows;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            // Приводим дату к нужному формату
            java.sql.Date sqlDate = java.sql.Date.valueOf(patient.birthday());
            ps.setString(1, patient.name());
            ps.setDate(2, sqlDate);
            numRows = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return numRows;
    }

    @Override
    public void update(Patient patient) {
        String sql = "UPDATE patient SET name = ?, birth=? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            java.sql.Date sqlDate = java.sql.Date.valueOf(patient.birthday());
            ps.setString(1, patient.name());
            ps.setDate(2, sqlDate);
            ps.setInt(3, patient.id());
            int numRows = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int userId) {
        String sql = "DELETE FROM patient WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
