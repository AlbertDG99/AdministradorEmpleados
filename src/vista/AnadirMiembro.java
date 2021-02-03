package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.w3c.dom.ls.LSOutput;

import modelo.Miembro;
import modelo.Personal;

import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AnadirMiembro extends JFrame {

	private JPanel contentPane;
	private JTextField tNombre;
	private JTextField tApellido1;
	private JTextField tApellido2;
	ArrayList CASA;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnadirMiembro frame = new AnadirMiembro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AnadirMiembro() {
		setTitle("A\u00F1adir Miembro");
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 549, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// TITULO
		JLabel lblNewLabel = new JLabel("Formulario de inserci\u00F3n de nuevo miembro:");
		lblNewLabel.setBounds(20, 20, 262, 13);
		contentPane.add(lblNewLabel);
		// PANEL
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 43, 517, 185);
		contentPane.add(panel);
		// LBL NOMBRE
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(28, 44, 70, 13);
		panel.add(lblNombre);
		// CAMPO TEXTO NOMBRE
		tNombre = new JTextField();
		tNombre.setColumns(10);
		tNombre.setBounds(79, 41, 121, 19);
		panel.add(tNombre);
		// LBL APELLIDO 1
		JLabel lblApellido1 = new JLabel("Apellido 1:");
		lblApellido1.setBounds(215, 26, 70, 13);
		panel.add(lblApellido1);
		// LBL APELLIDO 2
		JLabel lblApellido2 = new JLabel("Apellido 2:");
		lblApellido2.setBounds(215, 65, 79, 13);
		panel.add(lblApellido2);
		// CAMPO DE TEXTO APELLIDO 1
		tApellido1 = new JTextField();
		tApellido1.setColumns(10);
		tApellido1.setBounds(280, 23, 157, 19);
		panel.add(tApellido1);
		// CAMPO DE TEXTO APELLIDO 2
		tApellido2 = new JTextField();
		tApellido2.setColumns(10);
		tApellido2.setBounds(280, 62, 157, 19);
		panel.add(tApellido2);
		// LBL EDAD
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(44, 126, 38, 13);
		panel.add(lblEdad);
		// SPINNER EDAD
		JSpinner spEdad = new JSpinner();
		spEdad.setModel(new SpinnerNumberModel(16, 16, 70, 1));
		spEdad.setBounds(79, 123, 45, 20);
		panel.add(spEdad);
		// LBL CARGO
		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setBounds(235, 126, 70, 21);
		panel.add(lblCargo);
		// COMBOBOX CARGO
		JComboBox cbCargo = new JComboBox();
		cbCargo.setModel(
				new DefaultComboBoxModel(new String[] { "Programador Junior", "Programador Senior", "Analista" }));
		cbCargo.setToolTipText("");
		cbCargo.setBounds(280, 126, 157, 21);
		panel.add(cbCargo);
			//BOTÓN INSERTAR
		JButton bInsertar = new JButton("Insertar Miembro");

		bInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Primero comprobamos que los campos esten correctamente completos, de otra
				// manera enviaremos un mensaje de error
				if (!tNombre.getText().toString().isEmpty() && !tApellido1.getText().toString().isEmpty()
						&& !tApellido2.getText().toString().isEmpty()) {

					// Creo un objeto persona con los elementos del formulario
					Miembro persona = new Miembro(0, tNombre.getText().toString(), tApellido1.getText().toString(),
							tApellido2.getText().toString(), (int) spEdad.getValue(), cbCargo.getSelectedIndex());

					// Inserto la persona en el arraylist de personas
					persona.insertarMiembro();
					JOptionPane.showMessageDialog(AnadirMiembro.this, "Miembro añadido correctamente.");
				} else {
					// Si existe un error muestras este mensaje
					JOptionPane.showMessageDialog(AnadirMiembro.this, "Debes completar todos los campos.",
							"Error de Inserción", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		bInsertar.setBounds(191, 241, 137, 21);
		contentPane.add(bInsertar);
	}

}
