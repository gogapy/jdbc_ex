package com.jdbc_ex;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Select {

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

            // ResultSet rs = getPersonas(conn);
            ResultSet rs = getPersonaById(conn, 2);

            while (rs.next()) {
                System.out.println("id: " + rs.getInt(1)
                        + "\nnombre: " + rs.getString(2)
                        + "\nedad: " + rs.getInt(3) + "\n");
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static ResultSet getPersonas(Connection conn) throws SQLException {
        String select = "SELECT * FROM persona";

        PreparedStatement ps = conn.prepareStatement(select);

        return ps.executeQuery();
    }

    private static ResultSet getPersonaById(Connection conn, int id) throws SQLException {
        String select = "SELECT * FROM persona WHERE id = ?";

        PreparedStatement ps = conn.prepareStatement(select);
        ps.setInt(1, id);

        return ps.executeQuery();
    }

}
