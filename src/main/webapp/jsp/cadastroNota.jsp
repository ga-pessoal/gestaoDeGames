<%-- cadastroNota.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="includes/header.jsp" %>

<!-- Formulário -->
<form action="${pageContext.request.contextPath}/notas" method="post" class="form-container mt-5">

    <h2 class="mb-4 text-center">Cadastrar Nota</h2>

    <!-- Mensagem de erro -->
    <c:if test="${not empty mensagem}">
        <div style="display: block;" class="erro-msg">
            ${mensagem}
        </div>
    </c:if>

    <!-- id do usuário -->
    <input type="text" id="id_usuario" name="id_usuario" style="display:none" value="${usuarioLogado.id}">

    <!-- Game -->
    <div class="mb-3">
        <label for="game" class="form-label">Game</label>
        <select class="form-select" id="game" name="id_game" required>
            <option value="">Selecione o game</option>
            <c:forEach var="g" items="${games}">
                <option value="${g.id}">${g.titulo}</option>
            </c:forEach>
        </select>
    </div>

    <!-- Nota -->
    <div class="mb-3">
        <label for="nota" class="form-label">Nota</label>
        <select class="form-select" id="nota" name="nota" required>
            <option value="">Selecione a nota</option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
            <option value="8">8</option>
            <option value="9">9</option>
            <option value="10">10</option>
        </select>
    </div>

    <!-- Botão -->
    <button type="submit" class="btn btn-enviar w-100">Cadastrar</button>
</form>

<%@ include file="includes/footer.jsp" %>
