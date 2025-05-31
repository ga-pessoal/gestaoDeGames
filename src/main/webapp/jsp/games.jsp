<%-- games.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="includes/header.jsp" %>

<div class="container mt-5">
    <h2 class="titulo">Listagem de Games</h2>
<div class="d-flex justify-content-start mb-4">
        <a href="${pageContext.request.contextPath}/games?action=cadastro" class="btn btn-success fw-bold">Cadastrar Games</a>
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
                   <c:forEach var="g" items="${games}">
                       <tr>
                           <td>${g.id}</td>
                           <td>${g.titulo}</td>
                           <td>
                              <a href="${pageContext.request.contextPath}/games?action=editar&id=${g.id}" class="btn btn-editar btn-sm me-2">Editar</a>
                              <!-- Botão que abre a modal -->
                              <button class="btn btn-excluir btn-sm" data-bs-toggle="modal" data-bs-target="#confirmarExclusaoModal" data-id="${g.id}">Excluir</button>
                           </td>
                       </tr>
                   </c:forEach>
               </tbody>
            </table>
        </div>
    </div>
</div>
<%@ include file="includes/modalConfirmacao.jsp" %>
<%@ include file="includes/footer.jsp" %>
