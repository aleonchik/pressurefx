package fx.pressurefx.dao;

import fx.pressurefx.entity.Patient;

import java.util.List;

public interface DAOPatient {
    List<Patient> getAll();
    Patient getOne(int id);
    int insert(Patient patient);
    void update(Patient patient);
    void delete(int userId);
}
