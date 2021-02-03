package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.jdi.VMCannotBeModifiedException;

import modelo.Miembro;
import modelo.Personal;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JToggleButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ModificarMiembro extends JFrame {

	private JPanel contentPane;
	private JTextField tNombre;
	private JTextField tApellido1;
	private JTextField tApellido2;
	private JTextField tBusqueda;
	private JSpinner spEdad;
	private JComboBox cbCargo;
	private JLabel lblEdadMedia;
	private JLabel lblMiembros;
	private int idBuscador;
	private int indexArr=0; //Index para iterar en el arraylist auxiliar
	private Personal personal = new Personal(); //Arraylist de miembros auxiliar para tratar en la aplicación

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarMiembro frame = new ModificarMiembro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ModificarMiembro() throws SQLException {

		setTitle("Administrar Miembros");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 555, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblBuscador = new JLabel("Buscador:");
		lblBuscador.setBounds(20, 43, 97, 13);
		contentPane.add(lblBuscador);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 67, 517, 202);
		contentPane.add(panel);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(26, 73, 70, 13);
		panel.add(lblNombre);

		tNombre = new JTextField();
		tNombre.setEnabled(false);
		tNombre.setColumns(10);
		tNombre.setBounds(77, 70, 121, 19);
		panel.add(tNombre);

		JLabel lblApellido1 = new JLabel("Apellido 1:");
		lblApellido1.setBounds(213, 55, 70, 13);
		panel.add(lblApellido1);

		JLabel lblApellido2 = new JLabel("Apellido 2:");
		lblApellido2.setBounds(213, 94, 79, 13);
		panel.add(lblApellido2);

		tApellido1 = new JTextField();
		tApellido1.setEnabled(false);
		tApellido1.setColumns(10);
		tApellido1.setBounds(278, 52, 157, 19);
		panel.add(tApellido1);

		tApellido2 = new JTextField();
		tApellido2.setEnabled(false);
		tApellido2.setColumns(10);
		tApellido2.setBounds(278, 91, 157, 19);
		panel.add(tApellido2);

		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(42, 155, 38, 13);
		panel.add(lblEdad);

		spEdad = new JSpinner();
		spEdad.setEnabled(false);
		spEdad.setBounds(77, 152, 45, 20);
		panel.add(spEdad);

		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setBounds(233, 155, 70, 21);
		panel.add(lblCargo);

		cbCargo = new JComboBox();
		cbCargo.setModel(new DefaultComboBoxModel(new String[] { "Programador Junior", "Programador Senior", "Analista" }));
		cbCargo.setEnabled(false);
		cbCargo.setToolTipText("");
		cbCargo.setBounds(278, 155, 157, 21);
		panel.add(cbCargo);

		JButton btnModificarMiembro = new JButton("Modificar Miembro");
		btnModificarMiembro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//Mostrará un mensaje de confirmación y si es afirmativa, cambiará el elemento en la base de datos, por lo que se aplicará a todo el programa
				int n = JOptionPane.showConfirmDialog(ModificarMiembro.this,
						"Estás seguro que quieres modificar a esta persona??", "Confirmación Modificar",
						JOptionPane.YES_NO_OPTION);

				if (n == 0) { //Si es YES
					//Si los campos están completos
					if (!tNombre.getText().toString().isEmpty() && !tApellido1.getText().toString().isEmpty()
							&& !tApellido2.getText().toString().isEmpty()) {
						//Crea un objeto persona con los campos
						Miembro persona = new Miembro(idBuscador , tNombre.getText().toString(),
								tApellido1.getText().toString(), tApellido2.getText().toString(),
								(int) spEdad.getValue(), cbCargo.getSelectedIndex());
						//Modifica el objeto en la posición del ID del que estamos buscando.
						persona.modificarMiembro();
						//Muestra un mensaje de exito
						JOptionPane.showMessageDialog(ModificarMiembro.this, "Miembro modificado con exito.");
					} else {
						JOptionPane.showMessageDialog(ModificarMiembro.this, "Debes completar todos los campos.",
								"Error de Inserción", JOptionPane.ERROR_MESSAGE);
					}
				}
				//Muestra los miembros totales y su media de edad en un label
				try {
					actuMiembrosTotales();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnModificarMiembro.setEnabled(false);
		btnModificarMiembro.setBounds(20, 366, 160, 21);
		contentPane.add(btnModificarMiembro);
			//BOTON ELIMINAR
		JButton bDelete = new JButton("Eliminar Miembro");
		bDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//Te pregunta para confirmar si deseas eliminar o no.
				int n = JOptionPane.showConfirmDialog(ModificarMiembro.this,
						"Estás seguro que quieres borrar a esta persona??", "Confirmación Borrar",
						JOptionPane.YES_NO_OPTION);

				if (n == 0) { //En caso afirmativo, borra el elemento con el ID indicado
					
					Miembro.getInstance().eliminarMiembro(idBuscador);
					personal.getPersonal().remove(indexArr);
					rellenarCamposBusqueda(); //Muestra el anterior
					JOptionPane.showMessageDialog(ModificarMiembro.this, "Miembro eliminado con exito.");
				}
				try {
					actuMiembrosTotales();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //Vuelve a actualizar el contador de miembros
			}

		});
		bDelete.setEnabled(false);
		bDelete.setBounds(371, 366, 137, 21);
		contentPane.add(bDelete);

		
		//Botón que activa o desactiva las funcionalidades de modificacion
		JToggleButton bEdicion = new JToggleButton("Habilitar Edici\u00F3n");
		bEdicion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tNombre.isEnabled()) {
					tNombre.setEnabled(false);
					tApellido1.setEnabled(false);
					tApellido2.setEnabled(false);
					spEdad.setEnabled(false);
					cbCargo.setEnabled(false);
					btnModificarMiembro.setEnabled(false);
					bDelete.setEnabled(false);
				} else {
					tNombre.setEnabled(true);
					tApellido1.setEnabled(true);
					tApellido2.setEnabled(true);
					spEdad.setEnabled(true);
					cbCargo.setEnabled(true);
					btnModificarMiembro.setEnabled(true);
					bDelete.setEnabled(true);
				}
			}
		});
		bEdicion.setBounds(185, 10, 136, 21);
		panel.add(bEdicion);

		tBusqueda = new JTextField();
		tBusqueda.setBounds(224, 40, 172, 19);
		contentPane.add(tBusqueda);
		tBusqueda.setColumns(10);
			//COMBOBOX ELEM BUSQUEDA
		JComboBox cbBuscar = new JComboBox();
		cbBuscar.setModel(new DefaultComboBoxModel(new String[] { "Programador Junior", "Programador Senior", "Analista" }));
		cbBuscar.setBounds(224, 39, 172, 21);
		cbBuscar.setVisible(false);
		contentPane.add(cbBuscar);
			//COMBOBOX CARGO A BUSCAR
		JComboBox cBBusqueda = new JComboBox();
		cBBusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Si buscamos por cargo aparecerá un combobox, de otra manera será un textbox
				if (cBBusqueda.getSelectedItem().equals("Cargo")) {
					tBusqueda.setVisible(false);
					cbBuscar.setVisible(true);
				}else {
					tBusqueda.setVisible(true);
					cbBuscar.setVisible(false);
				}
			}
		});
		cBBusqueda.setModel(new DefaultComboBoxModel(new String[] { "Nombre Completo", "Nombre", "Primer Apellido", "Segundo Apellido", "Edad", "Cargo" }));
		cBBusqueda.setBounds(80, 39, 134, 21);
		contentPane.add(cBBusqueda);
			//BOTON DE BUSQUEDA
		JButton bBusqueda = new JButton("Buscar");
		bBusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Si estás en modo "Cargo" obtendrá el elemento del CB y de otra manera, del TB
				if(cbBuscar.isVisible()) {
					try {
						personal.busquedaPersonal(cBBusqueda.getSelectedIndex(),
								Integer.toString(cbBuscar.getSelectedIndex()));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					try {
						personal.busquedaPersonal(cBBusqueda.getSelectedIndex(),
								tBusqueda.getText().toString());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//Si hay resultados de busqueda muestra el primero
				if (!personal.getPersonal().isEmpty()) {
					rellenarCamposBusqueda();
				} else {//De otra manera muestra un mensaje de error
					JOptionPane.showMessageDialog(ModificarMiembro.this, "No hay coincidencias con su busqueda.");
					rellenarCamposBusqueda();
				}
				try {
					actuMiembrosTotales();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		bBusqueda.setBounds(423, 39, 85, 21);
		contentPane.add(bBusqueda);

		//Resta 1 al buscador
		JButton bAnterior = new JButton("Anterior");
		bAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				if (indexArr > 0) {
					indexArr--;
					rellenarCamposBusqueda();
				} else {
					JOptionPane.showMessageDialog(ModificarMiembro.this, "No hay miembros más atrás.");
				}
			}
		});
		bAnterior.setBounds(155, 279, 97, 21);
		contentPane.add(bAnterior);

		//Suma 1 al buscador
		JButton bSiguiente = new JButton("Siguiente");
		bSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (indexArr < personal.getPersonal().size() - 1) {
					indexArr++;
					rellenarCamposBusqueda();
				} else {
					JOptionPane.showMessageDialog(ModificarMiembro.this, "No hay miembros adelante.");
				}
			}
		});
		bSiguiente.setBounds(262, 279, 97, 21);
		contentPane.add(bSiguiente);

		JLabel lblNewLabel = new JLabel("Dejalo en blanco para mostrar todos los Miembros");
		lblNewLabel.setBounds(187, 16, 295, 13);
		contentPane.add(lblNewLabel);

		lblMiembros = new JLabel("Miembros totales: ");
		lblMiembros.setBounds(20, 327, 124, 13);
		contentPane.add(lblMiembros);

		lblEdadMedia = new JLabel("Edad media: ");
		lblEdadMedia.setBounds(20, 343, 124, 13);
		contentPane.add(lblEdadMedia);


		actuMiembrosTotales(); //Muesta los miembros totales y su edad media
	}
