package com.udb.sv.controlador;

import com.udb.sv.modelos.Team;
import com.udb.sv.recursos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Edwin
 */
public class TeamCtrl {

    private final Connection conn;

    public TeamCtrl() {
        this.conn = new Conexion().getConn();
    }

    public boolean save(String name, String des) {
        boolean resp = false;
        try {
            PreparedStatement cmd = this.conn.prepareStatement("insert into equipos VALUES(NULL,?,?)");
            cmd.setString(1, name);
            cmd.setString(2, des);
            cmd.executeUpdate();
            resp = true;
        } catch (Exception e) {
            System.err.println("Error al gaurdar el equipo " + e.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }

            } catch (Exception e) {
                System.err.println("Error al cerrar la conexión " + e.getMessage());
            }
        }
        return resp;
    }
    public boolean update(int codigo,String name, String des) {
        boolean resp = false;
        try {
            PreparedStatement cmd = this.conn.prepareStatement("update  equipos set nomb_equi= ? , desc_equi= ? where codi_equi= ?");
            cmd.setString(1, name);
            cmd.setString(2, des);
            cmd.setInt(3, codigo);
            cmd.executeUpdate();
            resp = true;
        } catch (Exception e) {
            System.err.println("Error al modificar el equipo " + e.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }

            } catch (Exception e) {
                System.err.println("Error al cerrar la conexión " + e.getMessage());
            }
        }
        return resp;
    }
       public boolean delete(int codigo) {
        boolean resp = false;
        try {
            PreparedStatement cmd = this.conn.prepareStatement("delete from equipos where codi_equi= ?");
            cmd.setInt(1, codigo);
            cmd.executeUpdate();
            resp = true;
        } catch (Exception e) {
            System.err.println("Error al eliminar el equipo " + e.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }

            } catch (Exception e) {
                System.err.println("Error al cerrar la conexión " + e.getMessage());
            }
        }
        return resp;
    }
    public List<Team> getTeams() {
        List<Team> teams = new ArrayList<>();
        try {
            ResultSet result = this.conn.createStatement().executeQuery("select * from equipos");
            while (result.next()) {
                Team team = new Team();
                team.setCodigo(result.getInt(1));
                team.setName(result.getString(2));
                team.setDes(result.getString(3));
                teams.add(team);
            }
           
        } catch (SQLException e) {
            System.err.println("Error al obtener equipos" + e.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }

            } catch (Exception e) {
                System.err.println("Error al cerrar la conexión " + e.getMessage());
            }
        }
         return teams;
    }
    public Team getTeams(int codigo) {
          Team team = new Team();
        try {
            
            PreparedStatement cmd = this.conn.prepareStatement("select * from equipos where codi_equi = ?");
            cmd.setInt(1, codigo);
            ResultSet result = cmd.executeQuery();
            
            if (result.next()) {
                team.setCodigo(result.getInt(1));
                team.setName(result.getString(2));
                team.setDes(result.getString(3));
            }
           
        } catch (SQLException e) {
            System.err.println("Error al obtener equipos" + e.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }

            } catch (Exception e) {
                System.err.println("Error al cerrar la conexión " + e.getMessage());
            }
        }
         return team;
    }
}
