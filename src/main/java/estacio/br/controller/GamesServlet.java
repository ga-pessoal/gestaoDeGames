package estacio.br.controller;

import estacio.br.dao.GameDAO;
import estacio.br.dao.GeneroDAO;
import estacio.br.model.Game;
import estacio.br.model.Genero;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.List;

@MultipartConfig
public class GamesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("cadastro".equals(action)) {
            // Exibe o formulário de cadastro
            request.setAttribute("generos", getGeneros());

            request.setAttribute("pageTitle", "Cadastro de Games");
            request.getRequestDispatcher("/jsp/cadastroGame.jsp").forward(request, response);
        } else {
            // Lista games
            request.setAttribute("pageTitle", "Games");
            request.getRequestDispatcher("/jsp/games.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
        GameDAO gameDAO = new GameDAO();
        gameDAO.inserir(game);

        // Insere os dados necessários para a página
        request.setAttribute("mensagem", "Registro criado com sucesso!");
        request.setAttribute("generos", getGeneros());

        // Redireciona para a lista de games (ou outra página)
        request.getRequestDispatcher("/jsp/cadastroGame.jsp").forward(request, response);
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
