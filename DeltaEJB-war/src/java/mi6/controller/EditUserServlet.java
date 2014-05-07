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
import mi6.ejbs.UserFacadeLocal;
import mi6.entity.User;

/**
 *
 * @author AgtLucas
 */
@WebServlet(name = "EditUserServlet", urlPatterns = {"/edituser"})
public class EditUserServlet extends HttpServlet {

    @EJB
    UserFacadeLocal ufl;
    
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
        
        String uid = request.getParameter("uid");
        String uname = request.getParameter("uname");
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditUserServlet</title>");
            out.println("<link rel=\"stylesheet\" href=\"css/style.css\"/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"main\">");
            
            if (uname == null) {
                out.println("<form method=\"POST\">");
                out.println("<label>User ID " + uid + "</label>");

                User uf = ufl.find(Long.parseLong(uid));

                out.println("<input type=\"text\" id=\"name\" name=\"uname\" value= " + uf.getName() + ">");
                out.println("<button type=\"submit\" name=\"btn\" value=\"val\">Enviar</button>");
                out.println("</form>");
                out.println("<a class=\"go-back\" href=\"index.html\">Home</a>");
            } else {
                User uf = ufl.find(Long.parseLong(uid));
                uf.setName(uname);
                ufl.edit(uf);
                response.sendRedirect("listuser");
            }
            
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
