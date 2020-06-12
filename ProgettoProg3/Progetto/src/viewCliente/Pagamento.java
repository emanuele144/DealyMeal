package viewCliente;
import strategiaPagamento.*;
import viewHomePage.ControllerInserimento;
import viewHomePage.HomePage;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import classidb.Database;
import enumerazione.Enum;
import memento.Memento;
import memento.Originator;
import javax.swing.JRadioButton;

/**************************************************************
 * Questa classe serve per il pagamento della prenotazione ad *
 * un menù.											          *
 *************************************************************/
public class Pagamento extends javax.swing.JFrame{
	
 	JRadioButton rdbtnB;
 	JRadioButton rdbtnP;
 	JRadioButton rdbtnC;
 	
 	private float totMenu;
 	private int idMenu;
 	private int idBibita; 
	//Creo un groupButton a cui aggiungo i vari JRadioButton per il pagamento.
 	private void groupButton(){
		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(rdbtnB);
		bg1.add(rdbtnP);
		bg1.add(rdbtnC);
	}
	
	private JFrame framePG;
	private JTextField textFieldNc;
	private int orarioP;	
	private int numeroP;
	static Originator cliente; 
	static Memento mementoSpesa;
	static int countPag=0;
	
    //Gli passiamo le variabili che servono alle funzioni di questa classe e inizializziamo il groupButton e la view.
	public Pagamento(float totMenu,int idMenu,int idBibita,int numeroPrenotati, int orarioP) {
		this.totMenu = totMenu;
		this.idMenu = idMenu;
		this.idBibita = idBibita;
		this.orarioP=orarioP;
		this.numeroP = numeroPrenotati;
		initialize();
		groupButton();
	}
		
	//Utilizziamo questo metodo per richiamarci l'oggetto di tipo Originator nella classe Profilo.
	public static Originator getCliente(){
		if(countPag==0) {
			 cliente = new Originator();
			 cliente.setUltimoPagamento(0);
			 return cliente;
		}
		return cliente;
	}
	
