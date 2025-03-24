<?php
session_start();
include("conexion.php");

//Verificar si el formulario ha sido enviado
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $table = $_POST['table'];

    // Consulta SQL para verificar las credenciales del usuario
    $sql = "SELECT mesa_id, numero_mesa FROM mesas WHERE mesa_id = '$table'";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        //Inicio de sesión exitoso
        $row = $result->fetch_assoc();
        $mesa = $row['mesa_id'];

        //Almacenar el ID del usuario en la sesión
        $_SESSION['mesaId'] = $mesa;

        //Redirigir a la página de éxito
        header("Location: ../Views/bill.php");
        exit();
    } else {
        //Credenciales incorrectas
        echo "<style>.err{ 
            width: 70%;
            text-align: center;
            height: 4vh;
            margin-left: 15%;
            background-color: red;
            border-radius:5px;
            color:white;}
            </style><p class='err'>Mesa no encontrada</p>";
    }
}

//Cerrar la conexión a la base de datos
$conn->close();
?>