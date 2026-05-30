package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entidad.Empleados;
import util.MySqlDBConexion;

public class EmpleadosModel {

	public int idEmpleados(Empleados obj) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			//1 Crear la conexion a la BD
			cn = MySqlDBConexion.getConexion();
			
			//2 Crear el SQL de insercion
			String sql = "INSERT INTO empleados (nombre,apellido,area, fechaNacimiento, fechaIngreso, correo) VALUES (?,?,?,?,?,?)";
			
			//3 Crear el PreparedStatement
			ps = cn.prepareStatement(sql);
			ps.setString(1, obj.getNombre());
			ps.setString(2, obj.getApellido());
			ps.setString(3, obj.getArea());
			ps.setDate(4, java.sql.Date.valueOf(obj.getFechaNacimiento()));
			ps.setDate(5, java.sql.Date.valueOf(obj.getFechaIngreso()));
			ps.setString(6, obj.getCorreo());
			
			System.out.println("SQL: " + ps);
			
			//4 Ejecutar el SQL	
			salida = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}	
		
		return salida;
	}
	
}