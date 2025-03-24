<?php
session_start();
include("conexion.php"); // Conectar con la base de datos

// Verificar si el usuario tiene una sesión activa
if (!isset($_SESSION['userid'])) {
    header("Location: ../index.php");
    exit();
}

// Obtener el `pedido_id` desde la solicitud (por ejemplo, a través de un GET o POST)
$pedidoId = isset($_GET['pedido_id']) ? intval($_GET['pedido_id']) : 0;

if ($pedidoId <= 0) {
    echo json_encode(['error' => 'ID de pedido inválido.']);
    exit();
}

// Consulta SQL utilizando prepared statements
$sql = "SELECT 
            p.pedido_id,
            u.nombre AS mesero,
            m.numero_mesa,
            p.estado,
            p.fecha_hora,
            pr.nombre AS producto,
            dp.cantidad,
            dp.subtotal
        FROM Pedidos p
        JOIN Usuarios u ON p.usuario_id = u.usuario_id
        JOIN Mesas m ON p.mesa_id = m.mesa_id
        JOIN DetallePedido dp ON p.pedido_id = dp.pedido_id
        JOIN Productos pr ON dp.producto_id = pr.producto_id
        WHERE p.pedido_id = ? AND pr.categoria = 'platos'";

$stmt = $conn->prepare($sql);
$stmt->bind_param("i", $pedidoId);
$stmt->execute();
$result = $stmt->get_result();

// Verificar si hay resultados
if ($result->num_rows > 0) {
    $pedidoData = []; // Array para almacenar los resultados

    while ($row = $result->fetch_assoc()) {
        $pedidoData[] = [
            'pedido_id' => $row['pedido_id'],
            'mesero' => $row['mesero'],
            'numero_mesa' => $row['numero_mesa'],
            'estado' => $row['estado'],
            'fecha_hora' => $row['fecha_hora'],
            'producto' => $row['producto'],
            'cantidad' => $row['cantidad'],
            'subtotal' => $row['subtotal']
        ];
    }

    // Enviar la respuesta como JSON al frontend
    echo json_encode($pedidoData);
} else {
    echo json_encode(['error' => 'No se encontraron pedidos para este ID.']);
}

// Cerrar conexión y statement
$stmt->close();
$conn->close();
?>
