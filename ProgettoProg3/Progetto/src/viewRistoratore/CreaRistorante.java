package viewRistoratore;
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
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import classidb.Database;
import classidb.Ristorante;
import viewHomePage.ControllerInserimento;
import viewHomePage.HomePage;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;

/****************************************************************************
 * Questa classe serve per creare un ristorante da associare al ristoratore *
 *  loggato e inserirlo nel database.	                                    * 
 ****************************************************************************/
public class CreaRistorante extends JFrame{
	
	private JFrame frameCR;
	private JTextField textFieldNR;
	private JTextField textFieldV;
	private JTextField textFieldCap;
	private JTextField textFieldCi;
	private JTextField textFieldCapienza;
	JRadioButton rdbtnS;
 	JRadioButton rdbtnN;
 	
 	private void groupButton(){
		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(rdbtnS);
		bg1.add(rdbtnN);
	}
	
	public CreaRistorante(){
		initialize();
		groupButton();
	}
	public void initialize(){
		
		ImageIcon iconaErrore = new ImageIcon(HomePage.class.getResource("/images/iconaErrore.jpg"));
		ImageIcon iconaSuccessoRist = new ImageIcon(HomePage.class.getResource("/images/succRist.png"));
		
		Database D = Database.getIstance();
		
		frameCR = new JFrame();
		frameCR.getContentPane().setBackground(new Color(245, 245, 245));
		frameCR.setResizable(false);
		frameCR.setTitle("Menu");
		frameCR.setBounds(0, 0, 530, 560);
		frameCR.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameCR.getContentPane().setLayout(null);
				
		JLabel lblNewLabel = new JLabel("Crea Ristorante");
		lblNewLabel.setFont(new Font("Viner Hand ITC", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(117, 11, 270, 45);
		frameCR.getContentPane().add(lblNewLabel);
			
		rdbtnS = new JRadioButton("Si");
		rdbtnS.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		rdbtnS.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnS.setBounds(165, 420, 62, 26);
		frameCR.getContentPane().add(rdbtnS);
		 
		rdbtnN = new JRadioButton("No");
		rdbtnN.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		rdbtnN.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnN.setBounds(229, 420, 62, 26);
		frameCR.getContentPane().add(rdbtnN);
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBackground(Color.BLACK);
		btnAnnulla.setForeground(Color.WHITE);
		btnAnnulla.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAnnulla.setBounds(22, 471, 117, 35);
		frameCR.getContentPane().add(btnAnnulla);
		
		btnAnnulla.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 HomeRistoratore Hr = new HomeRistoratore();
				 frameCR.setVisible(false);
				 Hr.setVisible(true);
			 }
		});
			
		textFieldNR = new JTextField();
		textFieldNR.setBounds(172, 90, 107, 26);
		frameCR.getContentPane().add(textFieldNR);
		textFieldNR.setColumns(10);
		
		textFieldV = new JTextField();
		textFieldV.setBounds(172, 140, 192, 26);
		frameCR.getContentPane().add(textFieldV);
		textFieldV.setColumns(10);
		
		textFieldCap = new JTextField();
		textFieldCap.setBounds(172, 192, 74, 26);
		frameCR.getContentPane().add(textFieldCap);
		textFieldCap.setColumns(10);
		
		textFieldCi = new JTextField();
		textFieldCi.setBounds(172, 244, 143, 29);
		frameCR.getContentPane().add(textFieldCi);
		textFieldCi.setColumns(10);
		
		JLabel nomeRist = new JLabel("Nome Ristorante");
		nomeRist.setHorizontalAlignment(SwingConstants.LEFT);
		nomeRist.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		nomeRist.setBounds(22, 90, 140, 26);
		frameCR.getContentPane().add(nomeRist);
		
		JLabel via = new JLabel("Via");
		via.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		via.setHorizontalAlignment(SwingConstants.LEFT);
		via.setBounds(22, 140, 90, 26);
		frameCR.getContentPane().add(via);
		
		JLabel cap = new JLabel("CAP");
		cap.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		cap.setHorizontalAlignment(SwingConstants.LEFT);
		cap.setBounds(22, 192, 90, 26);
		frameCR.getContentPane().add(cap);
		
