package fx.pressurefx.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    private final String DB_DRIVER_KEY    = "db.driver";
    private final String DB_URL_KEY       = "db.url";
    private final String DB_USER_NAME_KEY = "db.user";
    private final String DB_PASS_KEY      = "db.pass";

    public Connection getConnection() {
        try {
            Class.forName(PropertiesUtil.get(DB_DRIVER_KEY));
            Connection conn = DriverManager.getConnection(
                    PropertiesUtil.get(DB_URL_KEY),
                    PropertiesUtil.get(DB_USER_NAME_KEY),
                    PropertiesUtil.get(DB_PASS_KEY));
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