/**
 * Función que rellena los campos del formulario con los objetos del arraylist auxilair
 */
	public void rellenarCamposBusqueda() {
		if (!personal.getPersonal().isEmpty()) {
			idBuscador=(personal.getPersonal().get(indexArr).getId());
			tNombre.setText(personal.getPersonal().get(indexArr).getNombre());
			tApellido1.setText(personal.getPersonal().get(indexArr).getApellido1());
			tApellido2.setText(personal.getPersonal().get(indexArr).getApellido2());
			spEdad.setValue(personal.getPersonal().get(indexArr).getEdad());
			cbCargo.setSelectedIndex(personal.getPersonal().get(indexArr).getCargo());
		} else {
			tNombre.setText("");
			tApellido1.setText("");
			tApellido2.setText("");
			spEdad.setValue(0);
			cbCargo.setSelectedIndex(0);

		}
	}

	/**
	 * Metodo que muestra la edad media y los miembros totales mediante la abse de datos
	 * @throws SQLException 
	 */
	public void actuMiembrosTotales() throws SQLException {

		float edad = 0;
		ArrayList<Miembro> total = Personal.getInstance().listarPersonal();

		for (Miembro m : total) {
			edad += m.getEdad();
		}
		edad = edad / total.size();
		lblEdadMedia.setText("Edad Media: " + edad);
		lblMiembros.setText("Miembros Totales: " + total.size());
	}
}
