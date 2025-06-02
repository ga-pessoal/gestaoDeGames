package estacio.br.controller;

import estacio.br.dao.GameDAO;
import estacio.br.dao.GeneroDAO;
import estacio.br.model.Game;
import estacio.br.model.Genero;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

@MultipartConfig
public class GamesServlet extends BaseServlet {

    private GameDAO gameDAO;

    @Override
    public void init() {
        gameDAO = new GameDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch (parametro(request, "action") != null ? parametro(request, "action") : "") {
            case "cadastro":
                exibirFormularioCadastro(request, response);
                break;
            case "editar":
                exibirFormularioEdicao(request, response);
                break;
            default:
                listarGames(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (parametro(request, "action") != null ? parametro(request, "action") : "") {
            case "excluir":
                excluirGame(request, response);
                break;
            case "editar":
                editarGame(request, response);
                break;
            default:
                cadastrarGame(request, response);
        }
    }

    private void exibirFormularioCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("generos", getGeneros());
        request.setAttribute("pageTitle", "Cadastro de Games");
        encaminhar("/jsp/cadastroGame.jsp", request, response);
    }

    private void exibirFormularioEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Game game = gameDAO.gamePorId(parametroInt(request,"id"));

        request.setAttribute("generos", getGeneros());
        request.setAttribute("game", game);
        request.setAttribute("pageTitle", "Edição de Game");
        encaminhar("/jsp/cadastroGame.jsp", request, response);
    }

    private void listarGames(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Game> games = gameDAO.listar();
        request.setAttribute("games", games);
        request.setAttribute("pageTitle", "Games");
        encaminhar("/jsp/games.jsp", request, response);
    }

    private void excluirGame(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            gameDAO.deletar(id);
        } catch (SQLException e) {
            e.printStackTrace(); // Pode ser substituído por log
        }
        listarGames(request, response);
    }

    private void editarGame(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Game gameObj = new Game();
        gameObj.setId(parametroInt(request, "id_game"));
        gameObj.setTitulo(parametro(request, "titulo"));
        gameObj.setIdGenero(parametroInt(request, "id_genero"));

        try{
            gameDAO.editar(gameObj);
        }catch (SQLException e){
            e.printStackTrace();
        }

        listarGames(request, response);
    }

    private void cadastrarGame(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Part tituloPart = request.getPart("titulo");
        String titulo = getFormField(tituloPart);

        Part generoPart = request.getPart("id_genero");
        int idGenero = Integer.parseInt(getFormField(generoPart));

        // Diretório para salvar a imagem
        String uploadPath = "C:/GamesRate/uploads";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        Part imagemPart = request.getPart("imagem");
        String fileName = Paths.get(imagemPart.getSubmittedFileName()).getFileName().toString();
        String filePath = uploadPath + File.separator + fileName;

        if (!fileName.isEmpty()) {
            imagemPart.write(filePath);
        }

        // Cria o objeto Game
        Game game = new Game();
        game.setTitulo(titulo);
        game.setIdGenero(idGenero);
        game.setNomeImagem(fileName); // apenas o nome, não o caminho completo

        // Salva no banco de dados
        gameDAO.inserir(game);

        listarGames(request, response);
    }

    public List<Genero> getGeneros() {
        GeneroDAO generoDAO = new GeneroDAO();
        List<Genero> generos = generoDAO.listar();

        return generos;
    }

    private String getFormField(Part part) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"))) {
            StringBuilder value = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                value.append(line);
            }
            return value.toString();
        }
    }
}
