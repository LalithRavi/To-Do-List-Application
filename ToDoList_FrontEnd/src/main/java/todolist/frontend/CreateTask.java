/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.frontend;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import todolist.business.Business;
import todolist.helper.TasksXML;

/**
 *
 * @author student
 */
@WebServlet(name = "CreateTask", urlPatterns = {"/CreateTask"})
public class CreateTask extends HttpServlet {
    
    Authenticate autho;

    public CreateTask() {
        autho = new Authenticate();
    }
    
    private final String authenticationCookieName = "createtask_token";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private Map.Entry<String, String> isAuthenticated(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = "";
        
        System.out.println("TOKEN IS" + token);
        try {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                if (cookie.getName().equals(authenticationCookieName)) {
                    token = cookie.getValue();
                }
            }
        } catch (Exception e) {

        }
        if (!token.isEmpty()) {
           try {
                if (this.autho.verify(token).getKey()) {
                    Map.Entry entry= new  AbstractMap.SimpleEntry<String, String>
                                 (token,this.autho.verify(token).getValue());
                return entry;

                } else {
                    Map.Entry entry= new  AbstractMap.SimpleEntry<String, String>("","");
                    return entry;
                }
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        Map.Entry entry= new  AbstractMap.SimpleEntry<String, String>("","");
        return entry;

    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("inside create task servlet");
        String username = request.getSession().getAttribute("username").toString();
            System.out.println("username: " + username);
        String password = request.getParameter("password");
        boolean isAuthenticated = Business.isAuthenticated(username, password);
        
        String name = request.getParameter("nameField");
        String description = request.getParameter("descriptionField");
        String status = request.getParameter("statusField");
        String duedate = request.getParameter("dateField");
        String category = request.getParameter("categoryField");
        
        boolean nullCheck = (name != null && description != null && status != null && duedate != null && category != null);
        boolean emptyCheck = (!"".equals(name) && !"".equals(description) && !"".equals(status) && !"".equals(duedate) && !"".equals(category));
        
        if (nullCheck && emptyCheck) {
            String token = isAuthenticated(request).getKey();
            String uname = isAuthenticated(request).getValue();
            System.out.println("Token: " + token);
            token = autho.createJWT("Register", username, 100000);
            
            Cookie newCookie = new Cookie(authenticationCookieName, token);
            response.addCookie(newCookie);
            String taskinfo = "taskName=" + name + "&description=" + description + "&status=" + status + "&duedate=" + duedate + "&category=" + category + "&username=" + username;
            retreiveCreateTaskFromBackend(taskinfo, token);
            
            TasksXML result = retreiveTasksFromBackend(username, token);
            request.setAttribute("taskResults", result);
            request.setAttribute("username", username);
            request.getRequestDispatcher("UserHome.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Cannot create Task");
            request.setAttribute("username", username);
            request.getRequestDispatcher("UserHome.jsp").forward(request, response);   
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

    private void retreiveCreateTaskFromBackend(String query, String token) {
        try {
            Business.getServicesCreateTask(query, token);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private TasksXML retreiveTasksFromBackend(String query, String token) {
        try {
            TasksXML output = Business.getServicesViewTasks(query, token);
            return (output);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            return (null);
        }
    }

}
