package es.deusto.client;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.server.remote.ITransferer;
import es.deusto.server.db.data.Product;

import javax.swing.JLabel;

public class NewProdWindow extends JFrame implements ActionListener{
	final static Logger logger = LoggerFactory.getLogger(Client.class);
	private JTextField txfName;
	private JTextField txfDesc;
	private JButton btnInsert;
	private JButton btnCancel;

	private String desc;
	private String pName;
	private static ITransferer objHello;
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
		txfName.setBounds(106, 11, 76, 20);
		getContentPane().add(txfName);
		txfName.setColumns(10);

		txfDesc = new JTextField();
		txfDesc.setBounds(106, 51, 295, 20);
		getContentPane().add(txfDesc);
		txfDesc.setColumns(10);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(22, 14, 46, 14);
		getContentPane().add(lblName);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(22, 57, 74, 14);
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
			pName = txfName.getText();
			desc = txfDesc.getText();
			try{
				objHello.registerProd(new Product(pName, desc));
			}catch(Exception ex){
				logger.error("Exception launched: " + ex.getMessage());
			}
		}else{
			logger.error("Strange Action detected");
		}
	}


	public void setTransferer(ITransferer iTrans){
		objHello = iTrans;
	}

}
