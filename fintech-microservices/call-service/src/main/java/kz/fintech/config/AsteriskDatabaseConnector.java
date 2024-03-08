package kz.fintech.config;
import kz.fintech.props.CallProps;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class AsteriskDatabaseConnector {
    private static CallProps callProps;

    public Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:/home/fintech/cdr/cdr.db");
            //return DriverManager.getConnection("jdbc:sqlite:D:/cdr.db");
            //return DriverManager.getConnection(callProps.getUrl());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database.");
        }
    }
//    public static Connection getConnection() {
//        return connection;
//    }
}
