<%-- cadastroUsuario.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="includes/header.jsp" %>

<!-- Formulário de Cadastro de Usuário -->
<form action="${pageContext.request.contextPath}/usuarios" method="post" class="form-container mt-5">
    <h2 class="mb-4 text-center">Cadastro de Usuário</h2>

    <!-- Mensagem de erro -->
    <c:if test="${not empty mensagem}">
        <div style="display: block;" class="erro-msg">
            ${mensagem}
        </div>
    </c:if>

    <!-- Nome -->
    <div class="mb-3">
        <label for="nome" class="form-label">Nome</label>
        <input type="text" class="form-control" id="nome" name="nome" required>
    </div>

    <!-- Email -->
    <div class="mb-3">
        <label for="email" class="form-label">E-mail</label>
        <input type="email" class="form-control" id="email" name="email" required>
    </div>

    <!-- Senha -->
    <div class="mb-3">
        <label for="senha" class="form-label">Senha</label>
        <input type="password" class="form-control" id="senha" name="senha" required>
    </div>

    <!-- Senha -->
    <div class="mb-3">
        <label for="senha" class="form-label">Confirmar Senha</label>
        <input type="password" class="form-control" id="confirmar_senha" name="confirmar_senha" required>
    </div>

    <!-- Botão -->
    <button type="submit" class="btn btn-enviar w-100 mt-2">Cadastrar</button>
</form>

<%@ include file="includes/footer.jsp" %>
