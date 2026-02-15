package fx.pressurefx.dao;

import fx.pressurefx.entity.Pressure;

import java.util.List;

public interface DAOPressure {
    List<Pressure> getAll(int userId, int count);
    Pressure getOne(int pressureId);
    void insert(Pressure pressure);
    void deleteOne(int pressureId);
    void deleteAllForUserId(int userId);
}
