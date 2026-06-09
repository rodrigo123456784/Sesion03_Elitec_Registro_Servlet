<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/bootstrap.bundle.js" type="text/javascript"></script>
<script src="js/bootstrap.esm.js" type="text/javascript"></script>
<script src="js/jquery-4.0.0.min.js" type="text/javascript"></script>
<script src="js/datatables.js" type="text/javascript"></script>

<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap-grid.css" rel="stylesheet">
<link href="css/bootstrap-reboot.css" rel="stylesheet">
<link href="css/bootstrap-utilities.css" rel="stylesheet">
<link href="css/datatables.css" rel="stylesheet">

</head>
<body>

<div class="container">
    <h1>Consulta de Concurso</h1>
    <div class="row" style="margin-top: 4%;">
    	<div class="col-4">
    		<label for="nombre">Nombre</label> 
			<input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingrese el nombre" maxlength="30" >
    	</div>	
    </div>
    <div class="row justify-content-center" style="margin-top: 2%">
		<button class="btn btn-primary" id="btnFiltrar"style="width: 200px">Filtrar</button>
	</div>
	<div class="row" style="margin-top: 4%;">
		<div class="col-12">
			<table class="table table-striped" id="id_table">
				<thead>
					<tr>
						<th>Nombre</th>			
					</tr>
				</thead>
			</table>
		</div>
	</div>    
</div>

<script type="text/javascript">
	$('#id_table').DataTable();

	$("#btnFiltrar").click(function (e) {
		console.log("click en filtrar");
		e.preventDefault(); //Evita que el formulario se envíe automáticamente
		
		let nombre = $('#nombre').val();
		console.log("nombre: " + nombre);
		//enviar los datos al controlador
		$.ajax({
			url: 'consultaConcursoAlias',
			type: 'GET',
			data: {
				nombre: nombre
			},
			success: function (response) {
				console.log(response);
				agregarGrilla(response);
			}
		});
	});	
	
	function agregarGrilla(lista){
		 $('#id_table').DataTable().clear();
		 $('#id_table').DataTable().destroy();
		 $('#id_table').DataTable({
				data: lista,
				language: IDIOMA,
				searching: true,
				ordering: true,
				processing: true,
				pageLength: 10,
				lengthChange: false,
				info:true,
				scrollY: 305,
		        scroller: {
		            loadingIndicator: true
		        },
				columns:[
			
					{data: "nombre",className:'text-center'},
					
				]                                     
		    });
	}
	
	var IDIOMA = {
			processing:"procesando...",
		    lengthMenu: "_MENU_ Registros por p&aacute;gina",
		    zeroRecords: "No existen registros",
		    info: "P&aacute;gina _PAGE_ de _PAGES_",
		    infoEmpty: "Sin registros",
		    infoFiltered: "(Filtro de _MAX_ registros)",
		    search: "Buscar:",
		    paginate: {
		        "first":      "Primero",
		        "last":       "Last",
		        "next":       "Siguiente",
		        "previous":   "Anterior"
		    }
		};
</script>

</body>
</html>