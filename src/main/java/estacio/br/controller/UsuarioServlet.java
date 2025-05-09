package estacio.br.controller;

import estacio.br.dao.UsuarioDAO;
import estacio.br.model.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class UsuarioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("cadastro".equals(action)) {
            // Exibe o formulário de cadastro
            request.getRequestDispatcher("/jsp/cadastroUsuario.jsp").forward(request, response);
        } else {
            // Lista usuários
            UsuarioDAO dao = new UsuarioDAO();
            List<Usuario> lista = dao.listar();
            request.setAttribute("usuarios", lista);
            request.getRequestDispatcher("/jsp/usuarios.jsp").forward(request, response);
        }
    }
}