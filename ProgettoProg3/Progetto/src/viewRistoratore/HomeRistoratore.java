package viewRistoratore;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import classidb.Database;
import viewHomePage.HomePage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.SystemColor;

/***************************************************************
 * Questa classe serve per definire l'HomePage del ristoratore * 
 ***************************************************************/
public class HomeRistoratore extends JFrame{
	
	private JFrame frameRist;

	public HomeRistoratore() {
		initialize();
	}
	
	
	public void initialize(){
		
		Database D = Database.getIstance();
		ImageIcon iconaErrore = new ImageIcon(HomePage.class.getResource("/images/iconaErrore.jpg"));
		ImageIcon iconaSuccesso = new ImageIcon(HomePage.class.getResource("/images/logoutRis.jpg"));
		
		frameRist = new JFrame();
		frameRist.setBackground(Color.CYAN);
		frameRist.setForeground(Color.BLACK);
		frameRist.getContentPane().setBackground(new Color(245, 245, 245));
		frameRist.setResizable(false);
		frameRist.setTitle("Home Ristoratore");
		frameRist.setBounds(0, 0, 513, 528);
		frameRist.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameRist.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Home Ristoratore");
		lblNewLabel.setFont(new Font("Viner Hand ITC", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(131, 24, 236, 50);
		frameRist.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Nuovo piatto");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnNewButton.setBounds(38, 110, 117, 35);
		frameRist.getContentPane().add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 CreaPiatto CP = new CreaPiatto();
				 frameRist.setVisible(false);
				 CP.setVisible(true);
			 }
		});
		
		JButton btnNewButton_1 = new JButton("Modifica piatto");
		btnNewButton_1.setBackground(Color.BLACK);
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnNewButton_1.setBounds(303, 110, 154, 35);
		frameRist.getContentPane().add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
			     ModificaPiatto MP = new ModificaPiatto();
				 frameRist.setVisible(false);
				 MP.setVisible(true);
			 }
		});
		
		
		JButton btnNewButton_2 = new JButton("Crea men\u00F9");
		btnNewButton_2.setBackground(Color.BLACK);
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnNewButton_2.setBounds(38, 186, 117, 35);
		frameRist.getContentPane().add(btnNewButton_2);
		
		btnNewButton_2.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 //Se c'è almeno un piatto per tipologia è possibile entrare nella schermata CreaMenu.
			     if(D.numPiatti(HomePage.getUsername())==true) {
			    	 CreaMenu CM = new CreaMenu();
					 frameRist.setVisible(false);
					 CM.setVisible(true);
			     }else {
			    	 JOptionPane.showMessageDialog(frameRist, "Per entrare in questa schermata bisogna aver inserito almeno un piatto per tipologia", null, 0, iconaErrore);
			     }	
			 }
		});
		
		JButton btnNewButton_3 = new JButton("Prenotati");
		btnNewButton_3.setBackground(Color.BLACK);
		btnNewButton_3.setForeground(Color.WHITE);
		btnNewButton_3.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnNewButton_3.setBounds(303, 265, 154, 35);
		frameRist.getContentPane().add(btnNewButton_3);
		
		btnNewButton_3.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
			     VisualizzaPrenotazioni VP = new VisualizzaPrenotazioni();
				 frameRist.setVisible(false);
				 VP.setVisible(true);
			 }
		});		
		
		JButton btnNewButton_4 = new JButton("Visualizza Men\u00F9");
		btnNewButton_4.setBackground(Color.BLACK);
		btnNewButton_4.setForeground(Color.WHITE);
		btnNewButton_4.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnNewButton_4.setBounds(38, 265, 154, 35);
		frameRist.getContentPane().add(btnNewButton_4);
		
		btnNewButton_4.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
			     MenuSettimana MS = new MenuSettimana();
				 frameRist.setVisible(false);
				 MS.setVisible(true);
			 }
		});
				
		JButton btnNewButton_5 = new JButton("Log Out");
		btnNewButton_5.setBackground(Color.BLACK);
		btnNewButton_5.setForeground(Color.WHITE);
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_5.setBounds(38, 423, 125, 35);
		frameRist.getContentPane().add(btnNewButton_5);
		
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				JOptionPane.showMessageDialog(frameRist, "Logout effettuato con successo.", null, 0, iconaSuccesso);
				HomePage.main(null);
				HomeRistoratore.this.dispose();
				frameRist.setVisible(false);				
			}
		});		
		
		JButton btnNewButton_4_1 = new JButton("Aggiungi Ristorante");
		btnNewButton_4_1.setBackground(new Color(0, 0, 0));
		btnNewButton_4_1.setForeground(new Color(255, 255, 255));
		btnNewButton_4_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_4_1.setBounds(270, 424, 187, 35);
		
		btnNewButton_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreaRistorante CR = new CreaRistorante();
				frameRist.setVisible(false);
				CR.setVisible(true);			 
			}
		});

		int esito = D.controlRist(HomePage.getUsername());
		//Se il ristorante già è stato inserito precedentemente allora non si visualizza questo bottone.
		if(esito==0){
				frameRist.getContentPane().add(btnNewButton_4_1);
		}
		
		//Guadagno totale del ristoratore corrente.
		float guadagno = D.ritornaRicavoTot(HomePage.getUsername());
		JLabel lblGuadagn = new JLabel("Il tuoi guadagni: "+guadagno);
		lblGuadagn.setBackground(SystemColor.text);
		lblGuadagn.setHorizontalAlignment(SwingConstants.LEFT);
		lblGuadagn.setFont(new Font("Tempus Sans ITC", Font.BOLD, 19));
		lblGuadagn.setBounds(254, 342, 203, 35);
		frameRist.getContentPane().add(lblGuadagn);
		
		JButton btnAggBibita = new JButton("Aggiungi Bibita");
		btnAggBibita.setBackground(Color.BLACK);
		btnAggBibita.setForeground(Color.WHITE);
		btnAggBibita.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnAggBibita.setBounds(303, 186, 154, 35);
		frameRist.getContentPane().add(btnAggBibita);
		
		btnAggBibita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AggiuntaBibite CR = new AggiuntaBibite();
				frameRist.setVisible(false);
				CR.setVisible(true);
			}
		});
				
		JButton btnSped = new JButton("Spedizioni");
		btnSped.setBackground(Color.BLACK);
		btnSped.setForeground(Color.WHITE);
		btnSped.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSped.setBounds(38, 342, 154, 35);
		
		btnSped.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SpedizioniRist SR = new SpedizioniRist();
				frameRist.setVisible(false);
				SR.setVisible(true);
				
			}
		});
		String esitoR = D.checkSped(HomePage.getUsername());
		//Se il ristorante già è stato inserito precedentemente allora non si visualizza questo bottone.
		if(esitoR!=null && !esitoR.equals("0")){
			frameRist.getContentPane().add(btnSped);
		}
		
	}
			
	
	public void setVisible(boolean bool) {

		 try {
				if (bool == true) {		
					 HomeRistoratore window = new HomeRistoratore();			
					 window.frameRist.setVisible(true);		
				} else {	
					 HomeRistoratore window = new HomeRistoratore();
					 window.frameRist.setVisible(false);			
				}
		 }catch(Exception e) {
			 	e.printStackTrace();
		 }
	}
	
}


