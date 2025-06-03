package estacio.br.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String uri = request.getRequestURI();
        String query = request.getQueryString();
        String context = request.getContextPath();
        String metodo = request.getMethod();
        String action = request.getParameter("action");

        // URLs públicas (login, cadastro, arquivos estáticos)
        boolean recursoPublico = uri.equals(context + "/login") ||                          // Servlet de login
                uri.equals(context + "/jsp/login.jsp") ||
                (uri.equals(context + "/usuarios") && (
                        (query != null && query.contains("action=cadastro")) ||  // GET cadastro
                                ("POST".equalsIgnoreCase(metodo) && (action == null || "cadastro".equals(action)))
                )) ||
                uri.contains("/jsp/cadastroUsuario.jsp") ||
                uri.endsWith(".css") ||
                uri.endsWith(".js") ||
                uri.endsWith(".png") ||
                uri.endsWith(".jpg") ||
                uri.endsWith(".jpeg") ||
                uri.endsWith(".svg") ||
                uri.endsWith(".webp") ||
                uri.endsWith(".woff2") ||
                uri.contains("favicon.ico");

        HttpSession session = request.getSession(false);
        boolean logado = session != null && session.getAttribute("usuarioLogado") != null;

        if (logado || recursoPublico) {
            chain.doFilter(request, response); // continua para o recurso solicitado
        } else {
            response.sendRedirect(context + "/login");
        }
    }
}