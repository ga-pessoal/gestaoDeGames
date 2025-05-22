<%-- cadastroGenero.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="includes/header.jsp" %>

<!-- Formulário -->
<form action="/cadastrar-jogo" method="post" enctype="multipart/form-data" class="form-container mt-5">
    <h2 class="mb-4 text-center">Cadastrar Gênero</h2>

    <!-- Mensagem de erro -->
    <div style="display: block;" id="erroLogin" class="erro-msg">
        Erro ao cadastrar gênero!
    </div>

    <!-- Título -->
    <div class="mb-3">
        <label for="titulo" class="form-label">Título do genero</label>
        <input type="text" class="form-control" id="titulo" name="titulo" required>
    </div>

    <!-- Botão -->
    <button type="submit" class="btn btn-enviar w-100">Cadastrar</button>
</form>

<%@ include file="includes/footer.jsp" %>
