package es.deusto.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.rmi.RMISecurityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import es.deusto.server.remote.ITransferer;
import es.deusto.server.db.data.Product;
import es.deusto.server.db.data.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("deprecation")
public class Client extends JFrame implements ActionListener{
	final static Logger logger = LoggerFactory.getLogger(Client.class);
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

	private static ITransferer objHello;
	private static String name;
	private User activeU;
	private int moneyNow;
	private ArrayList<Product> allProdList;
	private ArrayList<User> allUserList;

	public Client() {
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

		table = new JTable(30, 2);
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
		
		table.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				btnBuyProduct.setEnabled(false);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				btnBuyProduct.setEnabled(true);				
			}
		});
		
		this.pack();
		this.setLocationRelativeTo(null);

	}

	public static void main(String[] args) {
		if (args.length != 3) {
			logger.info("Use: java [policy] [codebase] Client.Client [host] [port] [server]");
			System.exit(0);
		}

		//        if (System.getSecurityManager() == null) {
		//            System.setSecurityManager(new RMISecurityManager());
		//        }

		try{
			name = "//" + args[0] + ":" + args[1] + "/" + args[2];
			objHello = (ITransferer) java.rmi.Naming.lookup(name);
			Client window = new Client();
			window.setVisible(true);
			window.setSize(450, 300);
		}catch(Exception e){
			logger.debug("RMI Example exception: " + e.getMessage());
			e.printStackTrace();
		}

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String login = null;
		String pass = null;
		String name = null;

		int amountSend = 0;
		String loginMoRec = null;
		if(e.getSource().equals(btnLogIn)){
			try{

				login = txfLogin.getText();
				pass = txfPassword.getText();
				System.out.println("User " + login + " with pass " + pass);
				panel_login.setVisible(false);
				objHello.registerUser(new User(txfLogin.getText(), txfPassword.getText()));
				activeU = objHello.getUser(txfLogin.getText());
				moneyNow = activeU.getMoney();
				allProdList = objHello.getAllProd();
				rellenarTablaProd(allProdList);

				//Get amount of money and set it to txtMoneyRightNow
				moneyNow = activeU.getMoney();
				txtMoneyNow.setText(moneyNow + "");
				if(activeU.getSuper()){
					btnAllUsers.setEnabled(true);
				}
				panelLogged.setVisible(true);

			}catch(Exception ex){
				logger.error("Exception launched: " + ex.getMessage());
			}
		}else if(e.getSource().equals(btnBuyProduct)){

			try{
				Product p = allProdList.get(table.getSelectedRow());
				System.out.println(p.getName());
				objHello.buyProd(txfMoneyTo.getText(), p, 
						Integer.parseInt(txfAmount.getText()), activeU.getLogin());
			}catch(Exception ex){
				logger.error("Exception launched: " + ex.getMessage());
			}

		}else if(e.getSource().equals(btnInsertNewProduct)){
			NewProdWindow npw = new NewProdWindow();
			npw.setVisible(true);
			npw.setSize(450, 155);
			npw.setLocationRelativeTo(this);

			// Get all the data from the New Product Window 
			try{
				npw.setTransferer(objHello);
			}catch(Exception ex){
				logger.error("Exception launched: " + ex.getMessage());
			}
		}else if(e.getSource().equals(btnSearch)){
			try{
				table.getColumnModel().getColumn(1).setHeaderValue("Description");
				for(int x = 0; x < 2; x++){
					for(int y = 0; y < 30; y++){
						table.setValueAt(null, y, x);
					}
				}

				Product p = objHello.searchProd(txfProdName.getText());
				table.setValueAt(p.getName(), 0, 0);
				table.setValueAt(p.getCharacteristics(), 0, 1);

			}catch(Exception ex){
				logger.error("Exception launched: " + ex.getMessage());
			}

		}else if(e.getSource().equals(btnSend)){

			amountSend = Integer.parseInt(txfAmount.getText());
			loginMoRec = txfMoneyTo.getText();
			try{
				objHello.sendMoney(loginMoRec, amountSend, activeU.getLogin());
				moneyNow -= amountSend;
				txtMoneyNow.setText(moneyNow + "");
			}catch(Exception ex){
				logger.error("Exception launched: " + ex.getMessage());
			}

		}else if(e.getSource().equals(btnAllUsers)){
			try{
				allUserList = objHello.getAllUser();
				rellenarTablaUser(allUserList);
						
			}catch(Exception ex){
				logger.error("Exception launched: " + ex.getMessage());
			}
			
		}else{
			//TODO ERROR
		}
	}

	public void rellenarTablaProd(ArrayList<Product> pList){
		String name = null;
		String characteristics = null;
		table.getColumnModel().getColumn(1).setHeaderValue("Description");
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
		String pass = null;
		table.getColumnModel().getColumn(1).setHeaderValue("Password");
		for(int x = 0; x < 2; x++){
			for(int y = 0; y < 30; y++){
				table.setValueAt(null, y, x);
			}
		}
		for(int x = 0; x < uList.size(); x++){
			login = uList.get(x).getLogin();
			pass = uList.get(x).getPassword();
			// Hay que añadir los valores a la tabla
			table.setValueAt(login, x, 0);
			table.setValueAt(pass, x, 1);
			
		}
	}

}