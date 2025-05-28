package estacio.br.controller;

import estacio.br.dao.UsuarioDAO;
import estacio.br.model.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
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
        } else if("editar".equals(action)){
            int id_usuario = Integer.parseInt(request.getParameter("id"));

            UsuarioDAO dao = new UsuarioDAO();
            Usuario usuario = dao.usuarioPorId(id_usuario);

            request.setAttribute("usuario", usuario);
            request.setAttribute("pageTitle", "Edição de usuário");
            request.getRequestDispatcher("/jsp/cadastroUsuario.jsp").forward(request, response);
        }
        else {
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
        String action = request.getParameter("action");

        if("excluir".equals(action)){
            int id = Integer.parseInt(request.getParameter("id"));
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            try{
                usuarioDAO.deletar(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            List<Usuario> lista = usuarioDAO.listar();
            request.setAttribute("usuarios", lista);
            request.setAttribute("pageTitle", "Usuários");
            request.getRequestDispatcher("/jsp/usuarios.jsp").forward(request, response);
        }else if("editar".equals(action)){
            int id = Integer.parseInt(request.getParameter("id_usuario"));
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");

            Usuario usuarioObj = new Usuario();
            usuarioObj.setId(id);
            usuarioObj.setNome(nome);
            usuarioObj.setEmail(email);

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            try{
                usuarioDAO.editar(usuarioObj);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            List<Usuario> lista = usuarioDAO.listar();
            request.setAttribute("usuarios", lista);
            request.setAttribute("pageTitle", "Usuários");
            request.getRequestDispatcher("/jsp/usuarios.jsp").forward(request, response);
        }else{
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
}