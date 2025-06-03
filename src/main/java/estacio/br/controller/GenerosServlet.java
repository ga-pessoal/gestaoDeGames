package estacio.br.controller;

import estacio.br.dao.GeneroDAO;
import estacio.br.model.Genero;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GenerosServlet extends BaseServlet {

    private GeneroDAO generoDAO;

    @Override
    public void init() {
        generoDAO = new GeneroDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (parametro(request, "action") != null ? parametro(request, "action") : "") {
            case "cadastro":
                exibirFormularioCadastro(request, response);
                break;
            case "editar":
                exibirFormularioEdicao(request, response);
                break;
            default:
                listarGeneros(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (parametro(request, "action") != null ? parametro(request, "action") : "") {
            case "excluir":
                excluirGenero(request, response);
                break;
            case "editar":
                editarGenero(request, response);
                break;
            default:
                cadastrarGenero(request, response);
        }
    }

    private void exibirFormularioCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Exibe o formulário de cadastro
        request.setAttribute("pageTitle", "Cadastro de Gênero");
        encaminhar("/jsp/cadastroGenero.jsp", request, response);
    }

    private void exibirFormularioEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Genero genero = generoDAO.generoPorId(parametroInt(request, "id"));

        request.setAttribute("genero", genero);
        request.setAttribute("pageTitle", "Edição de gênero");
        encaminhar("/jsp/cadastroGenero.jsp", request, response);
    }

    private void listarGeneros(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Genero> generos = generoDAO.listar();
        request.setAttribute("generos", generos);
        request.setAttribute("pageTitle", "Gêneros");
        encaminhar("/jsp/generos.jsp", request, response);
    }

    private void excluirGenero(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = parametroInt(request, "id");

        try {
            generoDAO.deletar(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        listarGeneros(request, response);
    }

    private void editarGenero(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Genero generoObj = new Genero();
        generoObj.setId(parametroInt(request, "id_genero"));
        generoObj.setTitulo(parametro(request, "titulo"));

        try {
            generoDAO.editar(generoObj);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        listarGeneros(request, response);
    }

    private void cadastrarGenero(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Montar o objeto com os valores das variáveis acima
        Genero generoObj = new Genero();
        generoObj.setTitulo(parametro(request, "titulo"));

        // Inserir os dados na base de dados DAO
        generoDAO.inserir(generoObj);

        // Listar
        listarGeneros(request, response);
    }
}
