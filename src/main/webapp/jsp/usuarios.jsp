<%-- usuarios.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="includes/header.jsp" %>

<h1>Usuários</h1>
<ul>
    <c:forEach var="u" items="${usuarios}">
        <li>${u.nome} - ${u.email}</li>
    </c:forEach>
</ul>

<%@ include file="includes/footer.jsp" %>

