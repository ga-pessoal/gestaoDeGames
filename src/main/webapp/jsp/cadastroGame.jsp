<%-- cadastroGame.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="includes/header.jsp" %>

<h1>Cadastro de Game</h1>

<form action="${pageContext.request.contextPath}/games" method="post" enctype="multipart/form-data">
    <label for="titulo">Título:</label><br>
    <input type="text" id="titulo" name="titulo" required><br><br>

    <label for="genero">Gênero:</label><br>
    <select id="genero" name="id_genero" required>
        <option value="">Selecione</option>
        <c:forEach var="g" items="${generos}">
            <option value="${g.id}">${g.titulo}</option>
        </c:forEach>
    </select><br><br>

    <label for="imagem">Imagem da capa:</label><br>
    <input type="file" id="imagem" name="imagem" accept="image/*"><br><br>

    <input type="submit" value="Cadastrar Game">
</form>

<p>${mensagem != null ? mensagem : ""}</p>

<%@ include file="includes/footer.jsp" %>
