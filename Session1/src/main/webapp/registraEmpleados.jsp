<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/bootstrap.bundle.js" type="text/javascript"></script>
<script src="js/bootstrap.esm.js" type="text/javascript"></script>
<script src="js/jquery-4.0.0.min.js" type="text/javascript"></script>

<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap-grid.css" rel="stylesheet">
<link href="css/bootstrap-reboot.css" rel="stylesheet">
<link href="css/bootstrap-utilities.css" rel="stylesheet">


</head>
<body>
	<div class="container">
		<h1>Registro de Empleados</h1>
		<form id="formEmpleado" method="post" novalidate >
			<div class="row" style="margin-top: 2%;">
				<div class="col-3">
					<label for="nombre">Nombre</label> 
					<input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingrese el nombre" maxlength="30" required>
					<div class="invalid-feedback">Ingrese el nombre del empleado</div>
				</div>
				<div class="col-3">
					<label for="nombre">Apellido</label> 
					<input type="text" class="form-control" id="apellido" name="apellido" placeholder="Ingrese el apellido" maxlength="30" required>
					<div class="invalid-feedback">Ingrese el apellido del empleado</div>
				</div>
				<div class="col-3">
					<label for="nombre">Area</label> 
					<input type="text" class="form-control" id="area" name="area" placeholder="Ingrese el area" maxlength="30" required>
					<div class="invalid-feedback">Ingrese el area</div>
				</div>
				<div class="col-3">
					<label for="fecNac">Fecha de Nacimiento</label> 
					<input type="date" class="form-control" id="fecNac" name="fecNaci" required>
					<div class="invalid-feedback">Ingrese la Fecha de Nacimiento</div>
				</div>
				<div class="col-3">
					<label for="fecNac">Fecha de Ingreso</label> 
					<input type="date" class="form-control" id="fecIn" name="fecIng" required>
					<div class="invalid-feedback">Ingrese la Fecha de Ingreso</div>
				</div>
				<div class="col-3">
					<label for="correo">Correo</label> 
					<input type="email" class="form-control" id="correo" name="correo"	placeholder="Ingrese el correo" required>
					<div class="invalid-feedback">Ingrese el correo</div>
				</div>
			</div>
			<div class="row justify-content-center" style="margin-top: 2%">
				<button class="btn btn-primary" id="btnRegistrar"style="width: 200px">Registrar</button>
			</div>
		</form>
	</div>

<script type="text/javascript">
	$("#btnRegistrar").click(function(e) {
		console.log("click en registrar");		
		e.preventDefault(); //Evita que el formulario se envíe automáticamente

		
		let form = $('#formEmpleado')[0];
        if (form.checkValidity() === false) {
            $(form).addClass('was-validated');
            return;
        }

     
        
        
        $.ajax({
			url: 'RegistraEmpleadoAlias',
			type: 'POST',
			data: $(form).serialize(),
			success: function (response) {
				
				console.log('response >>> '+ response);
				//limpiar el formulario
				$('#formEmpleado')[0].reset();
				
				//limpiar las validaciones
				$('#formEmpleado').removeClass('was-validated');
				
				//enviar un mensaje de éxito al usuario en forma de div que dure 3 segundos
				$('#formEmpleado').prepend('<div class="alert alert-success" role="alert">'+ response.mensajeSalida +'</div>');
				setTimeout(function () {
					$('.alert').remove();
				}, 3000);
			},
			error: function (xhr, status, error) {
				// Manejar errores aquí
				console.error('Error al registrar :', error);
			}
		});
	});
</script>

</body>
</html>