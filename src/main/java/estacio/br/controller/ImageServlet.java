package estacio.br.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;

@WebServlet("/uploads/*")
public class ImageServlet extends HttpServlet {
    private static final String IMAGE_DIR = "C:/GamesRate/uploads/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fileName = request.getPathInfo(); // /nome-da-imagem.jpg

        if (fileName == null || fileName.equals("/")) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404
            return;
        }

        File file = new File(IMAGE_DIR, fileName);
        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Define o tipo do conteúdo (MIME)
        String mimeType = getServletContext().getMimeType(file.getName());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);
        response.setContentLengthLong(file.length());

        // Envia a imagem
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
             BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream())) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}