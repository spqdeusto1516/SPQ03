package es.deusto.client;

import javax.swing.*;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import es.deusto.server.db.data.Product;
import es.deusto.server.db.data.User;

public class MainWindow extends JFrame implements ActionListener{
	private ArrayList<String> prueba = new ArrayList<>();
	
	private JTextField txfMoneyTo;
	private JTextField txfAmount;
	private JTextField txtMoneyNow;
	private JTextField txfProdName;
	private JTable table;
	private JTextField txfLogin;
	private JPasswordField txfPassword;
	
	private JButton btnBuyProduct;
	private JButton btnSend;
	private JButton btnInsertNewProduct;
	private JButton btnSearch;
	private JButton btnAllUsers;

	private JButton btnLogIn;
	
	private JPanel panel_login;
	private JPanel panelLogged;
	
	
	public MainWindow() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().setLayout(null);
		
		panelLogged = new JPanel();
		panelLogged.setBounds(0, 0, 444, 271);
		getContentPane().add(panelLogged);
		panelLogged.setLayout(null);
		
		JLabel lblSendMoneyTo = new JLabel("Send money to:  ");
		lblSendMoneyTo.setBounds(10, 14, 106, 14);
		panelLogged.add(lblSendMoneyTo);
		
		txfMoneyTo = new JTextField();
		txfMoneyTo.setBounds(114, 11, 86, 20);
		panelLogged.add(txfMoneyTo);
		txfMoneyTo.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount: ");
		lblAmount.setBounds(10, 39, 63, 14);
		panelLogged.add(lblAmount);
		
		txfAmount = new JTextField();
		txfAmount.setBounds(114, 36, 86, 20);
		panelLogged.add(txfAmount);
		txfAmount.setColumns(10);
		
		btnSend = new JButton("Send");
		btnSend.setBounds(210, 35, 97, 23);
		panelLogged.add(btnSend);
		
		JLabel lblMoneyRightNow = new JLabel("Money right now: ");
		lblMoneyRightNow.setBounds(314, 13, 100, 17);
		panelLogged.add(lblMoneyRightNow);
		
		txtMoneyNow = new JTextField();
		txtMoneyNow.setBounds(317, 36, 97, 20);
		txtMoneyNow.setEditable(false);
		panelLogged.add(txtMoneyNow);
		txtMoneyNow.setColumns(10);
		
		JLabel lblSearchProduct = new JLabel("Search Product");
		lblSearchProduct.setBounds(10, 84, 95, 14);
		panelLogged.add(lblSearchProduct);
		
