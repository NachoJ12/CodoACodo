/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            /*out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet login</title>");            
            out.println("</head>");
            out.println("<body>");*/
            Persistencia bdd = new Persistencia();
            //ResultSet rs = bdd.consultaSQL("select * from usuarios");
            ResultSet rs = bdd.consultaSQL("SELECT * FROM usuarios WHERE email = '" + request.getParameter("inputEmail") + "' AND password = '" + request.getParameter("inputPassword") + "'");
            
                while(rs.next()){
                    
                    //out.println(rs.getString("clave"));
                    
                    out.println("""
                                <!DOCTYPE html>
                                <html lang="en">
                                  <head>
                                    <meta charset="utf-8" />
                                    <meta
                                      name="viewport"
                                      content="width=device-width, initial-scale=1, shrink-to-fit=no"
                                    />
                                    <meta name="description" content="" />
                                    <meta
                                      name="author"
                                      content="Mark Otto, Jacob Thornton, and Bootstrap contributors"
                                    />
                                    <meta name="generator" content="Jekyll v4.1.1" />
                                    <title>Login de Usuario</title>
                                
                                    <!-- CSS only -->
                                    <link
                                      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
                                      rel="stylesheet"
                                      integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
                                      crossorigin="anonymous"
                                    />
                                 </head>
                                <body class="text-center bg-light">
                                    <div class="py-5 text-center">
                                      <img
                                        class="d-block mx-auto mb-4"
                                        src="./images/logo.jpg"
                                        alt=""
                                        width="72"
                                        height="72"
                                      />
                                      <h2>Datos del usuario</h2>
                                      <p class="lead">Ante cualquier inconveniente consulte con el administrador</p>
                                    </div>
                                
                                
                                    <div class="d-flex flex-column align-items-center justify-content-center gap-3">
                                        <div class="col-sm-6" style="background-color:#607d8b; padding:10px; border-radius:3px">
                                            <div class="mb-3">
                                                <label for="email" class="fw-bold mb-2 fs-5 float-start">Email</label>
                                         
                                                <input
                                                  readonly
                                                  disabled
                                                  type="text"
                                                  class="form-control"
                                                  id="email"
                                                  placeholder='""");
                    out.println(rs.getString("email")+"'");
                    out.println("""         
                                                />     
                                            </div>
                                
                                <div class="row">
                                            <div class="col mb-3">
                                                <label for="nombre" class="fw-bold mb-2 fs-5 float-start">Nombre</label>
                                                                         
                                                <input
                                                  readonly
                                                  disabled
                                                  type="text"
                                                  class="form-control"
                                                  id="nombre"
                                                  placeholder='""");
                     out.println(rs.getString("nombre")+"'");
                     out.println("""         
                                                />     
                                            </div>""");
                                 
                    out.println("""   
                                            <div class="col mb-3">
                                                <label for="apellido" class="fw-bold mb-2 fs-5 float-start">Apellido</label>
                                                                         
                                                <input
                                                  readonly
                                                  disabled
                                                  type="text"
                                                  class="form-control"
                                                  id="apellido"
                                                  placeholder='""");
                     out.println(rs.getString("apellido")+"'");
                     out.println("""         
                                                />     
                                            </div>
                                 
                                        </div>
                                    </div>
                                <div>
                                     <a href="/webProyectCac/pedido.html" class="btn btn-primary btn-lg" role="button">Realizar pedido</a> 
                                </div>
                                 
                                 
                                """);
                     
                    out.println("""
                                    </body>
                                  </html>
                                  """);
                  
                }
                 /* El first nos indica la primera busqueda que se devuelva de la base de datos. 
                    El ResultSet first() se usa para mover el cursor a la primera fila en el objeto ResultSet. Devuelve verdadero si el cursor apuntó a la primera fila en el ResultSet y devuelve falso si el objeto ResultSet no contiene ninguna fila.*/
                if(rs.first() == false){
                    out.println("""
                                <!DOCTYPE html>
                                                                <html lang="en">
                                                                  <head>
                                                                    <meta charset="utf-8" />
                                                                    <meta
                                                                      name="viewport"
                                                                      content="width=device-width, initial-scale=1, shrink-to-fit=no"
                                                                    />
                                                                    <meta name="description" content="" />
                                                                    <meta
                                                                      name="author"
                                                                      content="Mark Otto, Jacob Thornton, and Bootstrap contributors"
                                                                    />
                                                                    <meta name="generator" content="Jekyll v4.1.1" />
                                                                    <title>Login de Usuario</title>
                                                                
                                                                    <!-- CSS only -->
                                                                    <link
                                                                      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
                                                                      rel="stylesheet"
                                                                      integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
                                                                      crossorigin="anonymous"
                                                                    />
                                                                 </head>
                                                                <body class="text-center bg-light">
                                                                    <div class="py-5 text-center">
                                                                      <img
                                                                        class="d-block mx-auto mb-4"
                                                                        src="./images/logo.jpg"
                                                                        alt=""
                                                                        width="72"
                                                                        height="72"
                                                                      />
                                                                      <h2>No hay usuarios que coincidan con la búsqueda</h2>
                                                                      <p class="lead">Ante cualquier inconveniente consulte con el administrador</p>
                                                                      <p>El email ingresado es: """);
                                                                        out.println(request.getParameter("inputEmail"));
                                                                        out.println("""
                                                                      </p> 
                                                                    </div>
                                                                    <div>
                                                                        <a href="/webProyectCac/login.html" class="btn btn-primary btn-lg" role="button">Volver a login</a>
                                                                    </div> 
                                                                        """);
                                                                      
                                                                                  
                    
                    
                    //out.println ("No hay usuarios que coincidan con la búsqueda");
                    
                    //out.println("<h1>Servlet login at " + request.getContextPath() + "</h1>");
                    
            }
    
           /* out.println("</body>");
            out.println("</html>");*/
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
