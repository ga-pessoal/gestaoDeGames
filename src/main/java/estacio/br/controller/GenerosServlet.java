package estacio.br.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GenerosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("cadastro".equals(action)) {
            // Exibe o formulário de cadastro
            request.setAttribute("pageTitle", "Cadastro de Gênero");
            request.getRequestDispatcher("/jsp/cadastroGenero.jsp").forward(request, response);
        } else {
            // Lista Generos
            request.setAttribute("pageTitle", "Generos");
            request.getRequestDispatcher("/jsp/generos.jsp").forward(request, response);
        }
    }
}
