package es.deusto.ingenieria.sd.client.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

import es.deusto.ingenieria.sd.client.controller.EBController;
//import es.deusto.ingenieria.sd.vueling.data.dto.VueloDTO;
import es.deusto.ingenieria.sd.server.DTO.*;
//import es.deusto.ingenieria.sd.vueling.data.dto.VueloDTO;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.JScrollPane;



public class buscarVentana extends JFrame {

	private JPanel contentPane;
	private JTextField tOrigen;
	private static final long serialVersionUID = 1L;
	private static buscarVentana INSTANCE;
	private EBController controller = null;
	private JTextField tFecha;
	private JTextField tDestino;
	private JTextField tNumPlazas;
	private JTable datos;
	private DefaultTableModel modelo = new DefaultTableModel();
	private String emailUsuario;
	List<DTOVuelo> exists = new ArrayList<>();
	
	
	public static buscarVentana getInstance() {
		return INSTANCE;

	}
	
	public void dispose(){
		INSTANCE.setVisible(false);
	}
	


	/**
	 * Create the frame.
	 */
	
	public buscarVentana(EBController controller, String email) {
		this.controller = controller;
		this.emailUsuario = email;
		initComponents();	
		
		this.setVisible(true);
		
	}
	
	
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lOrigen = new JLabel("Origen:");
		lOrigen.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		lOrigen.setBounds(10, 38, 75, 20);
		contentPane.add(lOrigen);
		
		JLabel lDestino = new JLabel("Destino:");
		lDestino.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		lDestino.setBounds(10, 68, 92, 20);
		contentPane.add(lDestino);
		
		JLabel lFecha = new JLabel("Fecha:");
		lFecha.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		lFecha.setBounds(10, 98, 92, 20);
		contentPane.add(lFecha);
		
		tOrigen = new JTextField();
		tOrigen.setBounds(82, 38, 108, 20);
		contentPane.add(tOrigen);
		tOrigen.setColumns(10);

		tDestino = new JTextField();
		tDestino.setColumns(10);
		tDestino.setBounds(82, 68, 108, 20);
		contentPane.add(tDestino);
		
		tFecha = new JTextField();
		tFecha.setColumns(10);
		tFecha.setBounds(82, 98, 108, 20);
		contentPane.add(tFecha);
		
		JLabel lNumPlazas = new JLabel("Plazas reserva:");
		lNumPlazas.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		lNumPlazas.setBounds(220, 68, 120, 20);
		contentPane.add(lNumPlazas);
		
		tNumPlazas = new JTextField();
		tNumPlazas.setColumns(10);
		tNumPlazas.setBounds(335, 68, 89, 23);
		contentPane.add(tNumPlazas);
		tNumPlazas.setText("1");
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
			System.out.println("entra");
			borrarTabla();
			List<DTOVuelo> exists = new ArrayList<>();
			
			exists = (List<DTOVuelo>)controller.buscarVuelo(tOrigen.getText(), tDestino.getText(), tFecha.getText());
	
			if(exists.size()!=0){
				for(int i = 0; i<exists.size();i++){
					cargarVueloEnTabla(exists.get(i));
				}
			}
			else{
                JOptionPane.showMessageDialog(new Frame(), "No hay ningun vuelo disponible");

			}
			}
		});
		
		
		
		
		btnBuscar.setBounds(335, 38, 89, 23);
		contentPane.add(btnBuscar);
		

		
		JLabel lblEasybooking = new JLabel("EasyBooking");
		lblEasybooking.setFont(new Font("Sitka Text", Font.BOLD, 18));
		lblEasybooking.setBounds(160, 8, 148, 23);
		contentPane.add(lblEasybooking);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 130, 414, 250);
		contentPane.add(scrollPane);
		
		datos = new JTable();
		datos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo","Origen", "Destino", "Fecha", "Hora", "Plazas"
			}
		));
		datos.setBackground(Color.WHITE);
		scrollPane.setViewportView(datos);
		
		JButton btnReservar = new JButton("Reservar");
		btnReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (datos.getSelectedRow() >= 0) { 
					System.out.println(datos.getValueAt(datos.getSelectedRow(), 0));
					int PlazasReserva=0;
					try {
						String c = (String)datos.getValueAt(datos.getSelectedRow(), 0);
						String o = (String)datos.getValueAt(datos.getSelectedRow(), 1);
						String d = (String)datos.getValueAt(datos.getSelectedRow(), 2);
						String f = (String)datos.getValueAt(datos.getSelectedRow(), 3);
						String h = (String)datos.getValueAt(datos.getSelectedRow(), 4);
						Integer p = (Integer)datos.getValueAt(datos.getSelectedRow(), 5);
						
						DTOVuelo v = new DTOVuelo(c, f, h, o, d, p);
						
					
						
						
						
						PlazasReserva=Integer.parseInt(tNumPlazas.getText());
						//int PlazasQuedan = controller.reservarVuelo((String)datos.getValueAt(datos.getSelectedRow(), 0), Integer.parseInt(tNumPlazas.getText()));
						
						controller.reservarVuelo(v, emailUsuario,Integer.parseInt(tNumPlazas.getText()));
						
						int PlazasVuelo = (int)datos.getValueAt(datos.getSelectedRow(), 5);
						modelo = (DefaultTableModel)datos.getModel();
						modelo.setValueAt(PlazasVuelo-PlazasReserva, datos.getSelectedRow(), 5);
						JOptionPane.showMessageDialog(new Frame(), "Reserva realizada con exito");
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(new Frame(), "Número plazas reserva incorrecto.");
						tNumPlazas.setText("1");
					}
				}
					else {
						JOptionPane.showMessageDialog(new Frame(), "Seleccione un vuelo");
					}
			}
		});
		btnReservar.setBounds(335, 98, 89, 23);
		contentPane.add(btnReservar);
		
	}
	
	private void cargarVueloEnTabla(DTOVuelo a){ // Carga los datos en la tabla
        modelo = (DefaultTableModel)datos.getModel();
        String c = a.getId_vuelo();
        String o = a.getOrigen();
        String d = a.getDestino();
        String f = a.getFecha();
        String h = a.getHora();
        int p = a.getPlazas();


        Object[] fila = {c,o,d,f,h,p};
        modelo.addRow(fila);                    
	}
	
	private void borrarTabla(){
		for (int i = 0; i < datos.getRowCount(); i++) {
	    	modelo.removeRow(i);
	        i-=1;
	       
		}
	}
	
	
	private static List<DTOVuelo> buscarVuelo(String origen, String destino, String fecha) {
		return buscarVentana.getInstance().getController().buscarVuelo(origen, destino, fecha);
	}
	
	public EBController getController() {
		return controller;
	}
	public void setController(EBController controller) {
		this.controller = controller;
	}
}
