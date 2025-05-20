package estacio.br.controller;

import estacio.br.dao.GameDAO;
import estacio.br.model.Game;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final int tipo_usuario = 1;
        List<Game> lista;

        GameDAO dao = new GameDAO();
        if(tipo_usuario == 1) {
            lista = dao.listarGamesComMediaNotas();
        }else{
            lista = dao.listarGamesComNotas();
        }

        request.setAttribute("games", lista);
        request.setAttribute("pageTitle", "Página Inicial");
        request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
    }
}
