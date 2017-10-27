<?php
use \Psr\Http\Message\ServerRequestIntance as Request;
use \Psr\Http\Message\ResponseInterface as Response;


if (PHP_SAPI == 'cli-server') {
    // To help the built-in PHP dev server, check if the request was actually for
    // something which should probably be served as a static file
    $file = __DIR__ . $_SERVER['REQUEST_URI'];
    if (is_file($file)) {
        return false;
    }
}
require __DIR__ . '/../vendor/autoload.php';
session_start();
// Instantiate the app
$settings = require __DIR__ . '/../src/settings.php';
$app = new \Slim\App($settings);
$app->get('/contatos', 'getContato');
$app->delete('/contatos/{id}','deleteContatos');

//Deleta um contato da agenda de id=?
function deleteContatos($request, $response, $args)
{  
    $id = $args['id'];
    $sql = "delete from contato where id = ".$id;
    try {
        $db = getConnection();
        $db->exec($sql);
        $db = null;
        echo 'Deletado';      
    } catch(PDOException $e) {
        echo '{"error":{"text":'. $e->getMessage() .'}}'; 
    }
}
//Lista um contato de id=?
$app->get('/contatos/{id}',function ($request, $response, $args)
{
    $id = $args['id'];
    $sql = "select * FROM contato where id=".$id;
    try {
        $db = getConnection();
        $stmt = $db->query($sql);  
        $wines = $stmt->fetchAll(PDO::FETCH_OBJ);
        $db = null;
        echo '{"id": ' . json_encode($wines) . '}';
    } catch(PDOException $e) {
        echo '{"error":{"text":'. $e->getMessage() .'}}'; 
    }

});
//adicionar um novo contato
//http://localhost/appAgendaBackend/public/addcontato?nome=pedro&email=pedro@gmail.com&senha=123
$app->post('/addcontato', function ($request, $response, $args) 
{ 
    $nome = $request->getParam('nome');
    $email = $request->getParam('email');
    $senha = $request->getParam('senha');	
	$endereco = $request->getParam('endereco');
    $sexo = $request->getParam('sexo');
    $foto = $request->getParam('foto');	
	$datanascimento = $request->getParam('datanascimento');	
	$estadocivil = $request->getParam('estadocivil');
	$registroativo = $request->getParam('registroativo');	
    $id=0;   
	
	$sql = "INSERT INTO contato (`id`,`nome`,`email`,`senha`,`endereco`,`sexo`,`foto`,`datanascimento`,`estadocivil`,`registroativo`) VALUES (:id, :nome, :email, :senha, :endereco, :sexo, :foto, :datanascimento, :estadocivil, :registroativo)";
	//echo $sql;
    try {
        $db = getConnection();
        $stmt = $db->prepare($sql); 
        $stmt->bindParam("id", $id);
        $stmt->bindParam("nome", $nome);
        $stmt->bindParam("email",$email);
        $stmt->bindParam("senha",$senha);	
        $stmt->bindParam("endereco", $endereco);
        $stmt->bindParam("sexo",$sexo);
        $stmt->bindParam("foto",$foto);		
		$stmt->bindParam("datanascimento",$datanascimento);
        $stmt->bindParam("estadocivil",$estadocivil);		
		$stmt->bindParam("registroativo",$registroativo);
		 echo 'registroativo '.$registroativo;
		 
        $stmt->execute();
        $db = null;
         echo '{"addcontato":{"Ecodigo": "1" ,"Mensagem":'. '"Created successfully"' .',"Dados": "" }}';      
    } catch(PDOException $e) {
        echo '{"error":{"text":'. $e->getMessage() .'}}'; 
    }
});
//Realiza login
//http://127.0.0.1/contato/login?email=carlos@gmail.com&senha=123
$app->get('/contato/login', function ($request, $response, $args) {
   
    $email = $request->getParam('email');
    $senha = $request->getParam('senha');

     $sql = "select * FROM contato where email='".$email."' and senha='".$senha."'";
     //echo $sql;
    try {
        $db = getConnection();
        $stmt = $db->query($sql);  
        $notas = $stmt->fetchAll(PDO::FETCH_OBJ);
        $db = null;
        echo '{"login": ' . json_encode($notas) . '}';
    } catch(PDOException $e) {
        echo '{"error":{"text":'. $e->getMessage() .'}}'; 
    }
});

//Realiza atualizacao
//http://127.0.0.1/contatos/editar?id=2&nome=jose&senha=123&endereco=medico
$app->get('/contato/editar', function ($request, $response, $args) {
   
    $id = $request->getParam('id');
    $nome = $request->getParam('nome');
    $senha = $request->getParam('senha');	
	$endereco = $request->getParam('endereco');
	
    try {
        $db = getConnection();
        $stmt = $db->prepare("UPDATE contato SET nome=?,senha=?,endereco=? WHERE id=?");
        $stmt->execute(array($nome,$senha,$endereco,$id));
		$affected_rows = $stmt->rowCount();
        $db = null;
        echo '{"editar": ' . $affected_rows . '}';
    } catch(PDOException $e) {
        echo '{"error":{"text":'. $e->getMessage() .'}}'; 
    }
});

//lista todos os contatos
function getContato() {
    $sql = "select * FROM contato";
    try {
        $db = getConnection();
        $stmt = $db->query($sql);  
        $avaliacoes = $stmt->fetchAll(PDO::FETCH_OBJ);
        $db = null;
        echo '{"contatos": ' . json_encode($avaliacoes) . '}';
    } catch(PDOException $e) {
        echo '{"error":{"text":'. $e->getMessage() .'}}'; 
    }
}
//seta a comunicação com o bd
function getConnection() {
    $dbhost="127.0.0.1";
    $dbuser="root";
    $dbpass="";
    $dbname="bdappagenda";
    $dbh = new PDO("mysql:host=$dbhost;dbname=$dbname", $dbuser, $dbpass);  
    $dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    return $dbh;
}

// Set up dependencies
require __DIR__ . '/../src/dependencies.php';

// Register middleware
require __DIR__ . '/../src/middleware.php';

// Register routes
require __DIR__ . '/../src/routes.php';

// Run app
$app->run();