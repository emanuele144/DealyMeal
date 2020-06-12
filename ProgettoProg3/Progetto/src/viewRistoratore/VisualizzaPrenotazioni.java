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

/***********************************************************************
 * Questa classe serve per visualizzare le prenotazioni effettuate dai *
 * clienti in base al giorno selezionato dal ristoratore			   *
 ***********************************************************************/
public class VisualizzaPrenotazioni {

	private JFrame frameVP;
	private JTable table_1;
	JScrollPane scrollPane = null;
		
	public VisualizzaPrenotazioni() {
		initialize();
	}
		
		
	public void initialize(){
			
		ImageIcon iconaErrore = new ImageIcon(HomePage.class.getResource("/images/iconaErrore.jpg"));
		Database D = Database.getIstance();
		
		frameVP = new JFrame();
		frameVP.getContentPane().setBackground(new Color(245, 245, 245));
		frameVP.setResizable(false);
		frameVP.setTitle("Prenotati");
		frameVP.setBounds(0, 0, 590, 491);
		frameVP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameVP.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Prenotati");
		lblNewLabel.setFont(new Font("Viner Hand ITC", Font.PLAIN, 28));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(148, 24, 285, 39);
		frameVP.getContentPane().add(lblNewLabel);
	 
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 142, 552, 223);
		frameVP.getContentPane().add(scrollPane_1);
		 
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(131, 86, 135, 29);
		frameVP.getContentPane().add(dateChooser);
		 
		JButton btnMostraP = new JButton("Mostra prenotati");
		btnMostraP.setBackground(Color.BLACK);
		btnMostraP.setForeground(Color.WHITE);
		btnMostraP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMostraP.setBounds(286, 86, 159, 29);
		frameVP.getContentPane().add(btnMostraP);
		btnMostraP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if(dateChooser.getDate() != null) {
					 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		             String giorno = dateFormat.format(dateChooser.getDate());
					 table_1 = new JTable(D.mostraPrenotati(HomePage.getUsername(),giorno)){
						 @Override
						 public boolean isCellEditable(int row, int column) { 
						 	return false;
						 }
					 };
					 scrollPane_1.setViewportView(table_1); 
				 }else JOptionPane.showMessageDialog(frameVP, "Devi selezionare una data", null, 0, iconaErrore);
			}    
		});
		 
	    JButton btnAnnulla = new JButton("Annulla");
	    btnAnnulla.setBackground(Color.BLACK);
	    btnAnnulla.setForeground(Color.WHITE);
	    btnAnnulla.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAnnulla.setBounds(229, 396, 125, 39);
		frameVP.getContentPane().add(btnAnnulla);
		 
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 HomeRistoratore Hr = new HomeRistoratore();
				 frameVP.setVisible(false);
				 Hr.setVisible(true);
			}
		});
	
	}

	public void setVisible(boolean bool) {
	
		try {
				if (bool == true) {
						VisualizzaPrenotazioni window = new VisualizzaPrenotazioni();
						window.frameVP.setVisible(true);
				}else{
						VisualizzaPrenotazioni window = new VisualizzaPrenotazioni();
						window.frameVP.setVisible(false);
				}
		
		}catch(Exception e){
				e.printStackTrace();
		}
	}
	
}