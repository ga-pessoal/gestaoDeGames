<%-- home.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="includes/header.jsp" %>

<h1>Bem-vindo!</h1>

<table>
    <tr>
        <th>Titulo</th>
        <th>genero</th>
        <th>Nota</th>
    </tr>
    <c:forEach var="game" items="${games}">
        <tr>
            <td>${game.titulo}</td>
            <td>${game.genero}</td>
            <td>
                ${game.nota != 0 ? game.nota : 'Sem nota'}
            </td>
        </tr>
    </c:forEach>
</table>

<%@ include file="includes/footer.jsp" %>

