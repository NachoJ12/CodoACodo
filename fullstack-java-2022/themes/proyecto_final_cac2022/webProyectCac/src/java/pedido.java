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

/**
 *
 * @author Nacho
 */
@WebServlet(name = "pedido", urlPatterns = {"/pedido"})
public class pedido extends HttpServlet {

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
             bdd.cargarPedidoSQL("INSERT INTO pedidos VALUES (default, '" + 
                    request.getParameter("inputFirstName") + "', '" +
                    request.getParameter("inputLastName") + "', '" + 
                    request.getParameter("inputUsername") + "', '" +
                    request.getParameter("inputEmail") + "', '" + 
                    request.getParameter("inputAddress") + "', '" +
                    request.getParameter("selectLocalityId") + "', '" +
                    request.getParameter("selectProvinceId") + "', '" + 
                    request.getParameter("inputZipCode") + "', '" +
                    request.getParameter("inputPaymentMethod") + "', '" +
                    request.getParameter("inputCardName" ) + "', '" +
                    request.getParameter("inputCardNumber") + "', '" +
                    request.getParameter("inputCardExpiration") + "', '" +
                    request.getParameter("inputCardCvv") + "')");
             
             //bdd.cargarPedidoSQL("INSERT INTO pedidos VALUES (default, '"+  request.getParameter("inputFirstName") + "', 'Justel', 'nachoj','nacho.justel12@gmail.com', 'Calle falsa 123', 2,1, '1636', 1, 'Nacho Justel', 1111222233334444, '08/23', 123)");
            
     
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
                                <body class="d-flex flex-column align-items-center bg-light">
                                    <a
                                        href="/webProyectCac/index.html"
                                        class="align-self-start previous btn btn-secondary mt-3 mx-3"
                                        role="button"
                                    >
                                        Volver a home
                                    </a>
                                    <div class="py-5 text-center">
                                      <img
                                        class="d-block mx-auto mb-4"
                                        src="./images/logo.jpg"
                                        alt=""
                                        width="72"
                                        height="72"
                                      />
                                      <h2>Formulario enviado con éxito</h2>
                                      <p class="lead">Ante cualquier inconveniente consulte con el administrador</p>
                                    </div>
                                    <div>
                                        <a href="/webProyectCac/consultaPedidos" class="btn btn-primary btn-lg" role="button">Consultar pedidos</a> 
                                    </div>
                                   <footer class="my-5 pt-5 text-muted text-center text-small">
                                    <p class="mb-1">&copy; 2022-2023 Codo a Codo</p>
                                    </footer>
                                </body>
                            </html>
                        """);
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
