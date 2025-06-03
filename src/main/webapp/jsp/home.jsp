<%-- home.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="includes/header.jsp" %>

<div class="container py-4">
    <!-- Saudação -->
    <h1 class="titulo-home">Olá, ${usuarioLogado.nome}!</h1>

    <!-- Botão cadastrar nota -->
    <c:if test="${usuarioLogado.id_tipo_usuario == 2}">
        <div class="text-center mb-4">
            <a href="${pageContext.request.contextPath}/notas?action=cadastro" class="btn btn-cadastrar">Cadastrar Nota</a>
        </div>
    </c:if>

    <!-- Lista de Cards de Jogos -->
    <div class="row g-4">
        <c:forEach var="game" items="${games}">
            <div class="col-12 col-sm-6 col-md-4 col-lg-3">
                <div class="card-jogo">
                    <img src="${pageContext.request.contextPath}/uploads/${game.nomeImagem}" alt="Capa do jogo">
                    <div class="card-body">
                        <h5 class="card-title">${game.titulo}</h5>
                        <p class="card-text">${usuarioLogado.id_tipo_usuario == 1 ? 'Média' : 'Nota'}: ${game.nota != 0 ? game.nota : 'Sem nota'}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<%@ include file="includes/footer.jsp" %>

