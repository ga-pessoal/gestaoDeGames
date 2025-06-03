package estacio.br.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class LogoutServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); // Evita criar nova sessão se não existir

        if (session != null) {
            session.invalidate(); // Finaliza a sessão
        }

        redirecionar(request.getContextPath() + "/login", response); // Redireciona para o login
    }
}