package com.jdbc_ex.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbc_ex.Entities.Persona;

public interface PersonaDao {
    public void createTables(Connection conn) throws SQLException;
    public void agregarPersona(Connection conn, Persona persona) throws SQLException;
    public ResultSet getPersonas(Connection conn) throws SQLException;
    public ResultSet getPersonaById(Connection conn, Long id) throws SQLException;
}