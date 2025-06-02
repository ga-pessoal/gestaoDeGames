<%-- cadastroGame.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="includes/header.jsp" %>

<!-- Formulário -->
<c:choose>
    <c:when test="${not empty game.id}">
        <form action="${pageContext.request.contextPath}/games?action=editar" method="post" class="form-container mt-5">
    </c:when>
    <c:otherwise>
        <form action="${pageContext.request.contextPath}/games" method="post" enctype="multipart/form-data" class="form-container mt-5">
    </c:otherwise>
</c:choose>
    <h2 class="mb-4 text-center">${game.id != null ? "Editar" : "Cadastrar"} Game</h2>

    <!-- Mensagem de erro -->
    <c:if test="${not empty mensagem}">
        <div style="display: block;" class="erro-msg">
            ${mensagem}
        </div>
    </c:if>

    <!-- ID -->
    <input type="text" id="id_game" name="id_game" style="display:none" value="${game.id}">

    <!-- Título -->
    <div class="mb-3">
        <label for="titulo" class="form-label">Título do Jogo</label>
        <input type="text" class="form-control" id="titulo" name="titulo" required value="${game.titulo}">
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

    <c:if test="${empty game.id}">
        <!-- Imagem da Capa -->
        <div class="mb-4">
            <label for="imagem" class="form-label">Imagem da Capa</label>
            <input type="file" class="form-control" id="imagem" name="imagem" accept="image/*" required>
        </div>
    </c:if>

    <!-- Botão -->
    <button type="submit" class="btn btn-enviar w-100">${game.id != null ? "Editar" : "Cadastrar"}</button>
</form>

<%@ include file="includes/footer.jsp" %>
