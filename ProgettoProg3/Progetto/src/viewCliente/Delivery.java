package viewCliente;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableColumn;
import classidb.Database;
import viewHomePage.HomePage;

/*****************************************************************
 * Questa classe serve per mostrare i ristoratori che effettuano *
 * spedizioni.												     *
 *****************************************************************/
public class Delivery {
	
	private JFrame frameDelivery;
	private JTable table_1;
	
	public Delivery() {
		initialize();
	}
	
	public void initialize(){
		
		Database D = Database.getIstance();
		ImageIcon iconaErrore = new ImageIcon(HomePage.class.getResource("/images/iconaErrore.jpg"));
		
		frameDelivery = new JFrame();
		frameDelivery.getContentPane().setBackground(new Color(245, 245, 245));
		frameDelivery.setTitle("Delivery");
		frameDelivery.setResizable(false);
		frameDelivery.setBounds(100, 100, 515, 500);
		frameDelivery.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameDelivery.getContentPane().setLayout(null);

		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 91, 479, 261);
		frameDelivery.getContentPane().add(scrollPane_1);
		table_1 = new JTable(D.mostraRistorantiSped()){			
			 @Override
			 public boolean isCellEditable(int row, int column) { 
			 	return false;
			 }
		};
		
		/********************************
		* Nascondiamo la colonna username, poichè quest'ultimo ci serve per far visualizzare
		* nella classe successiva, i prodotti associati ad esso.  
		***********/
		TableColumn user = table_1.getColumnModel().getColumn(3);
		user.setMinWidth(0);
		user.setMaxWidth(0);
		user.setPreferredWidth(0);
		scrollPane_1.setViewportView(table_1);
		 
		JButton btnVisualizzaMenu = new JButton("Visualizza Spedizione");
		btnVisualizzaMenu.setBackground(Color.BLACK);
		btnVisualizzaMenu.setForeground(Color.WHITE);
		btnVisualizzaMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVisualizzaMenu.setBounds(300, 415, 189, 35);
		frameDelivery.getContentPane().add(btnVisualizzaMenu);
		//Visualizziamo i ristoratori che effettuano spedizioni.
		btnVisualizzaMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_1.getSelectedRow();	
				if(row != -1) {
					String usernameRist = (String)table_1.getValueAt(row, 3);
					Spedizione Sp = new Spedizione(usernameRist);
					frameDelivery.setVisible(false);
					Sp.setVisible(true);
				}else JOptionPane.showMessageDialog(frameDelivery, "Devi selezionare un ristorante", null, 0, iconaErrore);
			}
		});
		 
		 
		JLabel lblNewLabel = new JLabel("Lista dei ristoranti a domicilio");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 23));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(78, 11, 337, 44);
		frameDelivery.getContentPane().add(lblNewLabel);
			
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBackground(Color.BLACK);
		btnAnnulla.setForeground(Color.WHITE);
		btnAnnulla.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAnnulla.setBounds(10, 412, 117, 40);
		frameDelivery.getContentPane().add(btnAnnulla);
		 
		btnAnnulla.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 HomeCliente HC = new HomeCliente();
				 frameDelivery.setVisible(false);
				 HC.setVisible(true);
			 }
		});
		
	}	
	

	public void setVisible(boolean bool) {
		 try {
				if (bool == true) {
					Delivery window = new Delivery();
					window.frameDelivery.setVisible(true);
				}else{
					Delivery window = new Delivery();
					window.frameDelivery.setVisible(false);
				}
				
		 }catch (Exception e){
		 	 	e.printStackTrace();
		 }	
	}
	
}