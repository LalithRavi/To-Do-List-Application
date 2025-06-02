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
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {
    
    Authenticate autho;

    public Register() {
        autho = new Authenticate();
    }
    
    private final String authenticationCookieName = "register_token";

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
            System.out.println(e);
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        int role = Integer.parseInt(request.getParameter("role"));
        boolean isAuthenticated = Business.isAuthenticated(username, password);
        
        if ((username != null && password != null) && (!"".equals(username) && !"".equals(password))) {
            if (email.matches("^(.+)@(\\S+)$")) {
                if (password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
                    String token = isAuthenticated(request).getKey();
                    String uname = isAuthenticated(request).getValue();
                    System.out.println("Token: " + token);
                    request.getSession().setAttribute("username", username);
                    token = autho.createJWT("Register", username, 100000);
                    if (isAuthenticated && (!(token.isEmpty()))) {
                        Cookie newCookie = new Cookie(authenticationCookieName, token);
                        response.addCookie(newCookie);
                        String info = "username=" + username + "&password=" + password + "&email=" + email + "&role=" + role;
                        
                        switch (role) {
                            case 1:
                                retreiveServicesFromBackend(info, token);
                                request.getRequestDispatcher("AdminHome.jsp").forward(request, response);
                                break;
                            case 2:
                                TasksXML tasks = retrieveTasksFromBackend(username, token);
                                request.setAttribute("taskResults", tasks);
                                retreiveServicesFromBackend(info, token);
                                request.getRequestDispatcher("UserHome.jsp").forward(request, response);
                                break;
                            default:
                                request.setAttribute("errorMessage", "Cannot create user.");
                                request.getRequestDispatcher("Register.jsp").forward(request, response);
                                break;
                        }
                    }
                } else {
                    request.setAttribute("errorMessage", "Password doesn't match criteria");
                    request.getRequestDispatcher("Register.jsp").forward(request, response); 
                }                   
            } else {
                request.setAttribute("errorMessage", "Email doesn't match criteria");
                request.getRequestDispatcher("Register.jsp").forward(request, response);             
            }
            
        } else {
            request.setAttribute("errorMessage", "Make sure to enter all fields");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
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

    private void retreiveServicesFromBackend(String query, String token) {
        try {
            Business.getServicesRegisterUser(query, token);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private TasksXML retrieveTasksFromBackend(String query, String token) {
        try {
            TasksXML output = Business.getServicesViewTasks(query, token);
            return (output);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            return (null);
        }
    }
    
}
