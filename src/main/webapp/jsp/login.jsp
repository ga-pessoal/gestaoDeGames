<%-- login.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="includes/header.jsp" %>

<!-- Título principal -->
<div class="titulo-principal">GameRate</div>

<div class="login-container">
    <h2>Login</h2>

    <!-- Mensagem de erro -->
    <c:if test="${not empty mensagem}">
        <div style="display: block;" class="erro-msg">
            ${mensagem}
        </div>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/login">
        <div class="mb-3">
            <label for="email" class="form-label">E-mail</label>
            <input type="email" class="form-control" id="email" name="email" required
                placeholder="seuemail@email.com">
        </div>

        <div class="mb-3">
            <label for="senha" class="form-label">Senha</label>
            <input type="password" class="form-control" id="senha" name="senha" required
                placeholder="Digite sua senha">
        </div>

        <button type="submit" class="btn btn-login">Entrar</button>
    </form>

    <a href="/GestaoDeGames/usuarios?action=cadastro" class="link-cadastrar">Não tem uma conta? Cadastre-se</a>
</div>

<style>
    body {
        min-height: 100vh;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
    }
</style>

<%@ include file="includes/footer.jsp" %>