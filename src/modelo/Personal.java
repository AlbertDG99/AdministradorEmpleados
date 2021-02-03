package modelo;

import java.awt.Container;
import java.sql.SQLException;
import java.util.ArrayList;

public class Personal {
	private ArrayList<Miembro> personal = new ArrayList<Miembro>(); // Arraylist estatico de miembros
	public static Personal instance = null;

	public Personal() {
	}

	/**
	 * Metodo singletone para el uso de la clase
	 * 
	 * @return el objeto
	 */
	public static Personal getInstance() {

		if (instance == null) {
			instance = new Personal();
		}
		return instance;
	}

	/**
	 * Metodo que obtiene el elemento buscado y el tipo de dato y devuelve un
	 * arraylist con las coincidencias de la base de datos
	 * 
	 * @param tipo atributo del miembro
	 * @param elem Texto a comparar
	 * @return Arraylist con las coincidencias
	 * @throws SQLException 
	 */
	public void busquedaPersonal(int tipo, String elem) throws SQLException {
		if (!elem.isEmpty()) {
			switch (tipo + 1) {
			case 1: // Nombre Completo
				personal=DAOMiembro.getInstance().obtenerEspecifico("concat(nombre,' ',apellido1,' ',apellido2)",elem);
				break;
			case 2: // Nombre
				personal=DAOMiembro.getInstance().obtenerEspecifico("nombre", elem);
				break;
			case 3: // Apellido1
				personal=DAOMiembro.getInstance().obtenerEspecifico("apellido1", elem);
				break;
			case 4: // Apellido2
				personal=DAOMiembro.getInstance().obtenerEspecifico("apellido2", elem);
				break;
			case 5:// Edad
				personal=DAOMiembro.getInstance().obtenerEspecifico("edad", elem);
				break;
			case 6: // Cargo
				personal=DAOMiembro.getInstance().obtenerEspecifico("cargo", elem);
				break;
			}
		} else //Si el campo está vacio devuelve todo el arraylist
			personal=DAOMiembro.getInstance().daoListar();
	}
	
	/**
	 * Metodo que llama al dao para obtener la lista completa de miembros
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Miembro> listarPersonal() throws SQLException{
		
		return DAOMiembro.getInstance().daoListar();
		
	}

	/**
	 * Setter del arraylist
	 * 
	 * @param personal
	 */
	public void setPersonal(ArrayList<Miembro> personal) {
		this.personal = personal;
	}

	/**
	 * Getter del arraylist
	 * 
	 * @return
	 */
	public ArrayList<Miembro> getPersonal() {
		return personal;
	}

}
