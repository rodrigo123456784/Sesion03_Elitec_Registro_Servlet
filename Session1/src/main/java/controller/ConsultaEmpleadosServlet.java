package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entidad.Empleados;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.EmpleadosModel;

@WebServlet("/consultaEmpleadosAlias")
public class ConsultaEmpleadosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1 
		String nombre = req.getParameter("nombre");
		String apellido = req.getParameter("apellido");
		String area = req.getParameter("area");
	
		
		//2 
		System.out.println("Datos recibidos: " + nombre + " - " + apellido + " - " + area + " - ");
		
		
		
		
		//3
		EmpleadosModel model = new EmpleadosModel();
		List<Empleados> lista = model.filtraEmpleados(nombre,apellido,area);
		
		//4 
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonSalida = gson.toJson(lista);
		System.out.println("Respuesta JSON: " + jsonSalida);
		
		//5
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(jsonSalida);
		
	}

	
}