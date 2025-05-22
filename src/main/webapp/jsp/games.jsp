<%-- games.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="includes/header.jsp" %>

<div class="container mt-5">
    <h2 class="titulo">Listagem de Games</h2>
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
                    <tr>
                        <td>1</td>
                        <td>Gabriel Mattos</td>
                        <td>
                            <a href="/editar-usuario?id=1" class="btn btn-editar btn-sm me-2">Editar</a>
                            <a href="/excluir-usuario?id=1" class="btn btn-excluir btn-sm">Excluir</a>
                        </td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>João Silva</td>
                        <td>
                            <a href="/editar-usuario?id=2" class="btn btn-editar btn-sm me-2">Editar</a>
                            <a href="/excluir-usuario?id=2" class="btn btn-excluir btn-sm">Excluir</a>
                        </td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>Maria Oliveira</td>
                        <td>
                            <a href="/editar-usuario?id=3" class="btn btn-editar btn-sm me-2">Editar</a>
                            <a href="/excluir-usuario?id=3" class="btn btn-excluir btn-sm">Excluir</a>
                        </td>
                    </tr>
                    <tr>
                        <td>4</td>
                        <td>Pedro Almeida</td>
                        <td>
                            <a href="/editar-usuario?id=4" class="btn btn-editar btn-sm me-2">Editar</a>
                            <a href="/excluir-usuario?id=4" class="btn btn-excluir btn-sm">Excluir</a>
                        </td>
                    </tr>
                    <tr>
                        <td>5</td>
                        <td>Ana Souza</td>
                        <td>
                            <a href="/editar-usuario?id=5" class="btn btn-editar btn-sm me-2">Editar</a>
                            <a href="/excluir-usuario?id=5" class="btn btn-excluir btn-sm">Excluir</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file="includes/footer.jsp" %>
