const apiUrl = 'http://localhost:5132/api/QualityDocument'; // cambia el puerto si es distinto

        document.getElementById('deleteForm').addEventListener('submit', function (e) {
            e.preventDefault();

            const id = document.getElementById('deleteId').value;

            fetch(`${apiUrl}/${id}`, {
                method: 'DELETE'
            })
            .then(res => {
                if (res.ok) {
                    document.getElementById('deleteMessage').innerHTML =
                        `<div class="alert alert-success">Documento con ID ${id} eliminado correctamente.</div>`;
                    document.getElementById('deleteForm').reset();
                } else if (res.status === 404) {
                    throw new Error('Documento no encontrado');
                } else {
                    throw new Error('Error al eliminar el documento');
                }
            })
            .catch(err => {
                document.getElementById('deleteMessage').innerHTML =
                    `<div class="alert alert-danger">${err.message}</div>`;
            });
        });