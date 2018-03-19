/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udb.sv.controlador;

import com.udb.sv.modelos.Player;
import com.udb.sv.modelos.Team;
import com.udb.sv.recursos.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerCtrl {

    private final Connection conn;

    public PlayerCtrl() {
        this.conn = new Conexion().getConn();
    }

    public boolean save(int codiEqui, String nombJuga, int edadJuga, int altuJuga, int pesoJuga) {
        boolean resp = false;
        try {
            PreparedStatement cmd = this.conn.prepareStatement("insert into jugadores VALUES(NULL,?,?,?,?,?)");
            cmd.setInt(1, codiEqui);
            cmd.setString(2, nombJuga);
            cmd.setInt(3, edadJuga);
            cmd.setInt(4, altuJuga);
            cmd.setInt(5, pesoJuga);
            cmd.executeUpdate();
            resp = true;
        } catch (SQLException ex) {
            System.err.println("Error al guardar JUgador: " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión");
            }
        }
        return resp;
    }

    public boolean update(int codigoPlayer, int codiEqui, String nombJuga, int edadJuga, int altuJuga, int pesoJuga) {

        boolean resp = false;
        try {
            PreparedStatement cmd = this.conn.prepareStatement("update jugadores  SET codi_equi=?,nomb_juga=?,edad_juga=?,altu_juga=?,peso_juga=? WHERE codi_juga= ?");
            cmd.setInt(1, codiEqui);
            cmd.setString(2, nombJuga);
            cmd.setInt(3, edadJuga);
            cmd.setInt(4, altuJuga);
            cmd.setInt(5, pesoJuga);
            cmd.setInt(6, codigoPlayer);
            cmd.executeUpdate();
            resp = true;
        } catch (SQLException ex) {
            System.err.println("Error al modificar jugador: " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión");
            }
        }
        return resp;
    }

    public boolean delete(int codigo) {
        boolean resp = false;
        try {
            PreparedStatement cmd = this.conn.prepareStatement("delete from jugadores where codi_juga= ?");
            cmd.setInt(1, codigo);
            cmd.executeUpdate();
            resp = true;
        } catch (Exception e) {
            System.err.println("Error al eliminar el jugador " + e.getMessage());
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

    public List<Player> getPlayers() {
        List<Player> resp = new ArrayList<>();
        try {
            PreparedStatement cmd = this.conn.prepareStatement("SELECT j.codi_juga, e.*, j.nomb_juga, j.edad_juga, j.altu_juga, j.peso_juga FROM jugadores j INNER JOIN equipos e ON j.codi_equi = e.codi_equi;");
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                Player player = new Player();
                player.setCodigo(rs.getInt(1));
                player.setName(rs.getString(5));
                player.setAge(rs.getInt(6));
                player.setHeight(rs.getInt(7));
                player.setWeight(rs.getInt(8));

                Team team = new Team();
                team.setCodigo(rs.getInt(2));
                team.setName(rs.getString(3));
                team.setDes(rs.getString(4));

                player.setCodigoEquipo(team);

                resp.add(player); // <----- Hay que llenar con los objetos
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar Jugadores: " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión");
            }
        }
       
        return resp;
    }
    public Player getPlayers(int codi) {
         Player player = new Player();
        try {
            PreparedStatement cmd = this.conn.prepareStatement("SELECT j.codi_juga, e.*, j.nomb_juga, j.edad_juga, j.altu_juga, j.peso_juga FROM jugadores j INNER JOIN equipos e ON j.codi_equi = e.codi_equi where codi_juga=?;");
            cmd.setInt(1, codi);
            ResultSet rs = cmd.executeQuery();
           if (rs.next()) {
              
                player.setCodigo(rs.getInt(1));
                player.setName(rs.getString(5));
                player.setAge(rs.getInt(6));
                player.setHeight(rs.getInt(7));
                player.setWeight(rs.getInt(8));

                Team team = new Team();
                team.setCodigo(rs.getInt(2));
                team.setName(rs.getString(3));
                team.setDes(rs.getString(4));

                player.setCodigoEquipo(team);

                
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar Jugadores: " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión");
            }
        }
       
        return   player;
    }

}
