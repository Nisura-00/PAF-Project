package com.electrogrid.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created By Rashani
 * Date: 4/18/2022
 */
public class DBUtil {

    private static Connection connection;
    public static final String URL = "jdbc:mysql://localhost:3306/unit_management_service?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "Atlaspitu80thanirule";

    public DBUtil() {
    }

    public static Connection connect() throws ClassNotFoundException, SQLException {

        if (connection == null || connection.isClosed()) {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }

        return connection;
    }
}
