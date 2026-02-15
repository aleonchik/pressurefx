package fx.pressurefx.dao;

import fx.pressurefx.entity.Pressure;

import java.util.List;

public class DAOPressureImpl implements DAOPressure {
    @Override
    public List<Pressure> getAll(int userId, int count) {
        return List.of();
    }

    @Override
    public Pressure getOne(int pressureId) {
        return null;
    }

    @Override
    public void insert(Pressure pressure) {

    }

    @Override
    public void deleteOne(int pressureId) {

    }

    @Override
    public void deleteAllForUserId(int userId) {

    }
}
