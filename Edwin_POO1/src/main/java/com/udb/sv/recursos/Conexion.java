package com.udb.sv.recursos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

/**
 *
 * @author moises
 */
public class Conexion {

    private Connection conn;
    private String url, user, pass;

    public Connection getConn() {
        try {
            if (this.getDataConnection()) {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                this.conn = DriverManager.getConnection(this.url, this.user, this.pass);
            }
        } catch (Exception e) {
            System.err.println("Error al conectar " + e.getMessage());
        }
        return conn;
    }

    private boolean getDataConnection() {
        boolean resp = false;
        try {
            Properties prop = new Properties();
            try (InputStream file = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
                prop.load(file);
                this.url = prop.getProperty("url");
                this.user = prop.getProperty("user");
                this.pass = prop.getProperty("password");
                resp = true;
            }
        } catch (Exception e) {
            System.err.println("Error al leer la información del archivo de configuración " + e.getMessage());
        }
        return resp;
    }
}
