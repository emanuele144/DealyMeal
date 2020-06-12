package viewRistoratore;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import classidb.Piatto;
import viewHomePage.ControllerInserimento;
import viewHomePage.HomePage;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import classidb.Database;
import javax.swing.JComboBox;

/***********************************************************************
 * Questa classe serve per creare un piatto da associare al ristorante *
 * del ristoratore loggato	                                           * 
 ***********************************************************************/
public class CreaPiatto {
	
	private JFrame frameCP;
	private JTextField textNomeP;
	private JTextField txtPrezzo;
	String[] tipo = {"Primo piatto","Secondo piatto","Antipasto","Dolce","Contorno"};
	
	public CreaPiatto() {
		initialize();
	}
	
	public void initialize(){
		
		ImageIcon iconaErrore = new ImageIcon(HomePage.class.getResource("/images/iconaErrore.jpg"));
		ImageIcon iconaDomanda = new ImageIcon(HomePage.class.getResource("/images/ShowMessag.jpg"));
		Database D = Database.getIstance();
		
		frameCP = new JFrame();
		frameCP.getContentPane().setBackground(new Color(245, 245, 245));
		frameCP.setResizable(false);
		frameCP.setTitle("Crea Piatto");
		frameCP.setBounds(0, 0, 550, 570);
		frameCP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameCP.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Componi il nuovo piatto");
		lblNewLabel.setFont(new Font("Viner Hand ITC", Font.PLAIN, 23));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(132, 26, 274, 49);
		frameCP.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome Piatto");
		lblNewLabel_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblNewLabel_1.setBounds(66, 109, 104, 38);
		frameCP.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Descrizione");
		lblNewLabel_2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblNewLabel_2.setBounds(66, 185, 104, 49);
		frameCP.getContentPane().add(lblNewLabel_2);
		
		textNomeP = new JTextField();
		textNomeP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textNomeP.setBounds(229, 114, 223, 32);
		frameCP.getContentPane().add(textNomeP);
		textNomeP.setColumns(10);
		
		JTextArea textDesc = new JTextArea();
		textDesc.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textDesc.setBounds(229, 185, 233, 49);
		frameCP.getContentPane().add(textDesc);
			
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBackground(Color.BLACK);
		btnAnnulla.setForeground(Color.WHITE);
		btnAnnulla.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAnnulla.setBounds(51, 477, 117, 38);
		frameCP.getContentPane().add(btnAnnulla);
		
		txtPrezzo = new JTextField();
		txtPrezzo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPrezzo.setBounds(229, 294, 104, 26);
		frameCP.getContentPane().add(txtPrezzo);
		txtPrezzo.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Prezzo");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_4.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblNewLabel_4.setBounds(66, 289, 71, 32);
		frameCP.getContentPane().add(lblNewLabel_4);
			
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeRistoratore Hr = new HomeRistoratore();
				frameCP.setVisible(false);
				Hr.setVisible(true);
			}
		});
		
		JComboBox <String>comboBox = new JComboBox <String>(tipo);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setBounds(229, 376, 198, 32);
		frameCP.getContentPane().add(comboBox);
	
		JButton bottoneInserisci = new JButton("Inserisci");
		bottoneInserisci.setBackground(Color.BLACK);
		bottoneInserisci.setForeground(Color.WHITE);
		bottoneInserisci.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		//Questo bottone inserisc,e con gli opportuni controlli, un piatto nel database 
		bottoneInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numPi = D.checkPiatto(HomePage.getUsername(),textNomeP.getText());
				if(ControllerInserimento.eUnNumero(txtPrezzo.getText()) && !ControllerInserimento.eVuota(textNomeP.getText())) {
					//Se esiste già nel database un piatto con lo stesso nome associato al ristoratore corrente non si permette l'inserimento
					if(numPi <1) {
						String tipologia =String.valueOf(comboBox.getSelectedItem());
						Piatto P = new Piatto(textNomeP.getText(), Float.parseFloat(txtPrezzo.getText()), textDesc.getText(), tipologia, HomePage.getUsername());
						D.inserisciPiatto(P,HomePage.getUsername());
						int scelta = JOptionPane.showConfirmDialog(frameCP, "Piatto inserito. Vuoi inserire un altro piatto?", null, JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, iconaDomanda);
						if(scelta == JOptionPane.NO_OPTION) {
								HomeRistoratore Hr = new HomeRistoratore();
								frameCP.setVisible(false);
								Hr.setVisible(true);
						}else if (scelta == JOptionPane.CANCEL_OPTION) {
							
						}else if (scelta == JOptionPane.YES_OPTION) {
								textNomeP.setText("");
								txtPrezzo.setText("");
								textDesc.setText("");
						}
					}else 
						JOptionPane.showMessageDialog(frameCP, "Già esiste un piatto con questo nome!!", null, 0, iconaErrore);
				} else 
					JOptionPane.showMessageDialog(frameCP, "Il costo deve essere un numero | Nome pietanza non può essere vuoto", null, 0, iconaErrore);
			}
		});
		
		bottoneInserisci.setBounds(391, 477, 118, 38);
		frameCP.getContentPane().add(bottoneInserisci);
		
		JLabel lblNewLabel_5 = new JLabel("Tipologia");
		lblNewLabel_5.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5.setBounds(66, 376, 90, 32);
		frameCP.getContentPane().add(lblNewLabel_5);
	
	}


	public void setVisible(boolean bool) {
		try {
				if (bool==true) {
					CreaPiatto window = new CreaPiatto();
					window.frameCP.setVisible(true);
				}
				else {
					CreaPiatto window = new CreaPiatto();
					window.frameCP.setVisible(false);
				}
		}catch (Exception e) {
				e.printStackTrace();
		}
	}
	
}
