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
import classidb.*;
import viewHomePage.*;
import javax.swing.JTextArea;

/****************************************************************************
 * Questa classe serve per modificare un piatto selezionato dal ristoratore *
 ****************************************************************************/
public class ModificaPiatto extends JFrame {

	private JFrame frameMP;
	private JTextField textField_1;
	private JTextField textField_3;
	String[] tipo;
	
	public ModificaPiatto(){
			initialize();
	}
	
	public void initialize(){
				
        ImageIcon iconaErrore = new ImageIcon(HomePage.class.getResource("/images/iconaErrore.jpg"));
        ImageIcon iconaDomanda = new ImageIcon(HomePage.class.getResource("/images/ShowMessag.jpg"));
		Database D = Database.getIstance();
		
		frameMP = new JFrame();
		frameMP.getContentPane().setBackground(new Color(245, 245, 245));
		frameMP.getContentPane().setForeground(Color.WHITE);
		frameMP.setResizable(false);
		frameMP.setTitle("Modifica Piatto");
		frameMP.setBounds(0, 0, 500, 500);
		frameMP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMP.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Modifica Piatto");
		lblNewLabel.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(148, 11, 189, 45);
		frameMP.getContentPane().add(lblNewLabel);
		 
		JComboBox comboBox = new JComboBox(D.elencoPiatti(HomePage.getUsername()).toArray());
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setBounds(24, 78, 444, 29);
		frameMP.getContentPane().add(comboBox);
		 
		JLabel lblNewLabel_1 = new JLabel("Inserisci nome");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblNewLabel_1.setBounds(24, 167, 117, 29);
		frameMP.getContentPane().add(lblNewLabel_1);
		 
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_1.setBounds(197, 167, 168, 30);
		frameMP.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		 
		JLabel lblNewLabel_2 = new JLabel("Inserisci descrizione");
		lblNewLabel_2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setBounds(24, 239, 161, 45);
		frameMP.getContentPane().add(lblNewLabel_2);
		 
		JLabel lblNewLabel_3 = new JLabel("Inserisci prezzo");
		lblNewLabel_3.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setBounds(24, 325, 117, 30);
		frameMP.getContentPane().add(lblNewLabel_3);
		 
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_3.setBounds(197, 326, 117, 30);
		frameMP.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		 
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textArea.setBounds(198, 239, 167, 45);
		frameMP.getContentPane().add(textArea);
		 
		JButton btnNewMod = new JButton("Modifica");
		btnNewMod.setBackground(Color.BLACK);
		btnNewMod.setForeground(Color.WHITE);
		btnNewMod.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewMod.setBounds(332, 409, 129, 38);
		frameMP.getContentPane().add(btnNewMod);
		
		//Questo bottone permette di modificare un piatto selezionato.
		btnNewMod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ControllerInserimento.eUnNumero(textField_3.getText()) && !ControllerInserimento.eVuota(textField_1.getText()) && !ControllerInserimento.eVuota(textArea.getText())) {
					Piatto VecchiaP = new Piatto(D.elencoPiatti(HomePage.getUsername()).get(comboBox.getSelectedIndex()));
					Piatto NuovaP = new Piatto(textField_1.getText(), textArea.getText(), Float.parseFloat(textField_3.getText()), HomePage.getUsername());
					//Si inseriscono i vecchi e i nuovi valori del piatto corrente.
					D.modificaPiatto(VecchiaP, NuovaP, HomePage.getUsername());				
					int scelta = JOptionPane.showConfirmDialog(frameMP, "Piatto modificato. Vuoi modificare un altro piatto ?", null, JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, iconaDomanda);
					if(scelta == JOptionPane.NO_OPTION) {
						frameMP.setVisible(false);
						ModificaPiatto.this.dispose();
						HomeRistoratore MenuR = new HomeRistoratore();
						MenuR.setVisible(true);					
					}else if (scelta == JOptionPane.CANCEL_OPTION) {
						
					}else if (scelta == JOptionPane.YES_OPTION) {
						textField_1.setText("");
						textField_3.setText("");
						textArea.setText("");
					}			
				} else {
				JOptionPane.showMessageDialog(frameMP, "Il costo deve essere un numero | Nome- Descrizione piatto non puo' essere vuoto", null, 0, iconaErrore);
				}
			}
		});
		 	
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBackground(Color.BLACK);
		btnAnnulla.setForeground(Color.WHITE);
		btnAnnulla.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAnnulla.setBounds(24, 409, 117, 38);
		frameMP.getContentPane().add(btnAnnulla);
		
		btnAnnulla.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 HomeRistoratore Hr = new HomeRistoratore();
				 frameMP.setVisible(false);
				 Hr.setVisible(true);
			 }
		});
		
	}


	public void setVisible(boolean bool) {
		 try {
				if (bool == true) {
					ModificaPiatto window = new ModificaPiatto();
					window.frameMP.setVisible(true);
				} else {
					ModificaPiatto window = new ModificaPiatto();
					window.frameMP.setVisible(false);
				}
		 } catch (Exception e) {
			 	e.printStackTrace();
		 }
	}
	
}