package com.jdbc_ex;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BaseDeDatos {

    public static void main(String[] args) {
        // el valor del string deberia venir de algun lado, aca se esta hardcodeando
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();

            // si el driver falla deberiamos salir del programa
            System.exit(1);
        }

        String uri = "jdbc:derby:MyDerbyDb;create=true";

        try {
            Connection conn = DriverManager.getConnection(uri);

            createTables(conn);
            agregarPersona(conn, 1, "Juan", 21);
            agregarPersona(conn, 2, "Paula", 32);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTables(Connection conn) throws SQLException {
        String table = "CREATE TABLE persona(id INT, nombre VARCHAR(18), edad INT, PRIMARY KEY(id))";
        conn.prepareStatement(table).execute();

        conn.commit();
    }

    private static void agregarPersona(Connection conn, int id, String nombre, int edad) throws SQLException {
        String insert = "INSERT INTO persona (id, nombre, edad) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(insert);

        ps.setInt(1, id);
        ps.setString(2, nombre);
        ps.setInt(3, edad);
        ps.executeUpdate();

        ps.close();

        conn.commit();
    }
}
