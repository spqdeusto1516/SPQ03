package es.deusto.client;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class NewProdWindow extends JFrame implements ActionListener{
	private JTextField txfName;
	private JTextField txfDesc;
	private JButton btnInsert;
	private JButton btnCancel;
	public NewProdWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);
		
		btnInsert = new JButton("Insert");
		btnInsert.setBounds(283, 82, 89, 23);
		getContentPane().add(btnInsert);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(67, 82, 89, 23);
		getContentPane().add(btnCancel);
		
		txfName = new JTextField();
		txfName.setBounds(96, 11, 86, 20);
		getContentPane().add(txfName);
		txfName.setColumns(10);
		
		txfDesc = new JTextField();
		txfDesc.setBounds(96, 51, 305, 20);
		getContentPane().add(txfDesc);
		txfDesc.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(22, 14, 46, 14);
		getContentPane().add(lblName);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(22, 57, 64, 14);
		getContentPane().add(lblDescription);
	
		btnCancel.addActionListener(this);
		btnInsert.addActionListener(this);
		this.setLocationRelativeTo(null);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnCancel)){
			this.dispose();
		}else if(e.getSource().equals(btnInsert)){
			//TODO Insertar Producto
		}else{
			// Logger.error("Other Action detected");
		}
	}
}
