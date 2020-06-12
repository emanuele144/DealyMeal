package viewRistoratore;
import classidb.*;
import decoratorMenu.Menu;
import viewHomePage.HomePage;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

/*******************************************************************
 * Questa classe serve per creare un menù in un giorno specificato *
 * dal cliente.													   * 
 *******************************************************************/
public class CreaMenu {
	
	private JFrame frameCM;
	String[] tipo = {"Antipasto","Primo piatto","Secondo piatto","Contorno","Dolce"};
	float prezzotot=0;
	
	public CreaMenu() {
		initialize();
	}
	
	public void initialize(){
			
		ImageIcon iconaErrore = new ImageIcon(HomePage.class.getResource("/images/iconaErrore.jpg"));
		ImageIcon iconaSoldi = new ImageIcon(HomePage.class.getResource("/images/soldi.png"));
		ImageIcon iconaDomanda = new ImageIcon(HomePage.class.getResource("/images/ShowMessag.jpg"));
		Database D = Database.getIstance();
		
		frameCM = new JFrame();
		frameCM.getContentPane().setBackground(new Color(245, 245, 245));
		frameCM.setResizable(false);
		frameCM.setTitle("Crea Menù");
		frameCM.setBounds(0, 0, 520, 660);
		frameCM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameCM.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Crea Menù");
		lblNewLabel.setFont(new Font("Viner Hand ITC", Font.PLAIN, 30));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(110, 23, 285, 45);
		frameCM.getContentPane().add(lblNewLabel);
		 
		JLabel lblNewLabel_1 = new JLabel("Primo piatto");
		lblNewLabel_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(27, 167, 107, 29);
		frameCM.getContentPane().add(lblNewLabel_1);
	
		JLabel lblNewLabel_2 = new JLabel("Secondo piatto");
		lblNewLabel_2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setBounds(27, 237, 125, 29);
		frameCM.getContentPane().add(lblNewLabel_2);
		 
		JLabel lblNewLabel_3 = new JLabel("Antipasto");
		lblNewLabel_3.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setBounds(27, 101, 85, 29);
		frameCM.getContentPane().add(lblNewLabel_3);
		 
		JLabel lblNewLabel_4 = new JLabel("Dolce");
		lblNewLabel_4.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_4.setBounds(27, 372, 85, 29);
		frameCM.getContentPane().add(lblNewLabel_4);
		
		//I JcomboBox ritornano i piatti del tipo specificato.
		JComboBox comboBoxA = new JComboBox(D.elencoPiatti(tipo[0],HomePage.getUsername()).toArray());
		comboBoxA.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxA.setBounds(162, 101, 302, 29);
		frameCM.getContentPane().add(comboBoxA);
	
		JComboBox comboBoxP = new JComboBox(D.elencoPiatti(tipo[1],HomePage.getUsername()).toArray());;
		comboBoxP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxP.setBounds(162, 168, 302, 29);
		frameCM.getContentPane().add(comboBoxP);
		 
		JComboBox comboBoxS = new JComboBox(D.elencoPiatti(tipo[2],HomePage.getUsername()).toArray());
		comboBoxS.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxS.setBounds(162, 239, 302, 27);
		frameCM.getContentPane().add(comboBoxS);
		 
		JComboBox comboBoxD = new JComboBox(D.elencoPiatti(tipo[4],HomePage.getUsername()).toArray());
		comboBoxD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxD.setBounds(162, 373, 302, 29);
		frameCM.getContentPane().add(comboBoxD);
		 
		JLabel lblNewLabel_5 = new JLabel("Contorno");
		lblNewLabel_5.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5.setBounds(27, 305, 85, 29);
		frameCM.getContentPane().add(lblNewLabel_5);
		 
		JComboBox comboBoxC = new JComboBox(D.elencoPiatti(tipo[3],HomePage.getUsername()).toArray());
		comboBoxC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxC.setBounds(162, 307, 302, 27);
		frameCM.getContentPane().add(comboBoxC);
		 
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(88, 463, 125, 29);
		frameCM.getContentPane().add(dateChooser);
		 
		JLabel lblNewLabel_8 = new JLabel("Data");
		lblNewLabel_8.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_8.setBounds(27, 463, 60, 29);
		frameCM.getContentPane().add(lblNewLabel_8);
			
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBackground(Color.BLACK);
		btnAnnulla.setForeground(Color.WHITE);
		btnAnnulla.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAnnulla.setBounds(27, 562, 125, 36);
		frameCM.getContentPane().add(btnAnnulla);
		
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeRistoratore Hr = new HomeRistoratore();
				frameCM.setVisible(false);
				Hr.setVisible(true);
			}
		});
	
		JButton bottoneInserisci = new JButton("Inserisci");
		bottoneInserisci.setForeground(Color.WHITE);
		bottoneInserisci.setBackground(Color.BLACK);
		bottoneInserisci.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bottoneInserisci.setBounds(354, 562, 125, 36);
		frameCM.getContentPane().add(bottoneInserisci);		
		
		/*Il bottone inserisci, con le opportune verifiche sugli errori di inserimento, calcola il prezzo totale del nuovo menù 
		 *sommando i prezzi dei singoli piatti scelti, inserisce il menù nella tabella Menù,inserisce i piatti scelti nella tabella
		 *Contiene e infine fa visualizzare un JOptionPane con la richiesta di nuovo inserimento.*/	 
		bottoneInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prezzotot=0;
				if(dateChooser.getDate() != null) {
					if(comboBoxA != null) {
						prezzotot = prezzotot + D.ritornaPrezzo(comboBoxA.getSelectedItem().toString(),HomePage.getUsername());
					}
					if(comboBoxP != null) {
						prezzotot = prezzotot + D.ritornaPrezzo(comboBoxP.getSelectedItem().toString(),HomePage.getUsername());
					}
					if(comboBoxS != null) {
						prezzotot = prezzotot + D.ritornaPrezzo(comboBoxS.getSelectedItem().toString(),HomePage.getUsername());
					}
					if(comboBoxC != null) {
						prezzotot = prezzotot + D.ritornaPrezzo(comboBoxC.getSelectedItem().toString(),HomePage.getUsername());
					}
					if(comboBoxD != null) {
						prezzotot = prezzotot + D.ritornaPrezzo(comboBoxD.getSelectedItem().toString(),HomePage.getUsername());
					}
						
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		            String giorno = dateFormat.format(dateChooser.getDate());
					int numM = D.checkMenu(HomePage.getUsername(), giorno);
					Date dateobj = new Date(0);
					if(dateChooser.getDate().compareTo(dateobj)>0 && dateChooser.getDate() != null){
							if(numM<1) { 
								Menu M = new Menu (giorno);
								D.inserisciMenu(M,HomePage.getUsername(),prezzotot);
								String NomeA =String.valueOf(comboBoxA.getSelectedItem());
								if(NomeA != null)
								D.inserisciContiene(M,HomePage.getUsername(),NomeA);
								
								String NomeP =String.valueOf(comboBoxP.getSelectedItem());
								if(NomeP != null)
								D.inserisciContiene(M,HomePage.getUsername(),NomeP);
								
								String NomeS =String.valueOf(comboBoxS.getSelectedItem());
								if(NomeS != null)
								D.inserisciContiene(M,HomePage.getUsername(),NomeS);
								
								String NomeC =String.valueOf(comboBoxC.getSelectedItem());
								if(NomeC != null)
								D.inserisciContiene(M,HomePage.getUsername(),NomeC);
								
								String NomeD =String.valueOf(comboBoxD.getSelectedItem());
								if(NomeD != null)
								D.inserisciContiene(M,HomePage.getUsername(),NomeD);
								
								int scelta = JOptionPane.showConfirmDialog(frameCM, "Menu inserito. Vuoi inserire un altro menu?", null, JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, iconaDomanda);
								if(scelta == JOptionPane.NO_OPTION) {
									  HomeRistoratore Hr = new HomeRistoratore();
									  frameCM.setVisible(false);
									  Hr.setVisible(true);
								}else if (scelta == JOptionPane.CANCEL_OPTION) {
								
								}else if (scelta == JOptionPane.YES_OPTION) {
				
								}
						  }else JOptionPane.showMessageDialog(frameCM, "Già hai inserito un menù in questo giorno", null, 0, iconaErrore);
					}else {
					JOptionPane.showMessageDialog(frameCM, "La data inserita deve essere maggiore del giorno corrente", null, 0, iconaErrore);
					}
				}else JOptionPane.showMessageDialog(frameCM, "Devi selezionare una data", null, 0, iconaErrore);
			}

		});
		
		
		JButton btnNewButton = new JButton("Visualizza totale");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(318, 456, 146, 36);
		frameCM.getContentPane().add(btnNewButton);
		
		//Selezionando questo bottone si visualizza il totale del menù composto
		btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
			    	prezzotot=0;
			    	if(comboBoxA != null) {
						prezzotot = prezzotot + D.ritornaPrezzo(comboBoxA.getSelectedItem().toString(),HomePage.getUsername());
			    	}
					if(comboBoxP != null) {
						prezzotot = prezzotot + D.ritornaPrezzo(comboBoxP.getSelectedItem().toString(),HomePage.getUsername());
					}
					if(comboBoxS != null) {
						prezzotot = prezzotot + D.ritornaPrezzo(comboBoxS.getSelectedItem().toString(),HomePage.getUsername());
					}
					if(comboBoxC != null) {
						prezzotot = prezzotot + D.ritornaPrezzo(comboBoxC.getSelectedItem().toString(),HomePage.getUsername());
					}
					if(comboBoxD != null) {
						prezzotot = prezzotot + D.ritornaPrezzo(comboBoxD.getSelectedItem().toString(),HomePage.getUsername());
					}
							    
					JOptionPane.showMessageDialog(frameCM,"Prezzo Totale: "+prezzotot+" €", null, 0, iconaSoldi);			
			    }	    
	    });
	}		


	public void setVisible(boolean bool) {
		
		try {
				if (bool == true) {
					 CreaMenu window = new CreaMenu();
					 window.frameCM.setVisible(true);
				} else {
					 CreaMenu window = new CreaMenu();
					 window.frameCM.setVisible(false);
				}
		}catch (Exception e) {
				e.printStackTrace();
		}

	}	
	
}