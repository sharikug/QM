<?php
    session_start();

    include("conexion.php");

    if (isset($_SESSION['mesaId'])) {
        $mesa = $_SESSION['mesaId'];  
    } else {
        header("Location: ../index.php");
    } 

    if (isset($_SESSION['userid'])) {
        $userId = $_SESSION['userid'];  
    } else {
        header("Location: ../index.php");
    } 

    include("../controllers/conexion.php");
                // Consulta SQL
                $sql = "SELECT Mesas.numero_mesa, 
                        Productos.nombre AS producto, 
                        DetallePedido.cantidad, 
                        Productos.precio, 
                        Productos.categoria, 
                        (DetallePedido.cantidad * Productos.precio) AS total
                    FROM Pedidos
                    INNER JOIN Mesas ON Pedidos.mesa_id = Mesas.mesa_id
                    INNER JOIN DetallePedido ON Pedidos.pedido_id = DetallePedido.pedido_id
                    INNER JOIN Productos ON DetallePedido.producto_id = Productos.producto_id
                    WHERE Mesas.mesa_id = $mesa";
                        

                $result = $conn->query($sql);

                // Inicializar variables para los totales
                $subtotal = 0;
                $totalImpuestos = 0;
                $totalPropina = 0;

                if ($result->num_rows > 0) {
                    echo "<tr>
                            <th>Número de Mesa</th>
                            <th>Producto</th>
                            <th>Cantidad</th>
                            <th>Precio Unitario</th>
                            <th>Total</th>
                          </tr>";

                    // Recorrer y mostrar los resultados
                    while ($row = $result->fetch_assoc()) {
                        $impuesto_producto = 0;
                        

                        // Calcular el impuesto solo para los productos de la categoría 'platos'
                        if ($row["categoria"] == 'platos'||$row["categoria"] == 'entradas'||$row["categoria"] == 'bebidas' ) {
                            $totalImpuestos += $row['total']*0.08;
                            $totalPropina += $row['total']*0.1;
                        }

                        // Mostrar la fila en la tabla
                        echo "<tr>
                                <td>" . $row["numero_mesa"] . "</td>
                                <td>" . $row["producto"] . "</td>
                                <td>" . $row["cantidad"] . "</td>
                                <td>" . number_format($row["precio"]) . "</td>
                                <td>" . number_format($row["total"]) . "</td>
                              </tr>";

                        // Sumar al subtotal
                        $subtotal += $row["total"];
                    } 

                    // Mostrar las filas con los totales
                    echo "<tr>
                            <td colspan='4'><strong>Subtotal (sin impuestos):</strong></td>
                            <td>" . number_format($subtotal - $totalImpuestos, 2) . "</td>
                        </tr>";

                    echo "<tr>
                            <td colspan='4'><strong>Total de Impuestos (8% solo en platos):</strong></td>
                            <td>" . number_format($totalImpuestos, 2) . "</td>
                        </tr>";

                    echo "<tr>
                            <td colspan='4'><strong>Subtotal:</strong></td>
                            <td>" . number_format($subtotal, 2) . "</td>
                        </tr>";
                    
                    echo "<tr>
                          <td colspan='4'><strong>Total propina (10% solo en platos):</strong></td>
                          <td>" . number_format($totalPropina, 2) . "</td>
                        </tr>";

                        $subtotal = $totalImpuestos+$totalPropina+$subtotal;
                    echo "<tr>
                            <td colspan='4'><strong>Total General (con impuestos):</strong></td>
                            <td>" . number_format($subtotal, 2) . "</td>
                          </tr>";

                } else {
                    echo "No se encontraron resultados.";
                }

                // Cerrar la conexión
                $conn->close();
?>