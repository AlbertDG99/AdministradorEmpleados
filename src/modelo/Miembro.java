package modelo;

import java.sql.SQLException;


public class Miembro {



	private int id; //ID del miembro
	private String nombre; //Nombre del miembro
	private String apellido1; //Primer apellido del miembro
	private String apellido2; //Segundo apellido del miembro
	private int edad; //Edad del miembro
	private int cargo; //Cargo del miembro
	public static Miembro instance = null;
	
	/**
	 * Constructor para miembro
	 * @param id
	 * @param nombre
	 * @param apellido1
	 * @param apellido2
	 * @param edad
	 * @param cargo
	 */
	public Miembro(int id,String nombre, String apellido1, String apellido2, int edad, int cargo) {
		super();
		this.id=id;
		this.nombre = nombre;
		this.edad = edad;
		this.cargo = cargo;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
	}
	
	public Miembro() {
		// TODO Auto-generated constructor stub
	}

	public static Miembro getInstance() {

		if (instance == null) {
			instance = new Miembro();
		}
		return instance;
	}
	
	//Getter and Setters de todas 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getCargo() {
		return cargo;
	}

	public void setCargo(int cargo) {
		this.cargo = cargo;
	}
	/**
	 * Metodo que llama al dao para insertar este miembro
	 */
	public void insertarMiembro() {
		try {
			DAOMiembro.getInstance().daoInsertar(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Metodo que llama al dao para modificar este miembro
	 */
	public void modificarMiembro() {
		try {
			DAOMiembro.getInstance().daoActualizar(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Metodo que llama al dao para eliminar el miembro con dicha ID
	 * @param id ID a eliminar
	 */
	public void eliminarMiembro(int id) {
		try {
			DAOMiembro.getInstance().daoEliminar(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
