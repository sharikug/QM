<?php

    session_start();

    include("conexion.php");

    if (isset($_SESSION['userid'])) {
        $userId = $_SESSION['userid'];  
    } else {
        header("Location: ../index.php");
    } 

    include("conexion.php");

    // Consulta SQL para verificar las credenciales del usuario
    $sql = "SELECT nombre, rol FROM usuarios WHERE usuario_id = '$userId'";
    $result = $conn->query($sql);

    $row = $result->fetch_assoc();
    $name = ucfirst($row["nombre"]);
    $rol = ucfirst($row["rol"]);

    //.......................................................................

    //Consulta para obtener mesas con pedidos activos
    $query = "SELECT mesa_id FROM Pedidos WHERE estado IN ('pendiente', 'servido')";
    $result = mysqli_query($conn, $query);

    //Almacenar mesas ocupadas en un array
    $mesas_ocupadas = array();
    while ($row = mysqli_fetch_assoc($result)) {
        $mesas_ocupadas[] = $row['mesa_id'];
    }
?>