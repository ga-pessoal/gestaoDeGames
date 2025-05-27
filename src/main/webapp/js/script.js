// Captura o ID do botão clicado e passa para o input oculto da modal
const excluirModal = document.getElementById('confirmarExclusaoModal');
excluirModal.addEventListener('show.bs.modal', function (event) {
    const button = event.relatedTarget;
    const id = button.getAttribute('data-id');
    document.getElementById('idToDelete').value = id;
});