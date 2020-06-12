package viewCliente;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.TableColumn;
import state.*;
import com.toedter.calendar.JDateChooser;
import classidb.Database;
import classidb.Ristorante;
import decoratorMenu.AbstractMenu;
import decoratorMenu.BibitaDecorator;
import decoratorMenu.Menu;
import viewHomePage.HomePage;
import javax.swing.JComboBox;
import javax.swing.JSpinner;

/***********************************************************************
 * Questa classe serve per visualizzare il menu del giorno attuale o di*
 * uno selezionato dal cliente del ristoratore passato dalla classe    *
 * precedente.												           *
 **********************************************************************/
public class VisualizzaMenuC extends JFrame{
	
	private JFrame frameVM;
	private JTable table_1;
	JScrollPane scrollPane = null;
	private String usernameRist;
	private String date;
	private float totMenu;
	
	public VisualizzaMenuC(String usernameR, String giornoC){
		date = giornoC;
		usernameRist = usernameR;	
		initialize();
	}
	
	
	
	public void initialize(){
			
		ImageIcon iconaErrore = new ImageIcon(HomePage.class.getResource("/images/iconaErrore.jpg"));
		Database D = Database.getIstance();
		
		frameVM = new JFrame();
		frameVM.getContentPane().setBackground(new Color(245, 245, 245));
		frameVM.setResizable(false);
		frameVM.setTitle("Menu del giorno");
		frameVM.setBounds(0, 0, 560, 560);
		frameVM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameVM.getContentPane().setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Menu del giorno");
		lblNewLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 25));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(128, 22, 285, 45);
		frameVM.getContentPane().add(lblNewLabel);
		
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBackground(Color.BLACK);
		btnAnnulla.setForeground(Color.WHITE);
		btnAnnulla.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAnnulla.setBounds(10, 471, 117, 38);
		frameVM.getContentPane().add(btnAnnulla);
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeCliente Hc = new HomeCliente();
				frameVM.setVisible(false);
				Hc.setVisible(true);
				}
		});
		 
		 
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(64, 87, 135, 30);
		frameVM.getContentPane().add(dateChooser);
		 
		
		JButton btnMostraMenu = new JButton("Mostra menu");
		btnMostraMenu.setBackground(Color.BLACK);
		btnMostraMenu.setForeground(Color.WHITE);
		btnMostraMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMostraMenu.setBounds(376, 87, 160, 30);
		frameVM.getContentPane().add(btnMostraMenu);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 142, 526, 234);
		frameVM.getContentPane().add(scrollPane_1);
		
		
		JLabel lblNewLabel_1 = new JLabel("Scegli bibita:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblNewLabel_1.setBounds(10, 395, 117, 30);
		frameVM.getContentPane().add(lblNewLabel_1);
		
		
		JComboBox comboBox = new JComboBox(D.mostraBibite(usernameRist).toArray());
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setBounds(131, 397, 143, 30);
		frameVM.getContentPane().add(comboBox);
		
		
		SpinnerModel model = new SpinnerNumberModel(0, 0, 24, 1);
		JSpinner spinnerOrarioP = new JSpinner(model);
		spinnerOrarioP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinnerOrarioP.setBounds(282, 87, 56, 30);
		frameVM.getContentPane().add(spinnerOrarioP);
		
		
		JLabel lblNewLabel_2 = new JLabel("Numero Persone: ");
		lblNewLabel_2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblNewLabel_2.setBounds(297, 395, 143, 30);
		frameVM.getContentPane().add(lblNewLabel_2);
		
		
		SpinnerModel model3 = new SpinnerNumberModel(1, 1, 200, 1);
		JSpinner spinnerPrenotati = new JSpinner(model3);
		spinnerPrenotati.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinnerPrenotati.setBounds(441, 397, 72, 28);
		frameVM.getContentPane().add(spinnerPrenotati);
	
		
		JButton btnAcquisto = new JButton("Acquista menu");
		btnAcquisto.setBackground(Color.BLACK);
		btnAcquisto.setForeground(Color.WHITE);
		btnAcquisto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAcquisto.setBounds(385, 471, 151, 38);
		frameVM.getContentPane().add(btnAcquisto);
		/*Questo bottone quando cliccato,effettua vari controlli sull'inserimento delle variabili e se soddisfatti porta alla schermata Pagamento*/
		//Qui si effettuano due state: ristorante aperto/chiuso e ristorante pieno
		btnAcquisto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	            String giorno = dateFormat.format(dateChooser.getDate());
	            int idMenu = (int)table_1.getValueAt(0, 4);
				int OrarioApertura,OrarioChiusura;
				OrarioApertura = D.trovaOrarioA(usernameRist);
				OrarioChiusura = D.trovaOrarioC(usernameRist);
				int OrarioP = (Integer)spinnerOrarioP.getValue(); 
	            int postDisp = D.numPosti(usernameRist,giorno);
	            int ris = postDisp-((Integer)spinnerPrenotati.getValue());
				int numP = D.ritornaPrenotazione(HomePage.getUsername(),giorno, usernameRist);
					if(dateChooser.getDate() != null) {
						//Questo costrutto if then else controlla che i posti inseriti dall'utente siano disponibili...
						if(ris>=0){
							//Questo if then else controlla che il cliente non sia già prenotato per il giorno e il ristorante selezionati
							if(numP <1) {
								AbstractMenu M = new Menu(totMenu);
								int idBibita = D.ritornaIdBibita(comboBox.getSelectedItem().toString(),usernameRist);
								AbstractMenu MenuBibita = new BibitaDecorator(M,D.ritornaPrezzoBibita(comboBox.getSelectedItem().toString(),usernameRist));
								int numeroP = (Integer)spinnerPrenotati.getValue();
								totMenu = MenuBibita.getPrezzo()*numeroP;
								if(OrarioApertura<=OrarioP && OrarioP<OrarioChiusura){				
									Ristorante ristorante = new Ristorante();
									ristorante.setStato(new RistoranteAperto());
									ristorante.statoCorrente(frameVM);
									Pagamento Pa = new Pagamento(totMenu,idMenu,idBibita,(Integer)spinnerPrenotati.getValue(),(Integer)spinnerOrarioP.getValue());
									frameVM.setVisible(false);
									Pa.setVisible(true);
								}else {
									Ristorante ristorante= new Ristorante();
									ristorante.setStato(new RistoranteChiuso());
									ristorante.statoCorrente(frameVM);
								}
									
							}else JOptionPane.showMessageDialog(frameVM, "Già ti sei prenotato a questo menù ", null, 0, iconaErrore);
						}else {
							//...altrimenti visualizza lo stato di ristorante pieno
							  Ristorante ristorante= new Ristorante();
							  ristorante.setStato(new RistorantePieno());
							  ristorante.statoCorrente(frameVM);
							  JOptionPane.showMessageDialog(frameVM,"Posti disponibili: "+postDisp, null, 0, iconaErrore);
						}
						
					}else JOptionPane.showMessageDialog(frameVM, "Devi selezionare una data in cui ci sia un menu", null, 0, iconaErrore);
			 }
		});
		
		totMenu = D.ritornaPrezzoM(date, usernameRist);
		table_1= new JTable(D.mostraMenu(date,usernameRist));
		TableColumn idMenu = table_1.getColumnModel().getColumn(4);
		idMenu.setMinWidth(0);
		idMenu.setMaxWidth(0);
		idMenu.setPreferredWidth(0);
		scrollPane_1.setViewportView(table_1);
		
	
		JLabel lblData = new JLabel("Data");
		lblData.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblData.setBounds(20, 87, 66, 30);
		frameVM.getContentPane().add(lblData);
		
		
		JLabel lblOra = new JLabel("Ora");
		lblOra.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblOra.setBounds(241, 87, 46, 30);
		frameVM.getContentPane().add(lblOra);
		//bottone che mostra il menù del giorno selezionato dal cliente
		btnMostraMenu.addActionListener(new ActionListener() {
			 @SuppressWarnings("deprecation")
			 public void actionPerformed(ActionEvent e) {
				 //Questo if then else controlla che nella data selezionata ci sia un menù
				 if(dateChooser.getDate() != null) {
					 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		             String giorno = dateFormat.format(dateChooser.getDate());
		             Long time = new Date().getTime();
		             Date oggi = new Date(time - time % (24 * 60 * 60 * 1000));
		             //setto le ore a 0.
		             oggi.setHours(0);
		             //	Questo costrutto controlla che il giorno selezionato dal cliente sia maggiore o uguale del giorno corrente
		             if(dateChooser.getDate().compareTo(oggi)>0 || dateChooser.getDate().equals(oggi)) {
			                 totMenu = D.ritornaPrezzoM(giorno, usernameRist);
			                 if(totMenu != 0) {
								 table_1 = new JTable(D.mostraMenu(giorno,usernameRist)){
									 @Override
									 public boolean isCellEditable(int row, int column) { 
									 	return false;
									 }
								 };
								 
					 TableColumn user = table_1.getColumnModel().getColumn(4);
					 user.setMinWidth(0);
					 user.setMaxWidth(0);
					 user.setPreferredWidth(0);
					 scrollPane_1.setViewportView(table_1); 
					 
			                 }else{
			                	 JOptionPane.showMessageDialog(frameVM,"In questo giorno non ci sono menu. Selezionare un' altra data", null, 0, iconaErrore);	 
			                 }
			                 
		             }else {
			            JOptionPane.showMessageDialog(frameVM,"Non puoi visualizzare un menu scaduto", null, 0, iconaErrore);
		             }
		             
				 }else JOptionPane.showMessageDialog(frameVM,"Inserisci una data", null, 0, iconaErrore);
			 }
		});
		 
		 
	
	}

		
		
		

	public void setVisible(boolean bool) {
		
		try {
			if (bool == true) {
				VisualizzaMenuC window = new VisualizzaMenuC(usernameRist,date);
				 window.frameVM.setVisible(true);
				 
				 }else{
					 VisualizzaMenuC window = new VisualizzaMenuC(usernameRist,date);
					 window.frameVM.setVisible(false);
				 }
		
		}catch(Exception e){
		
		e.printStackTrace();
		}
	
	}
}