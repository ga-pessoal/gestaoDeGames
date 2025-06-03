package estacio.br.controller;

import estacio.br.dao.GameDAO;
import estacio.br.model.Game;
import estacio.br.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class HomeServlet extends BaseServlet {
    private GameDAO gameDAO;

    @Override
    public void init() {
        gameDAO = new GameDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        listarNotas(request, response);

    }

    private void listarNotas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recupera o usuário logado da sessão
        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        final int tipo_usuario = usuario.getId_tipo_usuario();

        // Monta a lista de notas
        List<Game> lista;

        if(tipo_usuario == 1) {
            lista = gameDAO.listarGamesComMediaNotas();
        }else{
            lista = gameDAO.listarGamesComNotas(usuario.getId());
        }

        request.setAttribute("games", lista);
        request.setAttribute("pageTitle", "Página Inicial");
        encaminhar("/jsp/home.jsp", request, response);
    }
}
