/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
@WebServlet(name="consultaPedidos", urlPatterns = {"/consultaPedidos"})
public class consultaPedidos extends HttpServlet {

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
            
            
            Persistencia bdd = new Persistencia();
            ResultSet rs = bdd.consultaSQL("""
                                           SELECT PE.idpedido, CONCAT(PE.nombre, ' ', PE.apellido) nombre_y_apellido, PE.usuario, PE.email, PE.lugar_entrega, PE.codigo_postal, PE.tarjeta_titular, PE.tarjeta_numero, PE.tarjeta_vencimiento, LOC.nombre_localidad, PROV.nombre AS nombre_provincia, MP.tipo_de_metodo 
                                           FROM pedidos PE 
                                           INNER JOIN provincias PROV ON PROV.idprovincia = PE.provincia_id 
                                           INNER JOIN localidades LOC ON LOC.idlocalidad = PE.localidad_id 
                                           INNER JOIN metodos_de_pago MP ON MP.idmetodo_de_pago = PE.forma_de_pago
                                           ORDER BY PE.idpedido""");
            
            out.println("""
                            <!DOCTYPE html>
                            <html lang="en">
                              <head>
                                <meta charset="UTF-8" />
                                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                                <meta name="viewport" content="width=device-width, initial-scale=1.0" />
                                <title>Consulta de pedidos</title>
                                <!-- CSS only -->
                                <link
                                  href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
                                  rel="stylesheet"
                                  integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
                                  crossorigin="anonymous"
                                />
                              </head>
                              <body class="container-fluid bg-light">
                                <a
                                  href="/webProyectCac/index.html"
                                  class="float-left previous btn btn-secondary mx-auto mt-3"
                                  role="button"
                                >
                                  Volver a home
                                </a>
                                <div class="pb-5 pt-2 text-center">
                                  <img
                                    class="d-block mx-auto mb-4"
                                    src="./images/logo.jpg"
                                    alt=""
                                    width="72"
                                    height="72"
                                  />
                                  <h2>Consulta de pedidos</h2>
                                  <p class="lead">La fecha de entrega será coordinada telefónicamente.</p>
                                </div>
                                <div class="container">
                                      <div id="div_pedido_table">
                                        <h1>Pedidos</h1>
                                        <div class="row col-md-12 table-responsive">
                                          <table id="pedidoTable" class="table table-striped">
                                            <thead>
                                              <tr>
                                                <th>Id</th>
                                                <th>Nombre y apellido</th>
                                                <th>Usuario</th>
                                                <th>Email</th>
                                                <th>Lugar de entrega</th>
                                                <th>Cógido postal</th>
                                                <th>Tarjeta titular</th>
                                                <th>Provincia</th>
                                                <th>Localidad</th>
                                                <th>Metodo de pago</th>
                                              </tr>
                                            </thead>
                                            <tbody id="pedidoTableBody">""");
                        while(rs.next()){
                            out.println(""" 
                                         <tr>
                                         """);  
                            out.println("<th scope='row'>" + rs.getString("idpedido") + "</th>"); 
                            out.println("<td>" + rs.getString("nombre_y_apellido") + "</td>"); 
                            out.println("<td>" + rs.getString("usuario") + "</td>"); 
                            out.println("<td>" + rs.getString("email") + "</td>"); 
                            out.println("<td>" + rs.getString("lugar_entrega") + "</td>"); 
                            out.println("<td>" + rs.getString("codigo_postal") + "</td>"); 
                            out.println("<td>" + rs.getString("tarjeta_titular") + "</td>"); 
                            out.println("<td>" + rs.getString("nombre_provincia") + "</td>"); 
                            out.println("<td>" + rs.getString("nombre_localidad") + "</td>"); 
                            out.println("<td>" + rs.getString("tipo_de_metodo") + "</td>"); 
           
  
                            out.println("""
                                        </tr>
                                        """);
                         }
                        
                        out.println("""
                                            </tbody>
                                          </table>
                                        </div>
                                      </div>                  
                        """);
  
            
             out.println("""
                                </div>
                                <footer class="my-5 pt-5 text-muted text-center text-small">
                                   <p class="mb-1">&copy; 2022-2023 Codo a Codo</p>
                                 </footer>
                            </body>
                        </html>
                            """);
                
            
            
        } catch (SQLException ex) {
            Logger.getLogger(consultaPedidos.class.getName()).log(Level.SEVERE, null, ex);
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
