package com.example.travelagency.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnDB {
    private Connection con;
    private Statement st;

    public Connection getCon() {
        return con;
    }

    public Statement getSt() {
        return st;
    }

    public ConnDB() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "");
            st = con.createStatement();
            System.out.println("Connection Established !");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    //close connection
    public void close() throws SQLException {
        con.close();
    }
}
