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
                            <a href="/editar-usuario?id=1" class="btn btn-editar btn-sm me-2">Editar</a>
                            <a href="/excluir-usuario?id=1" class="btn btn-excluir btn-sm">Excluir</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="includes/footer.jsp" %>
