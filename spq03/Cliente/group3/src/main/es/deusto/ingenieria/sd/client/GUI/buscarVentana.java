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
//import es.deusto.ingenieria.sd.vueling.data.dto.JuegoDTO;
import es.deusto.ingenieria.sd.server.DTO.*;
//import es.deusto.ingenieria.sd.vueling.data.dto.JuegoDTO;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.JScrollPane;



public class buscarVentana extends JFrame {

	private JPanel contentPane;
	private JTextField tConsola;
	private static final long serialVersionUID = 1L;
	private static buscarVentana INSTANCE;
	private EBController controller = null;
	private JTextField tFecha;
	private JTextField tNumUnidades;
	private JTable datos;
	private DefaultTableModel modelo = new DefaultTableModel();
	private String emailUsuario;
	List<DTOJuego> exists = new ArrayList<>();
	
	
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
		
		JLabel lConsola = new JLabel("Consola:");
		lConsola.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		lConsola.setBounds(10, 38, 75, 20);
		contentPane.add(lConsola);
		
		JLabel lFecha = new JLabel("Fecha:");
		lFecha.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		lFecha.setBounds(10, 98, 92, 20);
		contentPane.add(lFecha);
		
		tConsola = new JTextField();
		tConsola.setBounds(82, 38, 108, 20);
		contentPane.add(tConsola);
		tConsola.setColumns(10);
		
		tFecha = new JTextField();
		tFecha.setColumns(10);
		tFecha.setBounds(82, 98, 108, 20);
		contentPane.add(tFecha);
		
		JLabel lNumUnidades = new JLabel("Unidades reserva:");
		lNumUnidades.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		lNumUnidades.setBounds(220, 68, 120, 20);
		contentPane.add(lNumUnidades);
		
		tNumUnidades = new JTextField();
		tNumUnidades.setColumns(10);
		tNumUnidades.setBounds(335, 68, 89, 23);
		contentPane.add(tNumUnidades);
		tNumUnidades.setText("1");
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
			System.out.println("entra");
			borrarTabla();
			List<DTOJuego> exists = new ArrayList<>();
			
			exists = (List<DTOJuego>)controller.buscarJuego(tConsola.getText(), tFecha.getText());
	
			if(exists.size()!=0){
				for(int i = 0; i<exists.size();i++){
					cargarJuegoEnTabla(exists.get(i));
				}
			}
			else{
                JOptionPane.showMessageDialog(new Frame(), "No hay ningun Juego disponible");

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
				"Codigo","Consola", "Fecha", "Hora", "Unidades"
			}
		));
		datos.setBackground(Color.WHITE);
		scrollPane.setViewportView(datos);
		
		JButton btnReservar = new JButton("Reservar");
		btnReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (datos.getSelectedRow() >= 0) { 
					System.out.println(datos.getValueAt(datos.getSelectedRow(), 0));
					int UnidadesReserva=0;
					try {
						String c = (String)datos.getValueAt(datos.getSelectedRow(), 0);
						String o = (String)datos.getValueAt(datos.getSelectedRow(), 1);
						String d = (String)datos.getValueAt(datos.getSelectedRow(), 2);
						String f = (String)datos.getValueAt(datos.getSelectedRow(), 3);
						String h = (String)datos.getValueAt(datos.getSelectedRow(), 4);
						Integer p = (Integer)datos.getValueAt(datos.getSelectedRow(), 5);
						
						DTOJuego v = new DTOJuego(c, f, h, o, d, p);
						
					
						
						
						
						UnidadesReserva=Integer.parseInt(tNumUnidades.getText());
						//int UnidadesQuedan = controller.reservarJuego((String)datos.getValueAt(datos.getSelectedRow(), 0), Integer.parseInt(tNumUnidades.getText()));
						
						controller.reservarJuego(v, emailUsuario,Integer.parseInt(tNumUnidades.getText()));
						
						int UnidadesJuego = (int)datos.getValueAt(datos.getSelectedRow(), 5);
						modelo = (DefaultTableModel)datos.getModel();
						modelo.setValueAt(UnidadesJuego-UnidadesReserva, datos.getSelectedRow(), 5);
						JOptionPane.showMessageDialog(new Frame(), "Reserva realizada con exito");
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(new Frame(), "N�mero Unidades reserva incorrecto.");
						tNumUnidades.setText("1");
					}
				}
					else {
						JOptionPane.showMessageDialog(new Frame(), "Seleccione un Juego");
					}
			}
		});
		btnReservar.setBounds(335, 98, 89, 23);
		contentPane.add(btnReservar);
		
	}
	
	private void cargarJuegoEnTabla(DTOJuego a){ // Carga los datos en la tabla
        modelo = (DefaultTableModel)datos.getModel();
        String c = a.getId_Juego();
        String o = a.getConsola();
        String f = a.getFecha();
        String h = a.getHora();
        int p = a.getUnidades();


        Object[] fila = {c,o,d,f,h,p};
        modelo.addRow(fila);                    
	}
	
	private void borrarTabla(){
		for (int i = 0; i < datos.getRowCount(); i++) {
	    	modelo.removeRow(i);
	        i-=1;
	       
		}
	}
	
	
	private static List<DTOJuego> buscarJuego(String Consola, String fecha) {
		return buscarVentana.getInstance().getController().buscarJuego(Consola, fecha);
	}
	
	public EBController getController() {
		return controller;
	}
	public void setController(EBController controller) {
		this.controller = controller;
	}
}
