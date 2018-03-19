/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udb.sv.vistas;

import com.udb.sv.controlador.TeamCtrl;
import com.udb.sv.modelos.Team;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JMagoSV
 */
@WebServlet(name = "EquiposServ", urlPatterns = {"/EquiposServ"})
public class EquiposServ extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        boolean esValido = request.getMethod().equals("POST");
        String mens = "";
        if(!esValido)
        {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
        else
        {
            String CRUD = request.getParameter("btonEqui").trim();
            if(CRUD.equals("Guardar"))
            {
                if(new TeamCtrl().save(request.getParameter("nomb"), request.getParameter("desc")))
                {
                    mens = "Datos guardados";
                }
                else
                {
                    mens = "Error al guardar equipos";
                }
            }else if(CRUD.equals("Modificar"))
            {
                if(new TeamCtrl().update(Integer.parseInt(request.getParameter("codi")),request.getParameter("nomb"), request.getParameter("desc")))
                {
                    mens = "Datos modificados";
                    request.setAttribute("estaModi", "false"); //Esta modificando
                }
                else
                {
                    mens = "Error al modificar equipos";
                    request.setAttribute("estaModi", "false"); //Esta modificando
                }
            }
            else if(CRUD.equals("Consultar"))
            {
                int codi = Integer.parseInt(request.getParameter("codiEquiRadi") == null ? "-1" : 
                        request.getParameter("codiEquiRadi"));
                Team obje = new TeamCtrl().getTeams(codi);
                if(obje != null)
                {
                    request.setAttribute("codi", obje.getCodigo());
                    request.setAttribute("nomb", obje.getName());
                    request.setAttribute("desc", obje.getDes());
                    mens = "Información consultada";
                    request.setAttribute("estaModi", "true"); //Esta modificando
                }
                else
                {
                    mens = "Error al consultar equipos";
                }
            }else if (CRUD.equals("Eliminar")){
                int codi = Integer.parseInt(request.getParameter("codi") == null ? "-1" : 
                        request.getParameter("codi"));
                if(new TeamCtrl().delete(codi))
                {
                    mens = "Equipo eliminado";
                    request.setAttribute("estaModi", "false"); //Esta modificando
                }
                else
                {
                    mens = "Error al eliminar equipo";
                }
            }
            request.setAttribute("mensAler", mens);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
