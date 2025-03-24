<?php
include("conexion.php");

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
      WHERE p.pedido_id = 1
      AND pr.categoria = 'platos'"; // Utilizamos un placeholder para evitar inyecciones SQL

 // Ejecutar la consulta
 $result = $conn->query($sql);
 $row = $result->fetch_assoc();

 // Comprobar si se encontraron resultados
 if ($result->num_rows > 0) {
     // Mostrar los resultados en una tabla HTML
     echo "<table border='5'>";
     echo "<tr>
             <th>Pedido ID</th>
             <th>Mesero</th>
             <th>Número de Mesa</th>
             <th>Estado</th>
             <th>Fecha y Hora</th>
             <th>Producto</th>
             <th>Cantidad</th>
             <th>Subtotal</th>
           </tr>";
         echo "<tr>";
         echo "<td>" . htmlspecialchars($row['pedido_id']) . "</td>";
         echo "<td>" . htmlspecialchars($row['mesero']) . "</td>";
         echo "<td>" . htmlspecialchars($row['numero_mesa']) . "</td>";
         echo "<td>" . htmlspecialchars($row['estado']) . "</td>";
         echo "<td>" . htmlspecialchars($row['fecha_hora']) . "</td>";
         echo "<td>" . htmlspecialchars($row['producto']) . "</td>";
         echo "<td>" . htmlspecialchars($row['cantidad']) . "</td>";
         echo "<td>" . htmlspecialchars($row['subtotal']) . "</td>";
         echo "</tr>";
     
     echo "</table>";
 }