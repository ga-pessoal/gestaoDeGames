<%-- cadastroNota.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="includes/header.jsp" %>

<h1>Cadastro de notas</h1>

<form action="${pageContext.request.contextPath}/notas" method="post">

    <input type="text" id="id_usuario" name="id_usuario" style="display:none" value="1">

    <label for="games">Game:</label><br>
    <select id="games" name="id_game" required>
        <option value="">Selecione</option>
        <c:forEach var="g" items="${games}">
            <option value="${g.id}">${g.titulo}</option>
        </c:forEach>
    </select><br><br>

    <label for="nota">Nota:</label><br>
    <select id="nota" name="nota" required>
        <option value="">Selecione</option>

        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
    </select><br><br>

    <input type="submit" value="Cadastrar Nota">
</form>

<p>${mensagem != null ? mensagem : ""}</p>

<%@ include file="includes/footer.jsp" %>