	//Questo metodo serve per passare il memento nella classe Profilo.
	public static Memento getMemento(){
		return mementoSpesa;
	}
	
	
	public void initialize(){
		
		ImageIcon iconaSuccesso = new ImageIcon(HomePage.class.getResource("/images/succCli.jpg"));
		ImageIcon iconaErrore = new ImageIcon(HomePage.class.getResource("/images/iconaErrore.jpg"));
		
		Database D = Database.getIstance();
		
		framePG = new JFrame();
		framePG.getContentPane().setBackground(new Color(245, 245, 245));
		framePG.getContentPane().setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		framePG.setTitle("Prenotati");
		framePG.setResizable(false);
		framePG.setBounds(0, 0, 500, 483);
		framePG.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePG.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pagamento");
		lblNewLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 25));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(101, 11, 278, 50);
		framePG.getContentPane().add(lblNewLabel);

			
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBackground(Color.BLACK);
		btnAnnulla.setForeground(Color.WHITE);
		btnAnnulla.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAnnulla.setBounds(20, 391, 117, 40);
		framePG.getContentPane().add(btnAnnulla);
		 
		JLabel lblNewLabel_1 = new JLabel("Seleziona il metodo per effettuare il pagamento: ");
		lblNewLabel_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(20, 83, 366, 46);
		framePG.getContentPane().add(lblNewLabel_1);
		 
		rdbtnC = new JRadioButton("Carta di credito");
		rdbtnC.setBackground(new Color(245, 245, 245));
		rdbtnC.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		rdbtnC.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnC.setBounds(10, 145, 134, 34);
		framePG.getContentPane().add(rdbtnC);
			 
		rdbtnP = new JRadioButton("Paypal");
		rdbtnP.setBackground(new Color(245, 245, 245));
		rdbtnP.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		rdbtnP.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnP.setBounds(174, 145, 117, 34);
		framePG.getContentPane().add(rdbtnP);
		 
		rdbtnB = new JRadioButton("Bancomat");
		rdbtnB.setBackground(new Color(245, 245, 245));
		rdbtnB.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		rdbtnB.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnB.setBounds(330, 145, 124, 34);
		framePG.getContentPane().add(rdbtnB);
		 
		textFieldNc = new JTextField();
		textFieldNc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldNc.setBounds(150, 233, 164, 29);
		framePG.getContentPane().add(textFieldNc);
		textFieldNc.setColumns(10);
		 
		JLabel lblNewLabel_2 = new JLabel("Numero carta: ");
		lblNewLabel_2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblNewLabel_2.setBounds(27, 233, 113, 29);
		framePG.getContentPane().add(lblNewLabel_2);
			 			 
		JButton btnPaga = new JButton("Paga");
		btnPaga.setBackground(Color.BLACK);
		btnPaga.setForeground(Color.WHITE);
		btnPaga.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPaga.setBounds(348, 391, 124, 40);
		framePG.getContentPane().add(btnPaga);
		
		/*Questo bottone serve per confermare il pagamento. Utilizza i pattern memento e strategy, in particolare crea il memento 
		  e attua una strategia in base alla tipologia di pagamento scelta.*/
		btnPaga.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				 if(ControllerInserimento.eUnNumero(textFieldNc.getText()) && !ControllerInserimento.eVuota(textFieldNc.getText()) && (rdbtnC.isSelected()== true || rdbtnP.isSelected()==true || rdbtnB.isSelected()==true)){
					 countPag=0;
					 //Creiamo un nuovo cliente che effettua la spesa
					 cliente = new Originator();
					 //Settiamo la spesa totale prendendo il suo valore dal database
					 cliente.setSpesaTotaleCorrente(D.ritornaSpesa(HomePage.getUsername()));
					 //Settiamo l'ultimo pagamento a 0 
					 cliente.nuovaSpesa(0);
					 //Creiamo il memento.
					 mementoSpesa = cliente.creaMemento();
					 
					 if(rdbtnC.isSelected()==true) {
						 GestorePagamento.setPagamentoStrategy(Enum.CartaDiCredito,totMenu,Integer.parseInt(textFieldNc.getText()),numeroP,orarioP, HomePage.getUsername(),idMenu,idBibita);
						 JOptionPane.showMessageDialog(framePG, "Hai pagato con successo " +totMenu+ "€ utilizzando la Carta di credito" , null, 0, iconaSuccesso);
					
					 }else if(rdbtnP.isSelected()==true) {
						 GestorePagamento.setPagamentoStrategy(Enum.PayPal,totMenu,Integer.parseInt(textFieldNc.getText()),numeroP,orarioP,HomePage.getUsername(),idMenu,idBibita);
						 JOptionPane.showMessageDialog(framePG, "Hai pagato con successo " +totMenu+ "€ utilizzando PayPal" , null, 0, iconaSuccesso);
				 	 }else if(rdbtnB.isSelected()==true) {
				 		 GestorePagamento.setPagamentoStrategy(Enum.Bancomat,totMenu,Integer.parseInt(textFieldNc.getText()),numeroP,orarioP,HomePage.getUsername(),idMenu,idBibita);
				 		 JOptionPane.showMessageDialog(framePG, "Hai pagato con successo " +totMenu+ "€ utilizzando il Bancomat" , null, 0, iconaSuccesso);
					 }
					 //Settiamo l'ultimo pagamento effettuato nel memento, in modo tale che nella classe profilo può poi annullarlo. 
					 cliente.nuovaSpesa(totMenu);
					 //Questa variabile serve per segnalare alla classe Profilo che c'è stato un pagamento.
					 countPag++;
					 //Una volta effettuato il pagamento ritorna alla schermata HomeCliente.
					 HomeCliente HC = new HomeCliente();
					 framePG.setVisible(false);
					 HC.setVisible(true);
				 }else JOptionPane.showMessageDialog(framePG, "Devi selezionare almeno una tipologia di carta | Il campo numero carta non puo essere vuoto", null, 0, iconaErrore);			 
			 }			 
		
		});
			 
			 
		JLabel lblTot = new JLabel("Totale:  " + totMenu);
		lblTot.setHorizontalAlignment(SwingConstants.LEFT);
		lblTot.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblTot.setBounds(296, 320, 143, 30);
		framePG.getContentPane().add(lblTot);
			 
			 
		btnAnnulla.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 HomeCliente HC = new HomeCliente();
				 framePG.setVisible(false);
				 HC.setVisible(true);	
			 }
		});			 
			 
	}


	public void setVisible(boolean bool) {
		
		try {
				if (bool == true) {
					Pagamento window = new Pagamento(totMenu,idMenu,idBibita,numeroP,orarioP);
					window.framePG.setVisible(true);
				} else {
					Pagamento window = new Pagamento(totMenu,idMenu,idBibita,numeroP,orarioP);
					window.framePG.setVisible(false);
				}		
		}catch (Exception e) {	
				e.printStackTrace();
		}
	
	}
	
}
	
