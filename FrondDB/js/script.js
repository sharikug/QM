// Cambia la URL si tu servidor no está en localhost o corre en otro puerto
const API_URL = "http://localhost:5132/api/QualityDocument";

async function fetchDocuments() {
  try {
    const response = await fetch(API_URL);
    if (!response.ok) throw new Error(`Error HTTP: ${response.status}`);

    const data = await response.json();
    displayData(data);
  } catch (error) {
    console.error("Error al consumir la API:", error);
    document.getElementById("data-body").innerHTML =
      `<tr><td colspan="3">No se pudieron cargar los datos</td></tr>`;
  }
}

function displayData(documents) {
  const tableBody = document.getElementById("data-body");
  tableBody.innerHTML = "";

  documents.forEach(doc => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${doc.id}</td>
      <td>${doc.title || "Sin nombre"}</td>
      <td>${doc.author || "Sin fecha"}</td>
      <td>${doc.category || "Sin fecha"}</td>
      <td>${doc.date || "Sin fecha"}</td>
      <td>${doc.filePath || "Sin fecha"}</td>
    `;
    tableBody.appendChild(row);
  });
}

fetchDocuments();
