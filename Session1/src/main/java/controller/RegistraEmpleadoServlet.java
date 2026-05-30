package controller;

import java.io.IOException;

import entidad.Empleados;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.EmpleadosModel;

@WebServlet("/RegistraEmpleadoAlias")
public class RegistraEmpleadoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1 Recibir los datos del formulario del JSP
		String nombre = req.getParameter("nombre");
		String apellido = req.getParameter("apellido");
		String area = req.getParameter("area");
		String fechaNacimiento = req.getParameter("fecNaci");
		String fechaIngreso = req.getParameter("fecIng");
		String correo = req.getParameter("correo");

		System.out.println("Datos recibidos: " + nombre + " - " + apellido + " - " + area + " - " + fechaNacimiento +" - "  + fechaIngreso +" - " + correo);
		    

		// 2 Crear un objeto Concurso
		Empleados Empleado= new Empleados();
		Empleado.setNombre(nombre);
		Empleado.setApellido(apellido);
		Empleado.setArea(area);
		Empleado.setFechaNacimiento(java.time.LocalDate.parse(fechaNacimiento));
		Empleado.setFechaIngreso(java.time.LocalDate.parse(fechaIngreso));
		Empleado.setCorreo(correo);
		

		// 3 Crear un objeto CocnursoModel
		EmpleadosModel model = new EmpleadosModel();
		int salida = model.idEmpleados(Empleado);

		String mensajeSalida = (salida > 0) ? "informacion del empleado correctamente (OK)" : "Error al informacion del empleado";

		
		// 4 Enviar una respuesta al cliente en JSON al jquery
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write("{\"mensajeSalida\":\"" + mensajeSalida + "\"}");
		
	}

}