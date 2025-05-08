<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de Usuários</title>
</head>
<body>
    <h2>Usuários</h2>
    <ul>
        <c:forEach var="u" items="${usuarios}">
            <li>${u.nome} - ${u.email}</li>
        </c:forEach>
    </ul>
</body>
</html>