<!-- includes/header.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title><c:out value="${pageTitle}" default="Minha Aplicação" /></title>

    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <!-- JS -->
    <script src="${pageContext.request.contextPath}/js/script.js" defer></script>
</head>
<body>