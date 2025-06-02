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
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.NewCookie;
import todolist.business.Business;
import todolist.helper.TasksXML;
import todolist.persistence.ValidateCredentials;

/**
 *
 * @author student
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {
    
    Authenticate autho;

    public Login() {
        autho = new Authenticate();
    }
    
    private final String authenticationCookieName = "login_token";

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
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean isAuthenticated = Business.isAuthenticated(username, password);
        TasksXML result;   
        
        
        
        if ((username != null && password != null) && (!"".equals(username) && !"".equals(password))) {
            String token = isAuthenticated(request).getKey();
            String uname = isAuthenticated(request).getValue();
            System.out.println("Token: " + token);
            request.setAttribute("username", username);
            token = autho.createJWT("Login", username, 100000);
            
            int validation = ValidateCredentials.validateLogin(username, password);
            System.out.println("Validate: " + validation);
            
            switch(validation) {
                case 1:
                    if (isAuthenticated) {
                        if (!token.isEmpty()) {

                            Cookie newCookie = new Cookie(authenticationCookieName, token);
                            response.addCookie(newCookie);

                            request.setAttribute("username", username);
                            RequestDispatcher requestDispatcher = request.getRequestDispatcher("AdminHome.jsp");

                            requestDispatcher.forward(request, response);
                        } else {
                            System.out.println("Token is empty");
                            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.jsp");
                            requestDispatcher.forward(request, response);
                        }
                    } else {
                        System.out.println("Not authenticated");
                    }
                    break;
                case 2:
                    if (isAuthenticated) {
                        if (!token.isEmpty()) {

                            Cookie newCookie = new Cookie(authenticationCookieName, token);
                            response.addCookie(newCookie);

                            request.setAttribute("username", uname);
                            result = retreiveServicesFromBackend(username, token);

                            if (!(result.getTasks().isEmpty())) {
                                request.setAttribute("taskResults", result);
                                request.setAttribute("username", username);
                                request.getSession().setAttribute("username", username);
                                RequestDispatcher requestDispatcher = request.getRequestDispatcher("UserHome.jsp");
                                requestDispatcher.forward(request, response);
                            } else {
                                request.getRequestDispatcher("Login.jsp").forward(request, response);
                            }
                        } else {
                            System.out.println("Token is empty");
                            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.jsp");
                            requestDispatcher.forward(request, response);
                        }
                    } else {
                        System.out.println("Not authenticated");
                    }
                    break;
                default:
                    request.setAttribute("errorMessage", "Incorrect username and/or password.");
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                    break;
            }
        } else {
            request.setAttribute("errorMessage", "Enter both username and pasword");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
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
    
    private TasksXML retreiveServicesFromBackend(String query, String token) {
        try {
            TasksXML output = Business.getServicesViewTasks(query, token);
            return (output);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            return (null);
        }
    }

}
