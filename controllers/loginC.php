<?php
session_start(); // iniciar la sesión
include("conexion.php");

//Verificar si el formulario ha sido enviado
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $user = $_POST['user'];
    $pass = $_POST['pass'];

    // Consulta SQL para verificar las credenciales del usuario
    $sql = "SELECT usuario_id, nombre FROM usuarios WHERE usuario = '$user' AND pass = '$pass'";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        //Inicio de sesión exitoso
        $row = $result->fetch_assoc();
        $userId = $row['usuario_id'];

        //Almacenar el ID del usuario en la sesión
        $_SESSION['userid'] = $userId;

        //Redirigir a la página de éxito
        header("Location: ../Views/home.php");
        exit();
    } else {
        //Credenciales incorrectas
        echo "<style>.err{ 
            width: 70%;
            text-align: center;
            height: 4vh;-
            margin-left: 15%;
            background-color: red;
            border-radius:5px;
            color:white;}
            </style><p class='err'>Usuario no encontrado</p>";
    }
}

//Cerrar la conexión a la base de datos
$conn->close();
?>