package viewCliente;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import viewHomePage.HomePage;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/*****************************************************************
 * Questa classe serve per mostrare i ristoratori che effettuano *
 * spedizioni.												     *
 *****************************************************************/
public class Spedizione extends JFrame{
	
	private JFrame frameSpedizione;
	private JTable table_1;
	private String username;
	//Array per le varie tipologie di piatti.
	String[] tipo = {"Antipasto","Primo piatto","Secondo piatto","Contorno","Dolce"};
	
	public Spedizione(String username) {
		initialize();
		this.username = username;
	}
	
	public Spedizione(){
		initialize();
	}
	
public void initialize(){
		
		Database D = Database.getIstance();
		ImageIcon iconaErrore = new ImageIcon(HomePage.class.getResource("/images/iconaErrore.jpg"));
		
		frameSpedizione = new JFrame();
		frameSpedizione.getContentPane().setBackground(new Color(245, 245, 245));
		frameSpedizione.setTitle("Spedizione");
		frameSpedizione.setResizable(false);
		frameSpedizione.setBounds(100, 100, 515, 577);
		frameSpedizione.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameSpedizione.getContentPane().setLayout(null);


		JLabel lblNewLabel = new JLabel("Spedizione");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(158, 11, 175, 44);
		frameSpedizione.getContentPane().add(lblNewLabel);
		
		 
		JLabel lblNewLabel_2 = new JLabel("Seleziona tipologia");
		lblNewLabel_2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblNewLabel_2.setBounds(10, 89, 148, 28);
		frameSpedizione.getContentPane().add(lblNewLabel_2);
		 
		JComboBox<String> comboBox = new JComboBox<String>(tipo);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setBounds(168, 89, 175, 28);
		frameSpedizione.getContentPane().add(comboBox);
		 
		 
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 184, 479, 217);
		frameSpedizione.getContentPane().add(scrollPane_1);
		 
		 
		JButton btnCerca = new JButton("Cerca");
		btnCerca.setBackground(Color.BLACK);
		btnCerca.setForeground(Color.WHITE);
		btnCerca.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCerca.setBounds(353, 89, 88, 28);
		frameSpedizione.getContentPane().add(btnCerca);
		//Data una tipologia cerca i piatti associati ad essa e li fa visualizzare all'interno di una tabella. 
		btnCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipologia = String.valueOf(comboBox.getSelectedItem());
					table_1 = new JTable(D.mostraPiattiSped(tipologia,username)) {
						 @Override
						 public boolean isCellEditable(int row, int column) { 
						 	return false;
						 }
					};
					
		 	TableColumn idPiatto = table_1.getColumnModel().getColumn(3);
		 	idPiatto.setMinWidth(0);
		 	idPiatto.setMaxWidth(0);
		 	idPiatto.setPreferredWidth(0);
			scrollPane_1.setViewportView(table_1);
				 
			}
		});
		
		 

		JLabel lblNewLabel_1 = new JLabel("Ora, seleziona il piatto che desideri");
		lblNewLabel_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblNewLabel_1.setBounds(10, 146, 265, 27);
		frameSpedizione.getContentPane().add(lblNewLabel_1);
		 
		
		JLabel lblNewLabel_3 = new JLabel("Quantita");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblNewLabel_3.setBounds(158, 430, 74, 28);
		frameSpedizione.getContentPane().add(lblNewLabel_3);
		 
		 
		SpinnerModel model = new SpinnerNumberModel(1, 1, 20, 1);
		JSpinner spinnerQ = new JSpinner(model);
		spinnerQ.setFont(new Font("Tahoma", Font.PLAIN, 16));
		spinnerQ.setBounds(238, 431, 63, 28);
		frameSpedizione.getContentPane().add(spinnerQ);
		 
		
		JButton btnPaga = new JButton("Paga");
		btnPaga.setBackground(Color.BLACK);
		btnPaga.setForeground(Color.WHITE);
		btnPaga.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPaga.setBounds(372, 486, 117, 40);
		frameSpedizione.getContentPane().add(btnPaga);
		//Quando il cliente preme sul pulsante Paga passa alla classe pagamento PagamentoSpedizione. 
		btnPaga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Qui effettuiamo il controllo che al momento del click la tabella non sia vuota.
				if(table_1 != null) {
					 int row = table_1.getSelectedRow();
					 if(row != -1) {
						 int numeroP = (Integer)spinnerQ.getValue();
						 float prezzoP = (float)table_1.getValueAt(row, 2);
						 int idPiatto = (int)table_1.getValueAt(row, 3);
						 /*gli passiamo il totale del costo del piatto moltiplicato per il numero delle persone selezionate con l'aggiunta
						 * di un euro della spedizione.*/
						 float totSped = (numeroP*prezzoP + 1);
						 PagamentoSpedizione PS = new PagamentoSpedizione(idPiatto,numeroP,totSped,username);
						 frameSpedizione.setVisible(false);
						 PS.setVisible(true);
					 }else JOptionPane.showMessageDialog(frameSpedizione, "Devi selezionare un piatto", null, 0, iconaErrore);
				}else JOptionPane.showMessageDialog(frameSpedizione, "Seleziona una tipologia", null, 0, iconaErrore);
			}
		});
		 
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBackground(Color.BLACK);
		btnAnnulla.setForeground(Color.WHITE);
		btnAnnulla.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAnnulla.setBounds(10, 486, 117, 40);
		frameSpedizione.getContentPane().add(btnAnnulla);
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			HomeCliente HC = new HomeCliente();
			frameSpedizione.setVisible(false);
			HC.setVisible(true);
			}
		});
		 
	}
	
	


	public void setVisible(boolean bool) {
		try {
			if(bool == true) {
				Spedizione window = new Spedizione(username);
				window.frameSpedizione.setVisible(true);
			}else{
				Spedizione window = new Spedizione(username);
				window.frameSpedizione.setVisible(false);
			}
	 	}catch (Exception e){
	 	e.printStackTrace();
		}
	
	}
}
