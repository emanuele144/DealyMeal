package viewHomePage;
import login.*;
import viewRistoratore.*;
import viewCliente.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

/****************************************************************************
 * Questa classe serve per effettuare il login e controlla se l'utente è un *
 * cliente o un ristoratore.								 			    * 
 ****************************************************************************/
public class HomePage{

	private JFrame frameHome;
	private static JTextField textFieldUsername;
	private static JTextField textFieldPassword;

	//Lancia l'applicazione.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage window = new HomePage();
					window.frameHome.setVisible(true);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	//Inizializza l'homepage
	public HomePage() {
		initialize();
	}


	//Inizializza il contenuto del frame
	private void initialize() {
		
		ImageIcon iconaSuccessoRist = new ImageIcon(HomePage.class.getResource("/images/succRist.png"));
		ImageIcon iconaSuccessoCli = new ImageIcon(HomePage.class.getResource("/images/succCli.jpg"));
        ImageIcon iconaErrore = new ImageIcon(HomePage.class.getResource("/images/iconaErrore.jpg"));
        
		frameHome = new JFrame();
		frameHome.setTitle("Home");
		frameHome.setResizable(false);
		frameHome.setBounds(100, 100, 453, 380);
		frameHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameHome.getContentPane().setLayout(null);
		
		
		JLabel labelLogin = new JLabel("Login");
		labelLogin.setBackground(new Color(255, 255, 255));
		labelLogin.setForeground(new Color(0, 0, 0));
		labelLogin.setHorizontalAlignment(SwingConstants.CENTER);
		labelLogin.setFont(new Font("Script MT Bold", Font.BOLD, 39));
		labelLogin.setBounds(179, 0, 95, 57);
		frameHome.getContentPane().add(labelLogin);
		
		JButton bottoneLogin = new JButton("Entra");
		bottoneLogin.setBackground(Color.WHITE);
		bottoneLogin.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		//Questo bottone serve per far entrare l'utente nell'applicazione e controlla se è un ristoratore , un utente oppure che abbia
		//sbagliato l'inserimento delle credenziali.
		bottoneLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login L = Login.getIstanced();
	
				if(L.autenticazione(textFieldUsername.getText(), textFieldPassword.getText()) == 1) {
						JOptionPane.showMessageDialog(frameHome, "Benvenuto Ristoratore", null, 0, iconaSuccessoRist);
						HomeRistoratore R = new HomeRistoratore();
						R.setVisible(true);
						frameHome.dispose();
				}else if(L.autenticazione(textFieldUsername.getText(), textFieldPassword.getText()) == 2) {
						JOptionPane.showMessageDialog(frameHome, "Benvenuto Cliente", null, 0, iconaSuccessoCli);
						HomeCliente C = new HomeCliente();
						C.setVisible(true);
						frameHome.dispose();
				}else if((L.autenticazione(textFieldUsername.getText(), textFieldPassword.getText()) == -1))
					JOptionPane.showMessageDialog(frameHome, "Username o password errata", null, 0, iconaErrore);
				
			}
			
		});


		bottoneLogin.setBounds(298, 284, 117, 40);
		frameHome.getContentPane().add(bottoneLogin);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldUsername.setBounds(155, 112, 169, 26);
		frameHome.getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		
		textFieldPassword = new JPasswordField(30);
		textFieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldPassword.setBounds(155, 174, 169, 26);
		frameHome.getContentPane().add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		JLabel labelUsername = new JLabel("Username:");
		labelUsername.setBackground(new Color(253, 245, 230));
		labelUsername.setForeground(new Color(0, 0, 0));
		labelUsername.setFont(new Font("Eras Bold ITC", Font.PLAIN, 16));
		labelUsername.setBounds(35, 109, 95, 31);
		frameHome.getContentPane().add(labelUsername);
		
		JLabel labelPassword = new JLabel("Password:");
		labelPassword.setForeground(new Color(0, 0, 0));
		labelPassword.setFont(new Font("Eras Bold ITC", Font.PLAIN, 16));
		labelPassword.setBounds(35, 173, 95, 26);
		frameHome.getContentPane().add(labelPassword);
		 
		JButton btnSingIn = new JButton ("Registrati");
		btnSingIn.setBackground(Color.WHITE);
		btnSingIn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		btnSingIn.setBounds(28, 284, 117, 40);
		frameHome.getContentPane().add(btnSingIn);
		 
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setIcon(new ImageIcon(HomePage.class.getResource("/images/Logo.jpg")));
		lblNewLabel_1.setBounds(0, 0, 444, 351);
		frameHome.getContentPane().add(lblNewLabel_1);
		btnSingIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignIn Si = new SignIn();
				Si.setVisible(true);
				frameHome.setVisible(false);
			}

		});
	}
	
	public static String getUsername() {
		return textFieldUsername.getText();
	}
	
	public static String getPassword() {
		return textFieldPassword.getText();
	}
	
	public void setVisible(boolean bool) {
		
		try {
			if (bool == true) {
				HomePage window = new HomePage();
				window.frameHome.setVisible(true);

			}else{
				HomePage window = new HomePage();
				window.frameHome.setVisible(false);
			}
		
		}catch (Exception e) {
				e.printStackTrace();

		}

	}
}
