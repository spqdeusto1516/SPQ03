package es.deusto.ingenieria.sd.client.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import es.deusto.ingenieria.sd.client.controller.EBController;


import javax.swing.JOptionPane;



public class loginVentana extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private static final long serialVersionUID = 1L;
	private static loginVentana INSTANCE;
	private EBController controller = null;
	
	
	public static loginVentana getInstance() {
		return INSTANCE;

	}
	
	public void dispose(){
		INSTANCE.setVisible(false);
	}
	


	/**
	 * Create the frame.
	 */
	
	public loginVentana(EBController controller) {
		this.controller = controller;
		initComponents();	
		
		this.setVisible(true);
		
	}
	
	
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		lblEmail.setBounds(63, 68, 75, 14);
		contentPane.add(lblEmail);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblContrasea.setBounds(63, 113, 92, 14);
		contentPane.add(lblContrasea);
		
		textField = new JTextField();
		textField.setBounds(176, 62, 205, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(176, 107, 205, 20);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
			System.out.println("entra");
			
				boolean exists = true;
				exists = controller.login(textField.getText(), passwordField.getText());
				
				
				if(exists){
					buscarVentana view = new buscarVentana(controller, textField.getText());
		            view.setVisible(true);
					
				}
				else{
                    JOptionPane.showMessageDialog(new Frame(), "Error de usuario");

				}
			}
		});
		
		
		
		
		btnAceptar.setBounds(176, 175, 89, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(292, 175, 89, 23);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
	}
	
	
	
	
	private static boolean login(String email, String contrasenia) {
		return loginVentana.getInstance().getController().login(email, contrasenia);
	}
	
	public EBController getController() {
		return controller;
	}
	public void setController(EBController controller) {
		this.controller = controller;
	}
	

	
	
	
	
}
