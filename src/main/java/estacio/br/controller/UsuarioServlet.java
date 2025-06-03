package estacio.br.controller;

import estacio.br.dao.UsuarioDAO;
import estacio.br.model.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UsuarioServlet extends BaseServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action != null ? action : "") {
            case "cadastro":
                exibirFormularioCadastro(request, response);
                break;
            case "editar":
                exibirFormularioEdicao(request, response);
                break;
            default:
                listarUsuarios(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action != null ? action : "") {
            case "excluir":
                excluirUsuario(request, response);
                break;
            case "editar":
                editarUsuario(request, response);
                break;
            default:
                cadastrarUsuario(request, response);
        }
    }

    // ========== Métodos privados extraídos ==========

    private void exibirFormularioCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("pageTitle", "Cadastro de usuário");
        encaminhar("/jsp/cadastroUsuario.jsp", request, response);
    }

    private void exibirFormularioEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario usuario = usuarioDAO.usuarioPorId(id);

        request.setAttribute("usuario", usuario);
        request.setAttribute("pageTitle", "Edição de usuário");
        encaminhar("/jsp/cadastroUsuario.jsp", request, response);
    }

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Usuario> usuarios = usuarioDAO.listar();
        request.setAttribute("usuarios", usuarios);
        request.setAttribute("pageTitle", "Usuários");
        encaminhar("/jsp/usuarios.jsp", request, response);
    }

    private void excluirUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            usuarioDAO.deletar(id);
        } catch (SQLException e) {
            e.printStackTrace(); // Pode ser substituído por log
        }
        listarUsuarios(request, response);
    }

    private void editarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usuario = new Usuario();
        usuario.setId(Integer.parseInt(request.getParameter("id_usuario")));
        usuario.setNome(request.getParameter("nome"));
        usuario.setEmail(request.getParameter("email"));

        try {
            usuarioDAO.editar(usuario);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        listarUsuarios(request, response);
    }

    private void cadastrarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String senha = request.getParameter("senha");
        String confirmarSenha = request.getParameter("confirmar_senha");

        if (!senha.equals(confirmarSenha)) {
            request.setAttribute("mensagem", "As senhas não são iguais.");
            encaminhar("/jsp/cadastroUsuario.jsp", request, response);
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setNome(parametro(request, "nome"));
        usuario.setEmail(parametro(request, "email"));
        usuario.setId_tipo_usuario(parametroInt(request, "id_tipo_usuario"));
        usuario.setSenha(senha);

        usuarioDAO.inserir(usuario);

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("usuarioLogado") != null) {
            // Usuário está logado, volta para a listagem
            redirecionar(request.getContextPath() + "/usuarios", response);
        } else {
            // Usuário não está logado, redireciona para login
            redirecionar(request.getContextPath() + "/login", response);
        }
    }
}
