package fx.pressurefx.dao;

import fx.pressurefx.entity.Pressure;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DAOPressureImpl implements DAOPressure {
    protected Connection conn = new DBConnect().getConnection();

    @Override
    public List<Pressure> getAll(int userId, int count) {
        String sql = "SELECT id, pid, sys, dia, pulse, dt FROM pressure WHERE pid = ? LIMIT ?";
        List<Pressure> lp = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, count);
            ResultSet rs = ps.executeQuery();

            while ( rs.next() ) {
                lp.add(new Pressure(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getTimestamp(6).toLocalDateTime()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lp;
    }

    @Override
    public Pressure getOne(int pressureId) {
        return null;
    }

    @Override
    public void insert(Pressure pressure) {
        String sql = "INSERT INTO pressure (id, pid, sys, dia, pulse, dt) VALUES (DEFAULT, ?, ?, ?, ?, ?)";
//        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy H:m");
        java.sql.Timestamp tStamp = java.sql.Timestamp.valueOf(pressure.getDt());

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, pressure.getPid());
            ps.setInt(2, pressure.getSys());
            ps.setInt(3, pressure.getDia());
            ps.setInt(4, pressure.getPulse());
            ps.setTimestamp(5, tStamp);
            int insertedRows =  ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOne(int pressureId) {

    }

    @Override
    public void deleteAllForUserId(int userId) {

    }
}
