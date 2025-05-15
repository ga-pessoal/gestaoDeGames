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
            request.setAttribute("pageTitle", "Cadastro de usuário");
            request.getRequestDispatcher("/jsp/cadastroUsuario.jsp").forward(request, response);
        } else {
            // Lista usuários
            UsuarioDAO dao = new UsuarioDAO();
            List<Usuario> lista = dao.listar();
            request.setAttribute("usuarios", lista);
            request.setAttribute("pageTitle", "Usuários");
            request.getRequestDispatcher("/jsp/usuarios.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Pega os dados de cadastroUsuario.jsp
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String confirmar_senha = request.getParameter("confirmar_senha");

        // Confirmar se as senhas são iguais, s/n inserir os dados para "mensagem" e redirecionar para a pg
        if(!senha.equals(confirmar_senha)){
            request.setAttribute("mensagem", "As senhas não são iguais.");
            request.getRequestDispatcher("/jsp/cadastroUsuario.jsp").forward(request, response);

            return;
        }

        // Montar o objeto com os valores das variáveis acima
        Usuario usuarioObj = new Usuario();
        usuarioObj.setNome(nome);
        usuarioObj.setEmail(email);
        usuarioObj.setSenha(senha);

        // Inserir os dados na base de dados DAO
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.inserir(usuarioObj);

        // Redireciona para a página de login
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);

    }
}