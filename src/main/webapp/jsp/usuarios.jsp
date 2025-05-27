<%-- usuarios.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="includes/header.jsp" %>

<div class="container mt-5">
    <h2 class="titulo">Listagem de Usuários</h2>

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

<!-- Modal de confirmação -->
<div class="modal fade" id="confirmarExclusaoModal" tabindex="-1" aria-labelledby="confirmarExclusaoModalLabel"
    aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="${pageContext.request.contextPath}/usuarios?action=excluir">
                <div class="modal-header">
                    <h5 class="modal-title" id="confirmarExclusaoModalLabel">Confirmar Exclusão</h5>
                    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"
                        aria-label="Fechar"></button>
                </div>
                <div class="modal-body">
                    Tem certeza que deseja excluir este usuário?
                    <input type="hidden" name="id" id="idToDelete">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-confirmar">Excluir</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="includes/footer.jsp" %>
