const apiUrl = 'http://localhost:5132/api/QualityDocument';

        document.getElementById('updateForm').addEventListener('submit', function (e) {
            e.preventDefault();

            const id = document.getElementById('updateId').value;
            const updatedDocument = {
                title: document.getElementById('updateTitle').value,
                author: document.getElementById('updateAuthor').value,
                category: document.getElementById('updateCategory').value,
                date: document.getElementById('updateDate').value,
                filePath: document.getElementById('updateFilePath').value
            };

            fetch(`${apiUrl}/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updatedDocument)
            })
            .then(res => {
                if (res.ok) return res.json();
                throw new Error('Error al actualizar el documento');
            })
            .then(data => {
                document.getElementById('updateMessage').innerHTML = `<div class="alert alert-success">Documento actualizado correctamente.</div>`;
                document.getElementById('updateForm').reset();
            })
            .catch(err => {
                document.getElementById('updateMessage').innerHTML = `<div class="alert alert-danger">${err.message}</div>`;
            });
        });