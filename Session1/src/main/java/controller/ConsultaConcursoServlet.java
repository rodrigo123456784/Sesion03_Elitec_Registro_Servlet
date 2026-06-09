package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entidad.Concurso;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ConcursoModel;

@WebServlet("/consultaConcursoAlias")
public class ConsultaConcursoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1 Recibir el dato del formulario del JSP
		String nombre = req.getParameter("nombre");
		
		//2 Mostrar los datos recibidos en la consola del servidor
		System.out.println("Datos recibidos: " + nombre + " - ");
		
		//4 Crear un objeto AlumnoModel
		ConcursoModel model = new ConcursoModel();
		List<Concurso> lista = model.filtraConcurso(nombre);
		
		//5 Convertir la lista de alumnos a un formato JSON usando gson
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonSalida = gson.toJson(lista);
		System.out.println("Respuesta JSON: " + jsonSalida);
		
		//6 Enviar el JSON al cliente
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(jsonSalida);
		
	}

	
	
}