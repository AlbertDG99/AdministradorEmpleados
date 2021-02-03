package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase encargada de administrar todos los elementos pertinentes de la base de datos relacionada con el personal
 * @author Alberto
 *
 */
public class DAOMiembro {

	private java.sql.Connection con =null;
	public static DAOMiembro instance =null;
	
	public DAOMiembro() throws SQLException{
		
		con = BdConnection.getConnection();
		
		}
	/**
	 * Metodo Singletone para usar los metodos del DAO sin tener que crear un objeto.
	 * @return
	 * @throws SQLException
	 */
	public static DAOMiembro getInstance() throws SQLException {
		
		if(instance== null) {
			instance=new DAOMiembro();
		}
		return instance;
	}
	
	/*Metodo que se comunica con MySql para insertar el objeto*/
	public void daoInsertar(Miembro m) throws SQLException {
		
		PreparedStatement ps= con.prepareCall("INSERT INTO miembros (nombre, apellido1, apellido2, edad, cargo)VALUES(?,?,?,?,?)");
		ps.setString(1,m.getNombre());
		ps.setString(2,m.getApellido1());
		ps.setString(3,m.getApellido2());
		ps.setInt(4,m.getEdad());
		ps.setInt(5,m.getCargo());
		ps.executeUpdate();
		ps.close();
	}
	/**
	 * Metodo que obtiene todos los miembros de la base de datos y los guarda en un ArrayList
	 * @return ArrayList de los miembros
	 * @throws SQLException
	 */
	public ArrayList<Miembro> daoListar() throws SQLException {
		
			PreparedStatement ps = con.prepareStatement("SELECT * from miembros");
			ResultSet rs = ps.executeQuery();
		ArrayList<Miembro> result = new ArrayList<Miembro>();
		
		while(rs.next()) { //Convertimos el resulset en un arraylist
			result.add(new Miembro(rs.getInt("id"),rs.getString("nombre"),rs.getString("apellido1"),rs.getString("apellido2"),rs.getInt("edad"),rs.getInt("cargo")));
			
		}
		
		return result;
	}
/**
 * Metodo para buscar empleados especificos 
 * @param campo Atributo por el que se buscará al empleado
 * @param busqueda Texto introducido en la busqueda de dicho campo
 * @return Retorna un Arraylist de los miembros que coinciden
 * @throws SQLException
 */
	public ArrayList<Miembro> obtenerEspecifico(String campo, String busqueda) throws SQLException {
		ArrayList<Miembro> result = new ArrayList<Miembro>();
		PreparedStatement ps = con.prepareStatement("select * from miembros WHERE "+campo+" like ?");
		ps.setString(1,("%"+busqueda+"%"));
		ResultSet rs = ps.executeQuery();
		while(rs.next()){ //Convertimos el resulset en un arraylist
			result.add(new Miembro(rs.getInt("id"),rs.getString("nombre"),rs.getString("apellido1"),rs.getString("apellido2"),rs.getInt("edad"),rs.getInt("cargo")));
		}
		return result;
	}
/**
 * Metodo que actualiza un miembro mediante ID
 * @param m Miembro a actualizar
 * @throws SQLException
 */
	public void daoActualizar(Miembro m) throws SQLException {
		PreparedStatement ps= con.prepareCall("UPDATE miembros SET nombre=?, apellido1=?, apellido2=?, edad=?, cargo=? WHERE id=?");
		ps.setString(1,m.getNombre());
		ps.setString(2,m.getApellido1());
		ps.setString(3,m.getApellido2());
		ps.setInt(4,m.getEdad());
		ps.setInt(5,m.getCargo());
		ps.setInt(6,m.getId());	
		ps.executeUpdate();
		ps.close();
	}
	
	/**
	 * Metodo que elimina un miembro mediante ID
	 * @param id ID del miembro a borrar
	 * @throws SQLException
	 */
	public void daoEliminar(int id) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement("DELETE FROM miembros WHERE id=?");
		ps.setInt(1, id);	
		ps.executeUpdate();
		ps.close();
		
	}
}