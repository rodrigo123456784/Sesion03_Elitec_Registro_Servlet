package entidad;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Empleados {

	private int idEmpleados;
	private String nombre;
	private String apellido;
	private String area;
	private LocalDate fechaNacimiento;
	private LocalDate fechaIngreso;
	private String correo;
}