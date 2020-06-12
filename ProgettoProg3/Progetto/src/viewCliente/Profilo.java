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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableColumn;
import classidb.Database;
import memento.Memento;
import memento.Originator;
import viewHomePage.HomePage;

/*******************************************************************
 * Questa classe serve per definire il profilo dell'utente, in cui *
 * quest'ultimo ha la possibilità di disdire l'ultimo pagamento o  *
 * un altro selezionato nella tabella dei pagamenti effettuati.    *									          *
 *******************************************************************/
public class Profilo extends JFrame{

	private JFrame framePr;
	private JTable table_1;
	JScrollPane scrollPane = null;
	JLabel lblSPesaTot;
	Database D = Database.getIstance();
	
	
	public Profilo(){
		initialize();
	}
		
		
	public void initialize(){
			
		ImageIcon iconaErrore = new ImageIcon(HomePage.class.getResource("/images/iconaErrore.jpg"));
		
		framePr = new JFrame();
		framePr.getContentPane().setBackground(new Color(245, 245, 245));
		framePr.setResizable(false);
		framePr.setTitle("Profilo");
		framePr.setBounds(0, 0, 690, 572);
		framePr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePr.getContentPane().setLayout(null);					
			
		JLabel lblNewLabel = new JLabel("Profilo");
		lblNewLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 32));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(187, 11, 285, 56);
		framePr.getContentPane().add(lblNewLabel);	
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBackground(Color.BLACK);
		btnAnnulla.setForeground(Color.WHITE);
		btnAnnulla.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAnnulla.setBounds(10, 482, 117, 38);
		framePr.getContentPane().add(btnAnnulla);
		
		btnAnnulla.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 HomeCliente Cl = new HomeCliente();
				 framePr.setVisible(false);
				 Cl.setVisible(true);
			 }
		});
					
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 119, 651, 332);
		framePr.getContentPane().add(scrollPane_1);
		table_1 = new JTable(D.mostraPrenotazioni(HomePage.getUsername())) {
			 @Override
			 public boolean isCellEditable(int row, int column) { 
			 	return false;
			 }
		};
		 
		//nascondo la colonna username
		TableColumn user = table_1.getColumnModel().getColumn(7);
		user.setMinWidth(0);
		user.setMaxWidth(0);
		user.setPreferredWidth(0);
		scrollPane_1.setViewportView(table_1);		 			 
			 
		float spesaC = D.ritornaSpesa(HomePage.getUsername());
		lblSPesaTot = new JLabel("Spesa totale: "+ spesaC);
		lblSPesaTot.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblSPesaTot.setBounds(483, 82, 158, 26);
		framePr.getContentPane().add(lblSPesaTot);
		 
		String nome = D.ritornaNome(HomePage.getUsername());
		JLabel lblBenvenuto = new JLabel("Ciao " +nome+ ", ecco le tue prenotazioni: ");
		lblBenvenuto.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblBenvenuto.setBounds(10, 78, 334, 33);
		framePr.getContentPane().add(lblBenvenuto);
		 
		JButton btnDisdiciU = new JButton("Disdisci ultima operazione");
		btnDisdiciU.setBackground(Color.BLACK);
		btnDisdiciU.setForeground(Color.WHITE);
		btnDisdiciU.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDisdiciU.setBounds(190, 482, 200, 38);
		framePr.getContentPane().add(btnDisdiciU);
		//Istanziamo l'oggetto cliente della classe Originator.
		Originator cliente;
		//Facciamo in modo che questo oggetto cliente punti all'indirizzo dell'oggetto cliente istanziato nella classe pagamento. 
		cliente = Pagamento.getCliente();
		//Se il cliente ha effettuato un pagamento nella classe Pagamento allora è possibile disdire quest'ultimo.
		if(cliente.getUltimoPagamento()!=0){
				 btnDisdiciU.addActionListener(new ActionListener() {
					 public void actionPerformed(ActionEvent e) {
						/*Ci facciamo passare dalla classe Pagamento il memento e lo utilizziamo per rinizializzare la spesa attuale e 
						 *l'ultimo pagamento all'istante in cui è stato effettuato il memento.*/
						Memento mementoSpesa;
						mementoSpesa = Pagamento.getMemento();
						mementoSpesa.restoreState();
						D.disdiciPrenotazione(HomePage.getUsername());
						lblSPesaTot.setVisible(false);
						JLabel lblSPesaTot2 = new JLabel("Spesa totale: "+cliente.getSpesaTotaleCorrente());
						lblSPesaTot2.setFont(new Font("Tahoma", Font.PLAIN, 14));
						lblSPesaTot2.setBounds(514, 82, 127, 26);
						framePr.getContentPane().add(lblSPesaTot2);
						table_1 = new JTable(D.mostraPrenotazioni(HomePage.getUsername())) {
							 @Override
							 public boolean isCellEditable(int row, int column) { 
							 	return false;
							 }	
						};
						scrollPane_1.setViewportView(table_1);
						btnDisdiciU.setVisible(false);
					 }					 
				 });
		}else {
				/*Se non si ha effettuato un pagamento da quando si è aperto il programma allora non si visualizza il bottone 
				*disdici ultimo pagamento.*/
				 btnDisdiciU.setVisible(false);
		}

		JButton btnDisdici = new JButton("Disdici prenotazione selezionata");
		btnDisdici.setBackground(Color.BLACK);
		btnDisdici.setForeground(Color.WHITE);
		btnDisdici.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDisdici.setBounds(415, 482, 246, 38);
		framePr.getContentPane().add(btnDisdici);
		
		JButton BtnVisualizzaSped = new JButton("Le tue spedizioni");
		BtnVisualizzaSped.setForeground(Color.WHITE);
		BtnVisualizzaSped.setFont(new Font("Tahoma", Font.PLAIN, 15));
		BtnVisualizzaSped.setBackground(Color.BLACK);
		BtnVisualizzaSped.setBounds(0, 0, 158, 38);
		framePr.getContentPane().add(BtnVisualizzaSped);
		BtnVisualizzaSped.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
			     VisualizzaSpedizioni VP = new VisualizzaSpedizioni();
			     framePr.setVisible(false);
				 VP.setVisible(true);
			 }
		});
		
		//Questo bottone serve per disdire il pagamento selezionato dal cliente nella tabella dei pagamenti.
		btnDisdici.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int row1 = table_1.getSelectedRow();
				 //Questo costrutto if-then-else serve per gestire l'errore nel caso in cui non si abbia selezionato alcuna tupla.
				 if(row1 != -1) {
					 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		             String oggi = dateFormat.format(new Date());
					 String giorno = (String)table_1.getValueAt(row1,5);
					 //Il cliente può disdire solo prenotazioni dal giorno seguente in poi.
					 if(giorno.compareTo(oggi)>0) {						 
						 int row = table_1.getSelectedRow();	
						 int idpagamento = (int)table_1.getValueAt(row, 7);
						 D.disdiciPrenotazioneSelezionata(idpagamento);
						 lblSPesaTot.setVisible(false);
						 float spesaC = D.ritornaSpesa(HomePage.getUsername());
						 JLabel lblSPesaTot2 = new JLabel("Spesa totale: "+ spesaC);
						 lblSPesaTot2.setFont(new Font("Tahoma", Font.PLAIN, 14));
						 lblSPesaTot2.setBounds(514, 82, 127, 26);
						 framePr.getContentPane().add(lblSPesaTot2);
						 table_1 = new JTable(D.mostraPrenotazioni(HomePage.getUsername())) {							
								 @Override
								 public boolean isCellEditable(int row, int column) { 
								 	return false;
								 }	
						 };
						 scrollPane_1.setViewportView(table_1);
					 }else{
						 JOptionPane.showMessageDialog(framePr,"Non puoi disdire una prenotazione minore o uguale al giorno corrente", null, 0, iconaErrore);
					 }
				 }else JOptionPane.showMessageDialog(framePr, "Devi selezionare una prenotazione", null, 0, iconaErrore); 
			}				 
		});			
		
	}
		
		
	public void setVisible(boolean bool) {
			
			try {
					if (bool == true) {
						Profilo window = new Profilo();
						window.framePr.setVisible(true);
					}else{
						Profilo window = new Profilo();
						window.framePr.setVisible(false);
					}
			}catch (Exception e) {
					e.printStackTrace();
			}
	}
}