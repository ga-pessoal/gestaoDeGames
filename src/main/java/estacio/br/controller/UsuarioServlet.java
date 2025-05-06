package estacio.br.controller;

import estacio.br.model.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class UsuarioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> lista = dao.listar();

        request.setAttribute("usuarios", lista);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/usuarios.jsp");
        dispatcher.forward(request, response);
    }
}