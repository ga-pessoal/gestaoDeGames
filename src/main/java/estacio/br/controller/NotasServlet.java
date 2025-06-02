package estacio.br.controller;

import estacio.br.dao.GameDAO;
import estacio.br.dao.NotaDAO;
import estacio.br.model.Game;
import estacio.br.model.Nota;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class NotasServlet extends BaseServlet {

    private GameDAO gameDAO;
    private NotaDAO notaDAO;

    @Override
    public void init() {
        gameDAO = new GameDAO();
        notaDAO = new NotaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        switch (parametro(request, "action") != null ? parametro(request, "action") : "") {
            case "cadastro":
                exibirFormularioCadastro(request, response);
                break;
            default:
                listarNotas(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        cadastrarNota(request, response);
    }

    private void exibirFormularioCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Exibe o formulário de cadastro
        request.setAttribute("pageTitle", "Cadastro de Nota");
        request.setAttribute("games", getGames());
        encaminhar("/jsp/cadastroNota.jsp", request, response);
    }

    private void listarNotas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lista Notas
        request.setAttribute("pageTitle", "Notas");
        encaminhar("/jsp/home.jsp", request, response);
    }

    private void cadastrarNota(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Monta o objeto com os valores da nota
        Nota notaObj = new Nota();
        notaObj.setNota(parametroInt(request, "nota"));
        notaObj.setIdGame(parametroInt(request, "id_game"));
        notaObj.setIdUsuario(parametroInt(request, "id_usuario"));

        // Insere os dados na base de dados
        notaDAO.inserir(notaObj);

        // Redireciona para a página de cadastro novamente
        listarNotas(request, response);
    }

    public List<Game> getGames() {
        gameDAO = new GameDAO();
        List<Game> games = gameDAO.listar();

        return games;
    }
}
