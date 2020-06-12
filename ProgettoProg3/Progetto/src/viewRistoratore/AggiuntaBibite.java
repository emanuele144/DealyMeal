package viewRistoratore;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import classidb.Bibita;
import classidb.Database;
import viewHomePage.ControllerInserimento;
import viewHomePage.HomePage;

/**************************************************************
 * Questa classe serve per aggiungere bibite ad un ristorante * 
 **************************************************************/
public class AggiuntaBibite{

	private JFrame frameB;
	private JTextField textFieldNome;
	private JTextField textFieldPrezzo;
	//Array con le varie tipologie di bibite
	String[] tipo = {"Acqua","Vino","Spumante","Birra","Digestivo"};
	
	public AggiuntaBibite() {
			initialize();
	}
		
	public void initialize(){
			
		ImageIcon iconaErrore = new ImageIcon(HomePage.class.getResource("/images/iconaErrore.jpg"));
		ImageIcon iconaDomanda = new ImageIcon(HomePage.class.getResource("/images/ShowMessag.jpg"));
		Database D = Database.getIstance();
		
		frameB = new JFrame();
		frameB.getContentPane().setBackground(new Color(245, 245, 245));
		frameB.getContentPane().setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		frameB.setResizable(false);
		frameB.setTitle("Aggiunta Bibita");
		frameB.setBounds(0, 0, 494, 433);
		frameB.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameB.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Aggiunta Bibite disponibili");
		lblNewLabel.setFont(new Font("Viner Hand ITC", Font.PLAIN, 22));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(95, 23, 297, 56);
		frameB.getContentPane().add(lblNewLabel);
		
		JLabel lblNomeB = new JLabel("Inserisci nome");
		lblNomeB.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblNomeB.setHorizontalAlignment(SwingConstants.LEFT);
		lblNomeB.setBounds(51, 119, 117, 31);
		frameB.getContentPane().add(lblNomeB);
		
		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldNome.setBounds(204, 121, 168, 27);
		frameB.getContentPane().add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel lblPrezzoB = new JLabel("Prezzo");
		lblPrezzoB.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblPrezzoB.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrezzoB.setBounds(51, 267, 117, 31);
		frameB.getContentPane().add(lblPrezzoB);
		
		textFieldPrezzo = new JTextField();
		textFieldPrezzo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldPrezzo.setBounds(204, 269, 104, 27);
		frameB.getContentPane().add(textFieldPrezzo);
		textFieldPrezzo.setColumns(10);
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBackground(Color.BLACK);
		btnAnnulla.setForeground(Color.WHITE);
		btnAnnulla.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAnnulla.setBounds(27, 346, 117, 38);
		frameB.getContentPane().add(btnAnnulla);
			btnAnnulla.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					HomeRistoratore Hr = new HomeRistoratore();
					frameB.setVisible(false);
					Hr.setVisible(true);
				}
			});
		
		JLabel lblTipologia = new JLabel("Tipologia");
		lblTipologia.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblTipologia.setHorizontalAlignment(SwingConstants.LEFT);
		lblTipologia.setBounds(51, 192, 117, 31);
		frameB.getContentPane().add(lblTipologia);
		
		JComboBox<String> comboBoxTip = new JComboBox<String>(tipo);
		comboBoxTip.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxTip.setBounds(204, 192, 168, 31);
		frameB.getContentPane().add(comboBoxTip);
		
		
		JButton bottoneInserisci = new JButton("Inserisci");
		bottoneInserisci.setBackground(Color.BLACK);
		bottoneInserisci.setForeground(Color.WHITE);
		bottoneInserisci.setFont(new Font("Tahoma", Font.PLAIN, 16));
		    //Questo bottone serve per aggiungere una bibita nel database.
			bottoneInserisci.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int numB = D.checkBibita(HomePage.getUsername(),textFieldNome.getText());
					if(ControllerInserimento.eUnNumero(textFieldPrezzo.getText()) && !ControllerInserimento.eVuota(textFieldNome.getText())) {
						//Se esiste già nel database una bibita con lo stesso nome associato al ristoratore corrente non si permette l'inserimento
						if(numB <1){
						String tipologia =String.valueOf(comboBoxTip.getSelectedItem());
						Bibita B = new Bibita(Float.parseFloat(textFieldPrezzo.getText()),textFieldNome.getText(),HomePage.getUsername(),tipologia);
						D.inserisciBibita(B,textFieldNome.getText(),Float.parseFloat(textFieldPrezzo.getText()),HomePage.getUsername(),tipologia);
						int scelta = JOptionPane.showConfirmDialog(frameB, "Bibita aggiunta correttamente. Vuoi inserirne un'altra ?", null, JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, iconaDomanda);
							if(scelta == JOptionPane.NO_OPTION) {
								HomeRistoratore Hr = new HomeRistoratore();
								frameB.setVisible(false);
								Hr.setVisible(true);
							}else if(scelta == JOptionPane.CANCEL_OPTION){
								
							}else if (scelta == JOptionPane.YES_OPTION) {
								textFieldNome.setText("");
								textFieldPrezzo.setText("");
							}
						}else JOptionPane.showMessageDialog(frameB, "Già esiste una bibita con questo nome!!", null, 0, iconaErrore);
					}else JOptionPane.showMessageDialog(frameB, "Il prezzo deve essere un numero | Nome bibita non può essere vuoto", null, 0, iconaErrore);
				}
			});
		
		bottoneInserisci.setBounds(348, 346, 117, 38);
		frameB.getContentPane().add(bottoneInserisci);
	
	}


	public void setVisible(boolean bool) {
			
		try {
			if(bool==true){
				AggiuntaBibite window = new AggiuntaBibite();
				window.frameB.setVisible(true);
			}
			else {
				AggiuntaBibite window = new AggiuntaBibite();
				window.frameB.setVisible(false);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}

