<%-- cadastroUsuario.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="includes/header.jsp" %>

<!-- Formulário de Cadastro de Usuário -->

<c:choose>
    <c:when test="${not empty usuario.id}">
        <form action="${pageContext.request.contextPath}/usuarios?action=editar" method="post" class="form-container mt-5">
    </c:when>
    <c:otherwise>
        <form action="${pageContext.request.contextPath}/usuarios" method="post" class="form-container mt-5">
    </c:otherwise>
</c:choose>
    <h2 class="mb-4 text-center">Cadastro de Usuário</h2>

    <!-- Mensagem de erro -->
    <c:if test="${not empty mensagem}">
        <div style="display: block;" class="erro-msg">
            ${mensagem}
        </div>
    </c:if>

    <!-- ID -->
    <input type="text" id="id_usuario" name="id_usuario" style="display:none" value="${usuario.id}">

    <!-- Nome -->
    <div class="mb-3">
        <label for="nome" class="form-label">Nome</label>
        <input type="text" class="form-control" id="nome" name="nome" value="${usuario.nome}" required>
    </div>

    <!-- Email -->
    <div class="mb-3">
        <label for="email" class="form-label">E-mail</label>
        <input type="email" class="form-control" id="email" name="email" value="${usuario.email}" required>
    </div>

    <c:if test="${empty usuario.id}">
        <!-- Senha -->
        <div class="mb-3">
            <label for="senha" class="form-label">Senha</label>
            <input type="password" class="form-control" id="senha" name="senha" value="${usuario.senha}" required>
        </div>

        <!-- Confirmar Senha -->
        <div class="mb-3">
            <label for="senha" class="form-label">Confirmar Senha</label>
            <input type="password" class="form-control" id="confirmar_senha" name="confirmar_senha" value="${usuario.senha}" required>
        </div>
    </c:if>

    <!-- Botão -->
    <button type="submit" class="btn btn-enviar w-100 mt-2">${usuario.id != null ? "Editar" : "Cadastrar"}</button>
</form>

<%@ include file="includes/footer.jsp" %>
