package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import modelo.Miembro;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class MenuPrincipal {

	
	private JFrame frmGestorDePersonal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal window = new MenuPrincipal();
					window.frmGestorDePersonal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGestorDePersonal = new JFrame();
		frmGestorDePersonal.setResizable(false);
		frmGestorDePersonal.setTitle("Gestor de Personal");
		frmGestorDePersonal.setBounds(100, 100, 534, 218);
		frmGestorDePersonal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGestorDePersonal.getContentPane().setLayout(null);
		//BOTON AÑADIR MIEMBRO
		JButton bAnnadir = new JButton("A\u00F1adir Miembro"); //Botón que lleva a la ventana para añadir miembros
		bAnnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				AnadirMiembro annadir = new AnadirMiembro();
				annadir.setVisible(true);
			}
		});
		bAnnadir.setFont(new Font("Verdana", Font.BOLD, 15));
		bAnnadir.setBounds(30, 53, 200, 92);
		bAnnadir.setForeground(new Color(0, 0, 0));
		bAnnadir.setBackground(new Color(216, 191, 216));
		frmGestorDePersonal.getContentPane().add(bAnnadir);
		//BOTON MODIFICAR MIEMBRO
		JButton bModificar = new JButton("Administrar Miembros"); //Botón que lleva a la ventana para ver y modificar los miembros
		bModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ModificarMiembro modif = null;
				try {
					modif = new ModificarMiembro();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				modif.setVisible(true);
			}
		});
		bModificar.setFont(new Font("Verdana", Font.BOLD, 15));
		bModificar.setBounds(240, 53, 257, 92);
		bModificar.setForeground(new Color(0, 0, 0));
		bModificar.setBackground(new Color(233, 150, 122));
		frmGestorDePersonal.getContentPane().add(bModificar);
	}
}
