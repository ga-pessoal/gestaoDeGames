<%-- cadastroGenero.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="includes/header.jsp" %>

<!-- Formulário -->
<c:choose>
    <c:when test="${not empty usuario.id}">
        <form action="${pageContext.request.contextPath}/generos?action=editar" method="post" class="form-container mt-5">
    </c:when>
    <c:otherwise>
        <form action="${pageContext.request.contextPath}/generos" method="post" class="form-container mt-5">
    </c:otherwise>
</c:choose>
    <h2 class="mb-4 text-center">${genero.id != null ? "Editar" : "Cadastrar"} Gênero</h2>

    <!-- Mensagem de erro -->

    <c:if test="${not empty mensagem}">
        <div style="display: block;" class="erro-msg">
                ${mensagem}
        </div>
    </c:if>

    <!-- ID -->
    <input type="text" id="id_genero" name="id_genero" style="display:none" value="${genero.id}">

    <!-- Título -->
    <div class="mb-3">
        <label for="titulo" class="form-label">Título do genero</label>
        <input type="text" class="form-control" id="titulo" name="titulo" value ="${genero.titulo}"required>
    </div>

    <!-- Botão -->
    <button type="submit" class="btn btn-enviar w-100">${genero.id != null ? "Editar" : "Cadastrar"}</button>
</form>

<%@ include file="includes/footer.jsp" %>
