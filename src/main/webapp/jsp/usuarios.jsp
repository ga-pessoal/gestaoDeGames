<%-- usuarios.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="includes/header.jsp" %>

<div class="container mt-5">
    <h2 class="titulo">Listagem de Usuários</h2>

    <div class="d-flex justify-content-start mb-4">
        <a href="${pageContext.request.contextPath}/usuarios?action=cadastro" class="btn btn-success fw-bold">Cadastrar Usuário</a>
    </div>

    <div class="table-responsive">
        <table class="table table-dark-custom table-bordered align-middle text-center">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="u" items="${usuarios}">
                    <tr>
                        <td>${u.id}</td>
                        <td>${u.nome}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/usuarios?action=editar&id=${u.id}" class="btn btn-editar btn-sm me-2">Editar</a>
                            <!-- Botão que abre a modal -->
                            <button class="btn btn-excluir btn-sm" data-bs-toggle="modal" data-bs-target="#confirmarExclusaoModal" data-id="${u.id}">Excluir</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@ include file="includes/modalConfirmacao.jsp" %>
<%@ include file="includes/footer.jsp" %>
