package login;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.swing.*;
import classidb.Database;
import classidb.Utente;
import javax.swing.ImageIcon;
import viewHomePage.ControllerInserimento;
import viewHomePage.HomePage;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;

/***************************************************************
 * Questa classe viene invocata al momento della registrazione.*
 ***************************************************************/
public class SignIn {
	
	 private JFrame frameSignIn;
	 private JTextField textFieldNome;
	 private JTextField textFieldCognome;
	 private JTextField textFieldUsername;
	 private JTextField textFieldPassword;
	 private JTextField textFieldRipPass;

	 public SignIn(){
		 initialize();
	 }
	
	 private void initialize() {
		
		 Database D = Database.getIstance();
		 ImageIcon iconaSuccesso = new ImageIcon(HomePage.class.getResource("/images/succRist.png"));
		 ImageIcon iconaErrore = new ImageIcon(HomePage.class.getResource("/images/iconaErrore.jpg"));

		 
		 frameSignIn = new JFrame();
		 frameSignIn.getContentPane().setBackground(new Color(245, 245, 245));
		 frameSignIn.setBounds(100, 100, 588, 491);
		 frameSignIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frameSignIn.getContentPane().setLayout(null);

		 
		 JLabel labelNome = new JLabel("Nome");
		 labelNome.setFont(new Font("Eras Bold ITC", Font.PLAIN, 16));
		 labelNome.setBounds(20, 83, 100, 28);
		 frameSignIn.getContentPane().add(labelNome);

		 
		 JLabel labelCognome = new JLabel("Cognome");
		 labelCognome.setFont(new Font("Eras Bold ITC", Font.PLAIN, 16));
		 labelCognome.setBounds(20, 125, 100, 27);
		 frameSignIn.getContentPane().add(labelCognome);

		 
		 JLabel labelUsername = new JLabel("Username");
		 labelUsername.setFont(new Font("Eras Bold ITC", Font.PLAIN, 16));
		 labelUsername.setBounds(20, 166, 100, 26);
		 frameSignIn.getContentPane().add(labelUsername);
		 
		 
		 JLabel labelPassword = new JLabel("Password");
		 labelPassword.setFont(new Font("Eras Bold ITC", Font.PLAIN, 16));
		 labelPassword.setBounds(20, 203, 100, 28);
		 frameSignIn.getContentPane().add(labelPassword);

		 
		 textFieldNome = new JTextField();
		 textFieldNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		 textFieldNome.setBounds(170, 84, 219, 28);
		 frameSignIn.getContentPane().add(textFieldNome);
		 textFieldNome.setColumns(10);


		 textFieldCognome = new JTextField();
		 textFieldCognome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		 textFieldCognome.setBounds(170, 125, 219, 26);
		 frameSignIn.getContentPane().add(textFieldCognome);
		 textFieldCognome.setColumns(10);

		 
		 textFieldUsername = new JTextField();
		 textFieldUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		 textFieldUsername.setBounds(170, 165, 219, 26);
		 frameSignIn.getContentPane().add(textFieldUsername);
		 textFieldUsername.setColumns(10);


		 textFieldPassword = new JPasswordField();
		 textFieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		 textFieldPassword.setBounds(170, 205, 219, 26);
		 frameSignIn.getContentPane().add(textFieldPassword);
		 textFieldPassword.setColumns(10);


		 JCheckBox checkPass = new JCheckBox("Mostra password");
		 checkPass.setFont(new Font("Eras Bold ITC", Font.PLAIN, 16));
		 checkPass.setBounds(399, 208, 200, 20);
		 frameSignIn.getContentPane().add(checkPass);
		 
		 checkPass.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if (checkPass.isSelected()) {
					 ((JPasswordField) textFieldPassword).setEchoChar((char) 0);
				 }else{
					 ((JPasswordField) textFieldPassword).setEchoChar('●');
				 }
		 	 }		
		 });


		 JButton btnAnnulla = new JButton("Annulla");
		 btnAnnulla.setForeground(Color.WHITE);
		 btnAnnulla.setBackground(Color.BLACK);
		 btnAnnulla.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		 btnAnnulla.setBounds(20, 392, 117, 37);
		 frameSignIn.getContentPane().add(btnAnnulla);
		
		 btnAnnulla.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e) {
				 HomePage Ho = new HomePage();
				 frameSignIn.setVisible(false);
				 Ho.setVisible(true);
			 }
		 });


		 JLabel labelRipPass = new JLabel("Ripeti Password");
		 labelRipPass.setFont(new Font("Eras Bold ITC", Font.PLAIN, 16));
		 labelRipPass.setBounds(20, 245, 135, 26);
		 frameSignIn.getContentPane().add(labelRipPass);
		 
		 
		 textFieldRipPass = new JPasswordField();
		 textFieldRipPass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		 textFieldRipPass.setBounds(170, 245, 219, 26);
		 frameSignIn.getContentPane().add(textFieldRipPass);
		 textFieldRipPass.setColumns(10);
		
		 
		 JLabel labelRuolo = new JLabel("Sei un ristoratore ?");
		 labelRuolo.setFont(new Font("Eras Bold ITC", Font.PLAIN, 16));
		 labelRuolo.setBounds(20, 302, 158, 26);
		 frameSignIn.getContentPane().add(labelRuolo);		 
		 
		 
		 JCheckBox checkRuolo = new JCheckBox();
		 checkRuolo.setBounds(184,302,21,26);
		 frameSignIn.getContentPane().add(checkRuolo);
		 
		 
		 JLabel labelRuolo1 = new JLabel("Sei un cliente ?");
		 labelRuolo1.setFont(new Font("Eras Bold ITC", Font.PLAIN, 16));
		 labelRuolo1.setBounds(20, 336, 135, 26);
		 frameSignIn.getContentPane().add(labelRuolo1);		 

		 
		 JCheckBox checkRuolo1 = new JCheckBox();
		 checkRuolo1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		 checkRuolo1.setBounds(170,342,21,20);
		 frameSignIn.getContentPane().add(checkRuolo1);
		 
		 
		 JLabel lblNewLabel = new JLabel("Registrati");
		 lblNewLabel.setForeground(Color.BLACK);
		 lblNewLabel.setFont(new Font("Script MT Bold", Font.BOLD, 30));
		 lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		 lblNewLabel.setBounds(195, 11, 176, 37);
		 frameSignIn.getContentPane().add(lblNewLabel);
		 
		 
		 JButton bottoneSignIn = new JButton("Crea");
		 bottoneSignIn.setForeground(Color.WHITE);
		 bottoneSignIn.setBackground(Color.BLACK);
		 bottoneSignIn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		 bottoneSignIn.setBounds(433, 392, 117, 37);
		 frameSignIn.getContentPane().add(bottoneSignIn);
		 bottoneSignIn.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 
				 if (textFieldPassword.getText().equals(textFieldRipPass.getText())) {
					 if (!ControllerInserimento.eVuota(textFieldUsername.getText())
						 && !ControllerInserimento.eVuota(textFieldPassword.getText())
						 && !ControllerInserimento.eVuota(textFieldRipPass.getText())){
					 
						//controllo sui checkBox selezionati	 
						 if((!(checkRuolo.isSelected()&&checkRuolo1.isSelected()) && (!(checkRuolo.isSelected()==false && checkRuolo1.isSelected()==false)))){
							 
							int ruolo=0;
							
							//Se è selezionato il checkbox del ristoratore significa che mi sto registrando come quest'ultimo.
							if(checkRuolo.isSelected()) {
									ruolo=1;
							}

							try {
									Utente U = new Utente(textFieldNome.getText(), textFieldCognome.getText(),
									textFieldUsername.getText(), textFieldPassword.getText(), ruolo);
									D.createUser(U);
									JOptionPane.showMessageDialog(frameSignIn, "Utente Creato", null, 0, iconaSuccesso);
									HomePage h = new HomePage();
									frameSignIn.setVisible(false);
									h.setVisible(true);
							}catch (SQLIntegrityConstraintViolationException s) {
									JOptionPane.showMessageDialog(frameSignIn, "Username esistente", null, 0,iconaErrore);
				
							}catch (SQLException e1) {
									JOptionPane.showMessageDialog(frameSignIn, "Errore Generico!", null, 0,iconaErrore);
				
							}catch (HeadlessException e1) {
									JOptionPane.showMessageDialog(frameSignIn, "Errore Generico!", null, 0, iconaErrore);
				
							}
			
			
						}else {
							JOptionPane.showMessageDialog(frameSignIn, "Devi selezionare un checkbox",null, 0, iconaErrore);
						}
	
					 } else {
						 JOptionPane.showMessageDialog(frameSignIn, "Username o Password non può essere vuoto!",null, 0, iconaErrore);			
					 }
					 
				 } else {
					 JOptionPane.showMessageDialog(frameSignIn, "Le Password non coincidono!", null, 0, iconaErrore);
				 }
			 }
		 });
		 
	 }
	
	 
	 public void setVisible(boolean bool) {

		 try {
				if (bool == true) {
					SignIn window = new SignIn();
					window.frameSignIn.setVisible(true);
				} else {
					SignIn window = new SignIn();
					window.frameSignIn.setVisible(false);		
				}
		 }catch (Exception e){
			 	e.printStackTrace();
		 }

	 }

}
