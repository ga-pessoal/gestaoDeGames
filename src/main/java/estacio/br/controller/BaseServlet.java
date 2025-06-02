package estacio.br.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public abstract class BaseServlet extends HttpServlet {

    protected void encaminhar(String caminho, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(caminho);
        dispatcher.forward(request, response);
    }

    protected void redirecionar(String url, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(url);
    }

    protected String parametro(HttpServletRequest request, String nome) {
        String valor = request.getParameter(nome);
        return valor != null ? valor.trim() : "";
    }

    protected int parametroInt(HttpServletRequest request, String nome) {
        try {
            return Integer.parseInt(parametro(request, nome));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    protected void setMensagem(HttpServletRequest request, String mensagem) {
        request.setAttribute("mensagem", mensagem);
    }
}