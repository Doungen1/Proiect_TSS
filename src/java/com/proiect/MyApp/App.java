package com.proiect.MyApp;

import com.proiect.MyApp.Model.CabinetMedical;
import com.proiect.MyApp.Model.ConsoleUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DATABASE_USER = "postgres";
    private static final String DATABASE_PASS = "Bosma.123";

    private static Connection initDatabaseConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASS);
        } catch (SQLException e) {
            System.out.println("An SQL error occurred while establishing a database connection: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred while establishing a database connection: " + e.getMessage());
        }
        return connection;
    }

    public static void main(String[] args) throws SQLException{
        try (Connection connection = initDatabaseConnection()) {
            if (connection != null) {
                CabinetMedical cabinetMedical = new CabinetMedical();
                ConsoleUI consoleUI = new ConsoleUI(cabinetMedical);
                consoleUI.start();
            }
        }
    }
}