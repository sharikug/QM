document.addEventListener("DOMContentLoaded", () => {
    const API_URL = "http://localhost:5132/api/QualityDocument";
  
    // Envío del formulario
    document.getElementById("createForm").addEventListener("submit", async function (e) {
      e.preventDefault(); // ← evita el comportamiento por defecto (submit tradicional)
  
      const form = e.target;
      const formData = {
        title: form.title.value,
        author: form.author.value,
        category: form.category.value,
        date: form.date.value,
        filePath: form.filepath.value
      };
  
      try {
        const response = await fetch(API_URL, {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(formData)
        });
  
        if (!response.ok) throw new Error("Error al guardar el documento.");
  
        document.getElementById("uploadStatus").innerText = "Documento guardado exitosamente.";
        form.reset();
        fetchDocuments(); // Recarga la tabla
      } catch (error) {
        console.error(error);
        document.getElementById("uploadStatus").innerText = "Error al guardar el documento.";
      }
    });
  
    // Función para traer los datos
    async function fetchDocuments() {
      try {
        const response = await fetch(API_URL);
        const data = await response.json();
        displayData(data);
      } catch (err) {
        console.error("Error al obtener datos:", err);
      }
    }
  
    // Mostrar en tabla
    function displayData(documents) {
      const tableBody = document.getElementById("data-body");
      tableBody.innerHTML = "";
      documents.forEach(doc => {
        const row = document.createElement("tr");
        row.innerHTML = `
          <td>${doc.id}</td>
          <td>${doc.title}</td>
          <td>${doc.author}</td>
          <td>${doc.category}</td>
          <td>${doc.date}</td>
          <td>${doc.filePath}</td>
        `;
        tableBody.appendChild(row);
      });
    }
  
    // Cargar datos al inicio
    fetchDocuments();
  });
  