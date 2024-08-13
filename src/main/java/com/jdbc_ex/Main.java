package com.jdbc_ex;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbc_ex.Dao.Imp.PersonaDaoImp;
import com.jdbc_ex.Entities.Persona;

public class Main {

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
            PersonaDaoImp personaDaoImp = new PersonaDaoImp();

            // personaDaoImp.createTables(conn);
            // personaDaoImp.agregarPersona(conn, new Persona("Juan", 27));
            // personaDaoImp.agregarPersona(conn, new Persona("Carla", 32));

            ResultSet rs = personaDaoImp.getPersonas(conn);

            while (rs.next()) {
                System.out.println();
                System.out.println("id: " + rs.getLong(1)
                        + "\nnombre: " + rs.getString(2)
                        + "\nedad: " + rs.getInt(3) + "\n");
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
