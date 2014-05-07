/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mi6.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mi6.ejbs.RoleFacadeLocal;
import mi6.entity.Role;

/**
 *
 * @author AgtLucas
 */
@WebServlet(name = "EditRoleServlet", urlPatterns = {"/editrole"})
public class EditRoleServlet extends HttpServlet {
    
    @EJB
    RoleFacadeLocal rfl;

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
        
        String rid = request.getParameter("rid");
        String rname = request.getParameter("rname");
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditRoleServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"main\">");
            
            if (rname == null) {
                out.println("<form method=\"POST\">");
                out.println("<label>Role ID " + rid + "</label>");

                Role rf = rfl.find(Long.parseLong(rid));

                out.println("<input type=\"text\" id=\"name\" name=\"rname\" value= " + rf.getName() + ">");
                out.println("<button type=\"submit\" name=\"btn\" value=\"val\">Ok</button>");
                out.println("</form>");
            } else {
                Role uf = rfl.find(Long.parseLong(rid));
                uf.setName(rname);
                rfl.edit(uf);
                response.sendRedirect("listrole");
            }
            out.println("<a class=\"go-back\" href=\"index.html\">Home</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
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