		JLabel citta = new JLabel("Citta");
		citta.setHorizontalAlignment(SwingConstants.LEFT);
		citta.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		citta.setBounds(22, 244, 90, 29);
		frameCR.getContentPane().add(citta);
		
		JLabel lblOrarioChiusura = new JLabel("Orario Apertura");
		lblOrarioChiusura.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblOrarioChiusura.setHorizontalAlignment(SwingConstants.LEFT);
		lblOrarioChiusura.setBounds(22, 362, 125, 26);
		frameCR.getContentPane().add(lblOrarioChiusura);
		
		JLabel lblApertura = new JLabel("Orario Chiusura");
		lblApertura.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblApertura.setHorizontalAlignment(SwingConstants.CENTER);
		lblApertura.setBounds(262, 362, 125, 26);
		frameCR.getContentPane().add(lblApertura);
		
		SpinnerModel model = new SpinnerNumberModel(6, 6, 24, 1);
		JSpinner spinnerOrarioA = new JSpinner(model);
		spinnerOrarioA.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinnerOrarioA.setBounds(152, 362, 90, 26);
		frameCR.getContentPane().add(spinnerOrarioA);
		
		SpinnerModel model2 = new SpinnerNumberModel(6, 6, 24, 1);
		JSpinner spinnerOrarioC = new JSpinner(model2);
		spinnerOrarioC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinnerOrarioC.setBounds(393, 362, 90, 26);
		frameCR.getContentPane().add(spinnerOrarioC);
		
		JLabel lblCapienza = new JLabel("Capienza");
		lblCapienza.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblCapienza.setHorizontalAlignment(SwingConstants.LEFT);
		lblCapienza.setBounds(22, 300, 90, 25);
		frameCR.getContentPane().add(lblCapienza);
		
		textFieldCapienza = new JTextField();
		textFieldCapienza.setBounds(172, 300, 86, 25);
		frameCR.getContentPane().add(textFieldCapienza);
		textFieldCapienza.setColumns(10);
		
		JLabel lblSped = new JLabel("Accetti spedizioni?");
		lblSped.setHorizontalAlignment(SwingConstants.LEFT);
		lblSped.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblSped.setBounds(22, 420, 143, 26);
		frameCR.getContentPane().add(lblSped);	

		JButton btnNewButton = new JButton("Inserisci");
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(393, 471, 107, 35);
		frameCR.getContentPane().add(btnNewButton);
		
		//Questo bottone crea un ristorante associato al ristoratore corrente e lo inserisce nel database.
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int check=0;
				//La variabile check è utilizzata per inserire nel database l'informazione che il ristoratore accetti spedizioni oppure no
				if(rdbtnS.isSelected()==true) {
					check = 1;
				}		
				if(ControllerInserimento.eUnNumero(textFieldCap.getText()) && !ControllerInserimento.eVuota(textFieldNR.getText()) && !ControllerInserimento.eVuota(textFieldV.getText()) && !ControllerInserimento.eVuota(textFieldCi.getText()) && (rdbtnS.isSelected()== true || rdbtnN.isSelected()==true)) {
					Ristorante R = new Ristorante(textFieldNR.getText(),textFieldV.getText(),Integer.parseInt(textFieldCap.getText()),textFieldCi.getText(),Integer.parseInt(textFieldCapienza.getText()),(Integer)spinnerOrarioA.getValue(),(Integer)spinnerOrarioC.getValue(),HomePage.getUsername(),check);
					D.inserisciRist(R);
					JOptionPane.showMessageDialog(frameCR, "Ristorante creato con successo", null, 0, iconaSuccessoRist);
					HomeRistoratore Ri = new HomeRistoratore();
					Ri.setVisible(true);
					frameCR.dispose();
				}else 
					JOptionPane.showMessageDialog(frameCR, "Il CAP deve essere un numero | Tutti i campi devono essere riempiti", null, 0, iconaErrore);
			}
		});	
	
	}
	
	public void setVisible(boolean bool) {
		
		try {
				if (bool == true) {
					CreaRistorante window = new CreaRistorante();
					window.frameCR.setVisible(true);
				} else {
					CreaRistorante window = new CreaRistorante();
					window.frameCR.setVisible(false);
				}	
		}catch (Exception e) {	
			 	e.printStackTrace();
		}
	}
	
}
