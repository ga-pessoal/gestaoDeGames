<%@ page contentType="text/html;charset=UTF-8" %>

<!-- Modal de confirmação -->
<div class="modal fade" id="confirmarExclusaoModal" tabindex="-1" aria-labelledby="confirmarExclusaoModalLabel"
    aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <c:choose>
                <c:when test="${not empty usuarios}">
                    <form method="post" action="${pageContext.request.contextPath}/usuarios?action=excluir">
                </c:when>
                <c:when test="${not empty generos}">
                    <form method="post" action="${pageContext.request.contextPath}/generos?action=excluir">
                </c:when>
                <c:when test="${not empty games}">
                    <form method="post" action="${pageContext.request.contextPath}/games?action=excluir">
                </c:when>
            </c:choose>
                <div class="modal-header">
                    <h5 class="modal-title" id="confirmarExclusaoModalLabel">Confirmar Exclusão</h5>
                    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"
                        aria-label="Fechar"></button>
                </div>
                <div class="modal-body">
                    Tem certeza que deseja excluir este registro?
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