		txfProdName = new JTextField();
		txfProdName.setBounds(114, 81, 86, 20);
		txfProdName.setText("Product Name");
		panelLogged.add(txfProdName);
		txfProdName.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 109, 380, 77);
		panelLogged.add(scrollPane);
		
		table = new JTable(30, 1);
		table.getColumnModel().getColumn(0).setHeaderValue("Name");
		scrollPane.setViewportView(table);
		
		btnBuyProduct = new JButton("Buy Product");
		btnBuyProduct.setEnabled(false);
		btnBuyProduct.setBounds(295, 200, 122, 23);
		panelLogged.add(btnBuyProduct);
		
		btnInsertNewProduct = new JButton("Insert New Product");
		btnInsertNewProduct.setBounds(41, 230, 159, 23);
		panelLogged.add(btnInsertNewProduct);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(218, 80, 79, 23);
		panelLogged.add(btnSearch);
		
		btnAllUsers = new JButton("All Users");
		btnAllUsers.setEnabled(false);
		btnAllUsers.setBounds(320, 80, 97, 23);
		panelLogged.add(btnAllUsers);
		panelLogged.setVisible(false);
		
		panel_login = new JPanel();
		panel_login.setBounds(0, 0, 444, 271);
		getContentPane().add(panel_login);
		panel_login.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(60, 84, 99, 20);
		panel_login.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(60, 123, 99, 20);
		panel_login.add(lblPassword);
		
		txfLogin = new JTextField();
		txfLogin.setBounds(188, 84, 163, 20);
		panel_login.add(txfLogin);
		txfLogin.setColumns(10);
		
		txfPassword = new JPasswordField();
		txfPassword.setBounds(189, 122, 164, 21);
		panel_login.add(txfPassword);
		txfPassword.setColumns(10);
		
		btnLogIn = new JButton("Log in");
		btnLogIn.setBounds(292, 194, 99, 23);
		panel_login.add(btnLogIn);
		panel_login.setVisible(true);
		
		btnAllUsers.addActionListener(this);
		btnBuyProduct.addActionListener(this);
		btnInsertNewProduct.addActionListener(this);
		btnSearch.addActionListener(this);
		btnSend.addActionListener(this);
		btnLogIn.addActionListener(this);
		
		this.setLocationRelativeTo(null);
		
		
		  prueba.add("1");
		  prueba.add("2");
		  prueba.add("3");
		  prueba.add("4");
		  prueba.add("5");
		 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String login = null;
		String pass = null;
		String name = null;
		int amount = 0;
		String loginMoRec = null;
		if(e.getSource().equals(btnLogIn)){
			login = txfLogin.getText();
			pass = txfPassword.getText();
			System.out.println("User " + login + " with pass " + pass);
			panel_login.setVisible(false);
			rellenarTablaPrueba(prueba);
			//TODO Get amount of money and set it to txtMoneyRightNow
			panelLogged.setVisible(true);
		}else if(e.getSource().equals(btnBuyProduct)){
			if(table.hasFocus()){
				int row = table.getSelectedRow();
				// TODO buscar por nombre
				// TODO comprar el que encuentre
			}else{
				System.out.println("Nothing selected");
			}
		}else if(e.getSource().equals(btnInsertNewProduct)){
			NewProdWindow npw = new NewProdWindow();
			npw.setVisible(true);
			npw.setSize(450, 155);
			
		}else if(e.getSource().equals(btnSearch)){
			for(int x = 0; x < 6; x++){
				for(int y = 0; y < 30; y++){
					table.setValueAt(null, y, x);
				}
			}
			Product p = null; //TODO buscar producto (quitar el null cuando se haga la llamada al server)
			table.setValueAt(p.getName(), 0, 0);
			table.setValueAt(p.getCharacteristics(), 0, 1);
		}else if(e.getSource().equals(btnSend)){
			amount = Integer.parseInt(txfAmount.getText());
			loginMoRec = txfMoneyTo.getText();
			// TODO hacer el pago con el sender login, el receiver loginoRec y la cantidad amount
		}else if(e.getSource().equals(btnAllUsers)){
			// TODO Si puede accede es que es superuser
			//rellenarTablaUser();//Coger la lista de usuarios de la BD
		}else{
			// Logger.error("Other Action detected");
		}
	}
	
	public void rellenarTablaProd(ArrayList<Product> pList){
		String name = null;
		String characteristics = null;
		
		for(int x = 0; x < pList.size(); x++){
			name = pList.get(x).getName();
			characteristics = pList.get(x).getCharacteristics();
			// Hay que añadir los valores a la tabla
				table.setValueAt(name, x, 0);
				table.setValueAt(characteristics, x, 1);
		}
	}
	
	public void rellenarTablaUser(ArrayList<User> uList){
		String login = null;
		
		for(int x = 0; x < uList.size(); x++){
			login = uList.get(x).getLogin();
			// Hay que añadir los valores a la tabla
				table.setValueAt(login, x, 0);
		}
	}
	
	
	
	public void rellenarTablaPrueba(ArrayList<String> pList){
		String name = null;		
		System.out.println(pList.size());
		for(int x = 0; x < pList.size(); x++){
			
			name = pList.get(x);
			System.out.println(name);
			// Hay que añadir los valores a la tabla
				table.setValueAt(name, x, 0);
		}
	}
	
	public static void main(String[] args) {
		MainWindow m = new MainWindow();
		m.setVisible(true);
		m.setSize(450, 300);
	}
}
