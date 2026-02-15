package fx.pressurefx.entity;

import javafx.beans.property.SimpleIntegerProperty;

import java.time.LocalDateTime;

public class Pressure {
    private int id;
    private int pid;
    private int sys;
    private int dia;
    private int pulse;
    private LocalDateTime dt;

    public Pressure() { }

    public Pressure(int id, int pid, int sys, int dia, int pulse, LocalDateTime dt) {
        this.id = id;
        this.pid = pid;
        this.sys = sys;
        this.dia = dia;
        this.pulse = pulse;
        this.dt = dt;
    }

    public int getId()                  { return id; }
    public void setId(int id)           { this.id = id; }

    public int getPid()                 { return pid; }
    public void setPid(int pid)         { this.pid = pid; }

    public int getSys()                 { return sys; }
    public void setSys(int sys)         { this.sys = sys; }

    public int getDia()                 { return dia; }
    public void setDia(int dia)         { this.dia = dia; }

    public int getPulse()               { return pulse; }
    public void setPulse(int pulse)     { this.pulse = pulse; }

    public LocalDateTime getDt()        { return dt; }
    public void setDt(LocalDateTime dt) { this.dt = dt; }

    @Override
    public String toString() {
        return "Pressure{" +
                "id=" + id +
                ", pid=" + pid +
                ", sys=" + sys +
                ", dia=" + dia +
                ", pulse=" + pulse +
                ", dt=" + dt +
                '}';
    }
}
