package viewCliente;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import com.toedter.calendar.JDateChooser;
import classidb.Database;
import classidb.Ristorante;
import enumerazione.Enum;
import state.RistoranteAperto;
import state.RistoranteChiuso;
import strategiaPagamentoSped.GestorePagamentoS;
import viewHomePage.ControllerInserimento;
import viewHomePage.HomePage;

import javax.swing.JSpinner;
import javax.swing.JRadioButton;

/*************************************************************
 * Questa classe serve per il pagamento della spedizione del *
 * piatto scelto dal cliente.								 *
 ************************************************************/
public class PagamentoSpedizione {
	
	private JFrame framePaga;
	private int idPiatto;
	private int quantita;
	private float totSped;
	private JTextField textVia;
	private JTextField textCap;
	private JTextField textCitta;
	private JTextField textNumCart;
	private String username;
	
	Database D = Database.getIstance();
	
	public PagamentoSpedizione(int idPiatto, int quantita, float totSped, String username) {
		this.idPiatto = idPiatto;
		this.quantita = quantita;
		this.totSped = totSped;
		this.username = username;
		initialize();	
	}
	
	public void initialize(){
		
		ImageIcon iconaSuccesso = new ImageIcon(HomePage.class.getResource("/images/succCli.jpg"));
		ImageIcon iconaErrore = new ImageIcon(HomePage.class.getResource("/images/iconaErrore.jpg"));
		
		framePaga = new JFrame();
		framePaga.getContentPane().setBackground(new Color(245, 245, 245));
		framePaga.setTitle("Pagamento");
		framePaga.setResizable(false);
		framePaga.setBounds(100, 100, 515, 577);
		framePaga.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePaga.getContentPane().setLayout(null);		
	 
		JLabel lblNewLabel = new JLabel("Pagamento");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(143, 11, 202, 44);
		framePaga.getContentPane().add(lblNewLabel);
	
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBackground(Color.BLACK);
		btnAnnulla.setForeground(Color.WHITE);
		btnAnnulla.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAnnulla.setBounds(16, 486, 117, 40);
		framePaga.getContentPane().add(btnAnnulla);
		
		btnAnnulla.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 Spedizione SP = new Spedizione();
				 framePaga.setVisible(false);
				 SP.setVisible(true);
			 }
		});
		 	 
		JLabel lblVia = new JLabel("Via");
		lblVia.setHorizontalAlignment(SwingConstants.LEFT);
		lblVia.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblVia.setBounds(20, 89, 77, 27);
		framePaga.getContentPane().add(lblVia);
		 
		JLabel lblCap = new JLabel("Cap");
		lblCap.setHorizontalAlignment(SwingConstants.LEFT);
		lblCap.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblCap.setBounds(20, 139, 77, 27);
		framePaga.getContentPane().add(lblCap);
		 
		JLabel lblCitta = new JLabel("Citt\u00E0");
		lblCitta.setHorizontalAlignment(SwingConstants.LEFT);
		lblCitta.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblCitta.setBounds(20, 190, 77, 27);
		framePaga.getContentPane().add(lblCitta);
		 
		textVia = new JTextField();
		textVia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textVia.setBounds(106, 89, 214, 27);
		framePaga.getContentPane().add(textVia);
		textVia.setColumns(10);
		 
		textCap = new JTextField();
		textCap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textCap.setColumns(10);
		textCap.setBounds(107, 139, 68, 27);
		framePaga.getContentPane().add(textCap);
		 
		textCitta = new JTextField();
		textCitta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textCitta.setColumns(10);
		textCitta.setBounds(107, 190, 105, 27);
		framePaga.getContentPane().add(textCitta);
		 
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(106, 246, 135, 30);
		framePaga.getContentPane().add(dateChooser);
		 
		JLabel lblGiorno = new JLabel("Giorno");
		lblGiorno.setHorizontalAlignment(SwingConstants.LEFT);
		lblGiorno.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblGiorno.setBounds(20, 246, 77, 33);
		framePaga.getContentPane().add(lblGiorno);
		 
		JLabel lblOra = new JLabel("Ora");
		lblOra.setHorizontalAlignment(SwingConstants.LEFT);
		lblOra.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblOra.setBounds(279, 246, 77, 30);
		framePaga.getContentPane().add(lblOra);
		 
		SpinnerModel model = new SpinnerNumberModel(0, 0, 24, 1);
		JSpinner spinnerOra = new JSpinner(model);
		spinnerOra.setFont(new Font("Tahoma", Font.PLAIN, 16));
		spinnerOra.setBounds(319, 246, 60, 30);
		framePaga.getContentPane().add(spinnerOra);
		 
		JLabel lblSelezionaMetodoPag = new JLabel("Seleziona il metodo per effettuare il pagamento: ");
		lblSelezionaMetodoPag.setHorizontalAlignment(SwingConstants.LEFT);
		lblSelezionaMetodoPag.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblSelezionaMetodoPag.setBounds(20, 287, 359, 46);
		framePaga.getContentPane().add(lblSelezionaMetodoPag);
		 
		JRadioButton rdbtnC = new JRadioButton("Carta di credito");
		rdbtnC.setBackground(new Color(245, 245, 245));
		rdbtnC.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnC.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		rdbtnC.setBounds(20, 349, 144, 34);
		framePaga.getContentPane().add(rdbtnC);
		 
		JRadioButton rdbtnP = new JRadioButton("Paypal");
		rdbtnP.setBackground(new Color(245, 245, 245));
		rdbtnP.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnP.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		rdbtnP.setBounds(184, 349, 117, 34);
		framePaga.getContentPane().add(rdbtnP);
		 
		JRadioButton rdbtnB = new JRadioButton("Bancomat");
		rdbtnB.setBackground(new Color(245, 245, 245));
		rdbtnB.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnB.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		rdbtnB.setBounds(340, 349, 124, 34);
		framePaga.getContentPane().add(rdbtnB);
		 
		textNumCart = new JTextField();
		textNumCart.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textNumCart.setColumns(10);
		textNumCart.setBounds(143, 414, 135, 29);
		framePaga.getContentPane().add(textNumCart);
		 
		JLabel lblNumCarta = new JLabel("Numero carta: ");
		lblNumCarta.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblNumCarta.setBounds(20, 414, 117, 29);
		framePaga.getContentPane().add(lblNumCarta);
		 
		JLabel lblTot = new JLabel("Totale: " + totSped);
		lblTot.setHorizontalAlignment(SwingConstants.LEFT);
		lblTot.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblTot.setBounds(354, 412, 124, 33);
		framePaga.getContentPane().add(lblTot);
		 
		JButton btnPaga = new JButton("Paga");
		btnPaga.setBackground(Color.BLACK);
		btnPaga.setForeground(Color.WHITE);
		btnPaga.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPaga.setBounds(365, 486, 117, 40);
		framePaga.getContentPane().add(btnPaga);
		
		//Similmente alla classe pagamento questo bottone usa lo strategy per il pagamento.
		btnPaga.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
				 //Ci prendiamo l'ora selezionata nello spinner.
				 int ora = (Integer)spinnerOra.getValue();
				 if(ControllerInserimento.eUnNumero(textNumCart.getText()) && !ControllerInserimento.eVuota(textVia.getText()) && !ControllerInserimento.eVuota(textCap.getText()) && !ControllerInserimento.eVuota(textCitta.getText()) && (dateChooser.getDate() != null) &&(rdbtnC.isSelected()== true || rdbtnP.isSelected()==true || rdbtnB.isSelected()==true)){
					 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
					 String giorno = dateFormat.format(dateChooser.getDate());
		             Long time = new Date().getTime();
		             Date oggi = new Date(time - time % (24 * 60 * 60 * 1000));
		             //setto le ore a 0.
		             oggi.setHours(0);
		             int OrarioApertura,OrarioChiusura;
					 OrarioApertura = D.trovaOrarioA(username);
					 OrarioChiusura = D.trovaOrarioC(username);
					 //Facciamo in modo che ci si possa prenotare alle spedizioni dal giorno attuale in poi.
					 if(dateChooser.getDate().compareTo(oggi)>0 || dateChooser.getDate().equals(oggi)){
						 /* Il costrutto if then else serve per attuare lo state che segnala all'utente che nell'ora selezionata il ristorante
						  * è aperto o chiuso. */
						 if(OrarioApertura<=ora && ora<OrarioChiusura){
							 	Ristorante ristorante = new Ristorante();
								ristorante.setStato(new RistoranteAperto());
								ristorante.statoCorrente(framePaga);
								if(rdbtnC.isSelected()==true) {
									GestorePagamentoS.setPagamentoStrategy(Enum.CartaDiCredito,textVia.getText(),Integer.parseInt(textCap.getText()),textCitta.getText(),giorno,ora,quantita,HomePage.getUsername(),idPiatto,totSped,Integer.parseInt(textNumCart.getText()));
									JOptionPane.showMessageDialog(framePaga, "Hai pagato con successo " +totSped+ "€ utilizzando la Carta di credito" , null, 0, iconaSuccesso);
									
								}else if(rdbtnP.isSelected()==true) {
									GestorePagamentoS.setPagamentoStrategy(Enum.PayPal,textVia.getText(),Integer.parseInt(textCap.getText()),textCitta.getText(),giorno,ora,quantita,HomePage.getUsername(),idPiatto,totSped,Integer.parseInt(textNumCart.getText()));
									JOptionPane.showMessageDialog(framePaga, "Hai pagato con successo " +totSped+ "€ utilizzando PayPal" , null, 0, iconaSuccesso);
						 		}else if(rdbtnB.isSelected()==true) {
						 			GestorePagamentoS.setPagamentoStrategy(Enum.Bancomat,textVia.getText(),Integer.parseInt(textCap.getText()),textCitta.getText(),giorno,ora,quantita,HomePage.getUsername(),idPiatto,totSped,Integer.parseInt(textNumCart.getText()));
									JOptionPane.showMessageDialog(framePaga, "Hai pagato con successo " +totSped+ "€ utilizzando il Bancomat" , null, 0, iconaSuccesso);
								}
								 	Spedizione SP = new Spedizione();
									framePaga.setVisible(false);
									SP.setVisible(true);								
						 }else { 
								Ristorante ristorante= new Ristorante();
								ristorante.setStato(new RistoranteChiuso());
								ristorante.statoCorrente(framePaga); 
						 }	 
					 }else JOptionPane.showMessageDialog(framePaga, "Devi selezionare una data maggiore o uguale del giorno corrente", null, 0, iconaErrore);							
				 }else JOptionPane.showMessageDialog(framePaga, "Devi selezionare almeno una tipologia di carta | I campi numero carta,via,cap,citta,giorno non possono essere vuoti", null, 0, iconaErrore);				 
				
			 }			 
		});

	 }


	 public void setVisible(boolean bool) {
		 try {
				if (bool == true) {
					PagamentoSpedizione window = new PagamentoSpedizione(idPiatto,quantita,totSped,username);
					window.framePaga.setVisible(true);
				}else{
					PagamentoSpedizione window = new PagamentoSpedizione(idPiatto,quantita,totSped,username);
					window.framePaga.setVisible(false);
				}
		 }catch (Exception e){
			 	e.printStackTrace();	
		 }
	
	 }
	 
}
