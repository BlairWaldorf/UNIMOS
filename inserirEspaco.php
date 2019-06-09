<?php
     $unidadeEnsino   = $_POST['unidadeEnsino'];
     $diasCurso       = $_POST['diasCurso'];
     $cobrarEspaco    = $_POST['cobrarEspaco'];
     $espacoTexto     = $_POST['espacoTexto'];
     $data    = date("Y-m-d");
     
    echo $unidadeEnsino;echo"<br>";
    echo $diasCurso;echo"<br>";     
    echo $cobrarEspaco;echo"<br>";     
    echo $espacoTexto;echo"<br>";     
    echo $data;echo"<br>";          
     
    //parametros para o banco de dados!
    $servername = "localhost";
    $database = "unimos";
    $username = "root";
    $password = "";    
    
    // Create connection
    $conn = mysqli_connect($servername, $username, $password, $database);
    // Check connection
    if (!$conn) {
          die("Connection failed: " . mysqli_connect_error());
    }
     
    echo "Connected successfully";
     
    $sql  = "insert into espaco (unidadeEnsino,diasCurso,cobrarEspaco,espacoTexto,data) 
    values ('$unidadeEnsino','$diasCurso','$cobrarEspaco','$espacoTexto','$data')"; 
    if (mysqli_query($conn, $sql)) {
          echo "<script language='javascript' type='text/javascript'>alert('Registro cadastrado com sucesso!');window.location.href='index.html'</script>";
    } else {
          echo "<script language='javascript' type='text/javascript'>alert('Registro não cadastrado com sucesso!');window.location.href='index.html'</script>";
    }
    mysqli_close($conn);
?>
