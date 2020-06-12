package viewRistoratore;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import classidb.*;
import viewHomePage.HomePage;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;
import javax.swing.JScrollPane;

/**********************************************************************
 * Questa classe serve per visualizzare i menù del giorno selezionato *
 * dal ristoratore.													  * 
 **********************************************************************/
public class MenuSettimana extends JFrame{

	private JFrame frameMS;
	private JTable table_1;
	JScrollPane scrollPane = null;
	
	public MenuSettimana(){
		initialize();
	}
	
	public void initialize(){
			
		ImageIcon iconaErrore = new ImageIcon(HomePage.class.getResource("/images/iconaErrore.jpg"));
		
		Database D = Database.getIstance();
		
		frameMS = new JFrame();
		frameMS.getContentPane().setBackground(new Color(245, 245, 245));
		frameMS.setResizable(false);
		frameMS.setTitle("Menu");
		frameMS.setBounds(0, 0, 650, 550);
		frameMS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMS.getContentPane().setLayout(null);
				
		JLabel lblNewLabel = new JLabel("Menu caricati");
		lblNewLabel.setFont(new Font("Viner Hand ITC", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(166, 24, 285, 45);
		frameMS.getContentPane().add(lblNewLabel);		
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBackground(Color.BLACK);
		btnAnnulla.setForeground(Color.WHITE);
		btnAnnulla.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAnnulla.setBounds(251, 452, 117, 38);
		frameMS.getContentPane().add(btnAnnulla);
		 
		btnAnnulla.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 HomeRistoratore Hr = new HomeRistoratore();
				 frameMS.setVisible(false);
				 Hr.setVisible(true);
			 }
		});
		 	 
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(166, 87, 135, 30);
		frameMS.getContentPane().add(dateChooser);
		 
		JButton btnMostraMenu = new JButton("Mostra menu");
		btnMostraMenu.setBackground(Color.BLACK);
		btnMostraMenu.setForeground(Color.WHITE);
		btnMostraMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMostraMenu.setBounds(324, 87, 127, 30);
		frameMS.getContentPane().add(btnMostraMenu);
		 
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 142, 613, 278);
		frameMS.getContentPane().add(scrollPane_1);
		
		 //Questo bottone serve per mostrare i menù creati dal ristoratore in un dato giorno.
		 btnMostraMenu.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if(dateChooser.getDate() != null) {
					 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		             String giorno = dateFormat.format(dateChooser.getDate()); 
					 table_1 = new JTable(D.mostraMenu(giorno,HomePage.getUsername())) {
						 @Override
						 public boolean isCellEditable(int row, int column) { 
						 	return false;
						 }
					 };
					 scrollPane_1.setViewportView(table_1);
				 }else JOptionPane.showMessageDialog(frameMS, "Devi selezionare una data", null, 0, iconaErrore);
			 }
		 });	
		 
	}
		

	public void setVisible(boolean bool) {
		try {
				if (bool == true) {
					MenuSettimana window = new MenuSettimana();
					window.frameMS.setVisible(true);
				} else {
					MenuSettimana window = new MenuSettimana();
					window.frameMS.setVisible(false);
				}		
		}catch (Exception e) {		
			 e.printStackTrace();
		}		
	}
	
}