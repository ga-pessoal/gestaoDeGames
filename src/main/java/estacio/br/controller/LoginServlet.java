package estacio.br.controller;

import estacio.br.dao.UsuarioDAO;
import estacio.br.model.Usuario;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginServlet extends BaseServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("pageTitle", "Login");
        encaminhar("/jsp/login.jsp", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.autenticar(parametro(request, "email"), parametro(request, "senha"));

        if (usuario != null) {
            // Login bem-sucedido
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", usuario);
            redirecionar( request.getContextPath() + "/home", response);
        } else {
            // Erro de login
            request.setAttribute("mensagem", "E-mail ou senha inválidos.");
            request.setAttribute("pageTitle", "Login");
            encaminhar("/jsp/login.jsp", request, response);
        }
    }
}
