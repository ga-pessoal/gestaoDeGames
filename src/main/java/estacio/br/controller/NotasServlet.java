package estacio.br.controller;

import estacio.br.dao.GameDAO;
import estacio.br.dao.NotaDAO;
import estacio.br.model.Game;
import estacio.br.model.Nota;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class NotasServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("cadastro".equals(action)) {
            // Exibe o formulário de cadastro
            request.setAttribute("pageTitle", "Cadastro de Nota");
            request.setAttribute("games", getGames());
            request.getRequestDispatcher("/jsp/cadastroNota.jsp").forward(request, response);
        } else {
            // Lista Notas
            request.setAttribute("pageTitle", "Notas");
            request.getRequestDispatcher("/jsp/notas.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Pega os dados dos inputs
        int nota = Integer.parseInt(request.getParameter("nota"));
        int id_game = Integer.parseInt(request.getParameter("id_game"));
        int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));

        // Monta o objeto com os valores da nota
        Nota notaObj = new Nota();
        notaObj.setNota(nota);
        notaObj.setIdGame(id_game);
        notaObj.setIdUsuario(id_usuario);

        // Insere os dados na base de dados
        NotaDAO notaDAO = new NotaDAO();
        notaDAO.inserir(notaObj);

        // Insere os dados necessários para a página
        request.setAttribute("mensagem", "Registro criado com sucesso!");
        request.setAttribute("games", getGames());

        // Redireciona para a página de cadastro novamente
        request.getRequestDispatcher("/jsp/cadastroNota.jsp").forward(request, response);
    }

    public List<Game> getGames() {
        GameDAO gameDAO = new GameDAO();
        List<Game> games = gameDAO.listar();

        return games;
    }
}
