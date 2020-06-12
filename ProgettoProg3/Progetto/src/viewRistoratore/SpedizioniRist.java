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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import classidb.Database;
import viewHomePage.HomePage;

/*****************************************************************************
 * Questa classe serve per visualizzare le consegne da effettuare/effettuate *
 * in base al giorno selezionato dal ristoratore							 *
 *****************************************************************************/
public class SpedizioniRist{

	private JFrame frameSR;
	private JTable table_1;
	JScrollPane scrollPane = null;
		
	public SpedizioniRist() {
		initialize();
	}
		
		
	public void initialize(){
			
		ImageIcon iconaErrore = new ImageIcon(HomePage.class.getResource("/images/iconaErrore.jpg"));
		Database D = Database.getIstance();
		
		frameSR = new JFrame();
		frameSR.getContentPane().setBackground(new Color(245, 245, 245));
		frameSR.setResizable(false);
		frameSR.setTitle("Spedizioni");
		frameSR.setBounds(0, 0, 796, 491);
		frameSR.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameSR.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Visualizza consegne");
		lblNewLabel.setFont(new Font("Viner Hand ITC", Font.PLAIN, 25));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(238, 22, 285, 39);
		frameSR.getContentPane().add(lblNewLabel);
	 
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 142, 759, 223);
		frameSR.getContentPane().add(scrollPane_1);
		 
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(228, 85, 135, 29);
		frameSR.getContentPane().add(dateChooser);
		 
		JButton btnMostraC = new JButton("Mostra consegne");
		btnMostraC.setForeground(Color.WHITE);
		btnMostraC.setBackground(Color.BLACK);
		btnMostraC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMostraC.setBounds(393, 85, 159, 29);
		frameSR.getContentPane().add(btnMostraC);
		
		btnMostraC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if(dateChooser.getDate() != null) {
					 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		             String giorno = dateFormat.format(dateChooser.getDate());
					    table_1 = new JTable(D.mostraConsegne(HomePage.getUsername(),giorno)){
						 @Override
							 public boolean isCellEditable(int row, int column) { 
							 	return false;
							 }

						};
					 scrollPane_1.setViewportView(table_1); 
				 }else JOptionPane.showMessageDialog(frameSR, "Devi selezionare una data", null, 0, iconaErrore);
			} 
		});
		 
	    JButton btnAnnulla = new JButton("Annulla");
	    btnAnnulla.setBackground(Color.BLACK);
	    btnAnnulla.setForeground(Color.WHITE);
	    btnAnnulla.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAnnulla.setBounds(335, 395, 125, 39);
		frameSR.getContentPane().add(btnAnnulla);
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeRistoratore Hr = new HomeRistoratore();
				frameSR.setVisible(false);
				Hr.setVisible(true);
			}
		});
	}


	public void setVisible(boolean bool) {
	
		try {
				if (bool == true) {
						SpedizioniRist window = new SpedizioniRist();
						window.frameSR.setVisible(true);
					 }else{
						SpedizioniRist window = new SpedizioniRist();
						window.frameSR.setVisible(false);
					 }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}