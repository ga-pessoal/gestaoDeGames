<%-- cadastroGame.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="includes/header.jsp" %>

<!-- Formulário -->
<form action="${pageContext.request.contextPath}/games" method="post" enctype="multipart/form-data" class="form-container mt-5">
    <h2 class="mb-4 text-center">Cadastrar Game</h2>

    <!-- Mensagem de erro -->
    <c:if test="${not empty mensagem}">
        <div style="display: block;" class="erro-msg">
            ${mensagem}
        </div>
    </c:if>

    <!-- Título -->
    <div class="mb-3">
        <label for="titulo" class="form-label">Título do Jogo</label>
        <input type="text" class="form-control" id="titulo" name="titulo" required>
    </div>

    <!-- Gênero -->
    <div class="mb-3">
        <label for="genero" class="form-label">Gênero</label>
        <select class="form-select" id="genero" name="id_genero" required>
            <option value="">Selecione o gênero</option>
            <c:forEach var="g" items="${generos}">
                <option value="${g.id}">${g.titulo}</option>
            </c:forEach>
        </select>
    </div>

    <!-- Imagem da Capa -->
    <div class="mb-4">
        <label for="imagem" class="form-label">Imagem da Capa</label>
        <input type="file" class="form-control" id="imagem" name="imagem" accept="image/*" required>
    </div>

    <!-- Botão -->
    <button type="submit" class="btn btn-enviar w-100">Cadastrar</button>
</form>

<%@ include file="includes/footer.jsp" %>
