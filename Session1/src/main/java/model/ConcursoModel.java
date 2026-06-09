package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entidad.Alumno;
import entidad.Concurso;
import util.MySqlDBConexion;

public class ConcursoModel {

	public int registraConcurso(Concurso obj) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			//1 Crear la conexion a la BD
			cn = MySqlDBConexion.getConexion();
			
			//2 Crear el SQL de insercion
			String sql = "INSERT INTO concurso (nombre) VALUES (?,?,?,?)";
			
			//3 Crear el PreparedStatement
			ps = cn.prepareStatement(sql);
			ps.setString(1, obj.getNombre());
			ps.setDate(2, java.sql.Date.valueOf(obj.getFechaInicio()));
			ps.setDate(3, java.sql.Date.valueOf(obj.getFechaFin()));
			ps.setInt(4, obj.getEstado());
			
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
	
	
	public List<Concurso> filtraConcurso(String nombre) {
		
		List<Concurso> lista = new ArrayList<Concurso>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = MySqlDBConexion.getConexion();
			String sql = ""
					+ "SELECT idConcurso ,nombre ,fechaInicio,fechaFin,Estado"
					+ "From concurso "
					+ "WHERE "
					+ "nombre LIKE ? ";
			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + nombre + "%");

			System.out.println("SQL: " + ps);

			rs = ps.executeQuery();

			while (rs.next()) {
				Concurso a = new Concurso();
			a.setNombre(rs.getString("nombre"));
			a.setIdConcurso(rs.getInt("idConcurso"));
			a.setNombre(rs.getString("nombre"));
			a.setFechaInicio(rs.getDate("fechaInicio").toLocalDate());
			a.setFechaFin(rs.getDate("fechaFin").toLocalDate());
			a.setEstado(rs.getInt("estado"));
			
			
			
			
			lista.add(a);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return lista;
	}
	
}