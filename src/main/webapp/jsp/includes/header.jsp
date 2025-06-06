<!-- includes/header.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>${pageTitle != null ? pageTitle : "Games Rate"}</title>

    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <!-- JS -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
        crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" ></script>

</head>
<body>
    <!-- Navbar -->
    <c:if test="${not empty usuarioLogado}">
        <nav class="navbar navbar-expand-lg navbar-futurista fixed-top">
            <div class="container-fluid d-flex justify-content-between">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/home">GameRate</a>

                <div class="dropdown ms-auto">
                    <button class="btn config-btn dropdown-toggle" type="button" data-bs-toggle="dropdown"
                        aria-expanded="false">
                        ⚙️ Configurações
                    </button>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/usuarios?action=editar&id=${usuarioLogado.id}">Minha Conta</a></li>
                        <c:if test="${usuarioLogado.id_tipo_usuario == 1}">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/generos">Generos</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/games">Games</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/usuarios">Usuários</a></li>
                        </c:if>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Sair</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </c:if>