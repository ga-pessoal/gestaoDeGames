<%-- cadastroUsuario.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="includes/header.jsp" %>

<h1>Cadastro de usuário</h1>

<form action="${pageContext.request.contextPath}/usuarios" method="post">
    <label for="nome">Nome:</label><br>
    <input type="text" id="nome" name="nome" required><br><br>

    <label for="email">Emil:</label><br>
    <input type="email" id="email" name="email" required><br><br>

   <label for="senha">Senha:</label><br>
   <input type="password" id="senha" name="senha" required><br><br>

   <label for="confirmar_senha">Repetir senha:</label><br>
   <input type="password" id="confirmar_senha" name="confirmar_senha" required><br><br>

   <input type="submit" value"Cadastrar usuario">

</form>

<p>${mensagem != null ? mensagem : ""}</p>

<%@ include file="includes/footer.jsp" %>
