package viewCliente;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import viewHomePage.ControllerInserimento;
import viewHomePage.HomePage;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.TableColumn;

import classidb.Database;
import javax.swing.JTextField;
import java.awt.Color;

/*****************************************************************
 * Questa classe serve come Home per il cliente e si mostrano in *
 * una tabella i ristoranti con le varie informazioni.			 *
 *****************************************************************/
public class HomeCliente extends JFrame{
	
	private JFrame frameCliente;
	private JTable table_1;
	private JTextField textField;
	
	
	public HomeCliente() {
		initialize();
	}
	
	public void initialize(){
		
		Database D = Database.getIstance();
		
		ImageIcon iconaErrore = new ImageIcon(HomePage.class.getResource("/images/iconaErrore.jpg"));
		ImageIcon iconaSuccesso = new ImageIcon(HomePage.class.getResource("/images/logoutCli.jpg"));
		
		frameCliente = new JFrame();
		frameCliente.getContentPane().setBackground(new Color(245, 245, 245));
		frameCliente.setTitle("Home Cliente");
		frameCliente.setResizable(false);
		frameCliente.setBounds(100, 100, 500, 550);
		frameCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameCliente.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Profilo");
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(391, 0, 93, 35);
		frameCliente.getContentPane().add(btnNewButton);
		//Questa tipologia di bottone permette di passare alla view sotto descritta.
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				Profilo Pr = new Profilo();
				frameCliente.setVisible(false);
				Pr.setVisible(true);
			}
		});				
		
		JLabel lblNewLabel = new JLabel("Benvenuto Cliente");
		lblNewLabel.setBackground(new Color(245, 245, 245));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 23));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(120, 11, 234, 44);
		frameCliente.getContentPane().add(lblNewLabel);			
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 177, 464, 261);
		frameCliente.getContentPane().add(scrollPane_1);
		table_1 = new JTable(D.mostraRistoranti()) {
			 @Override
			 public boolean isCellEditable(int row, int column) { 
			 	return false;
			 }
		};		 
		
		//nascondo la colonna username
		TableColumn user = table_1.getColumnModel().getColumn(3);
		user.setMinWidth(0);
		user.setMaxWidth(0);
		user.setPreferredWidth(0);
		scrollPane_1.setViewportView(table_1);				 		
		 
		JButton btnVisualizzaMenu = new JButton("Visualizza Menu");
		btnVisualizzaMenu.setBackground(Color.BLACK);
		btnVisualizzaMenu.setForeground(Color.WHITE);
		btnVisualizzaMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVisualizzaMenu.setBounds(313, 465, 161, 35);
		frameCliente.getContentPane().add(btnVisualizzaMenu);
		//Questo bottone ci sposta alla view in cui si visualizza il menù selezionato, se non si seleziona quest'ultimo compare un JOptionPane.
		btnVisualizzaMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_1.getSelectedRow();	
				if(row != -1) {
					String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
					String usernameRist = (String)table_1.getValueAt(row, 3);
					VisualizzaMenuC VMC = new VisualizzaMenuC(usernameRist,date);
					frameCliente.setVisible(false);
					VMC.setVisible(true);
				}else JOptionPane.showMessageDialog(frameCliente, "Devi selezionare un ristorante", null, 0, iconaErrore);
			}
		});				 				 
		
		JButton btnNewButton_5 = new JButton("Log Out");
		btnNewButton_5.setBackground(Color.BLACK);
		btnNewButton_5.setForeground(Color.WHITE);
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_5.setBounds(10, 465, 125, 35);
		frameCliente.getContentPane().add(btnNewButton_5);
		
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				JOptionPane.showMessageDialog(frameCliente, "Logout effettuato con successo.", null, 0, iconaSuccesso);
				HomePage.main(null);
				HomeCliente.this.dispose();
				frameCliente.setVisible(false);				
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Seleziona un ristorante per scoprirne il men\u00F9 del giorno");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblNewLabel_1.setBounds(10, 122, 409, 44);
		frameCliente.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ricerca per citt\u00E0");
		lblNewLabel_2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblNewLabel_2.setBounds(73, 82, 125, 29);
		frameCliente.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBounds(208, 79, 111, 32);
		frameCliente.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnRicerca = new JButton("Cerca");
		btnRicerca.setBackground(Color.BLACK);
		btnRicerca.setForeground(Color.WHITE);
		btnRicerca.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRicerca.setBounds(326, 79, 78, 32);
		frameCliente.getContentPane().add(btnRicerca);
		//Questo bottone cerca i ristoranti per città e controlla che i campi obbligatori non siano vuoti.
		btnRicerca.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if(!ControllerInserimento.eVuota(textField.getText())){
					 table_1 = new JTable(D.ricercaRistorante(textField.getText())){
						 @Override
						 public boolean isCellEditable(int row, int column) { 
						 	return false;
						 }
					 };
					 TableColumn user = table_1.getColumnModel().getColumn(3);
					 user.setMinWidth(0);
					 user.setMaxWidth(0);
					 user.setPreferredWidth(0);
					 scrollPane_1.setViewportView(table_1); 
	             }
	             else {
	            	 JOptionPane.showMessageDialog(frameCliente,"Non hai inserito una citta", null, 0, iconaErrore);
	             }
			 }	             
		});
		
		JButton btnDelivery = new JButton("Delivery");
		btnDelivery.setBackground(Color.BLACK);
		btnDelivery.setForeground(Color.WHITE);
		btnDelivery.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelivery.setBounds(0, 0, 93, 35);
		frameCliente.getContentPane().add(btnDelivery);
	
		btnDelivery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				Delivery De = new Delivery();
				frameCliente.setVisible(false);
				De.setVisible(true);
			}
		});

	}	
	 
	
	public void setVisible(boolean bool) {
		 try {
				if (bool == true) {
					HomeCliente window = new HomeCliente();
					window.frameCliente.setVisible(true);
				}else{
					HomeCliente window = new HomeCliente();
					window.frameCliente.setVisible(false);
				}
		 }catch (Exception e){
		 	 	e.printStackTrace();
		 }
	}

}