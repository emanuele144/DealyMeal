package viewCliente;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableColumn;
import classidb.Database;
import viewHomePage.HomePage;

/*************************************************************************
 * Questa classe serve per visualizzare le prenotazioni effettuate da un *
 * determinato cliente,						 							 *
 *************************************************************************/
public class VisualizzaSpedizioni{

	private JFrame frameVS;
	private JTable table_1;
	JScrollPane scrollPane = null;
		
	public VisualizzaSpedizioni() {
		initialize();
	}
		
		
	public void initialize(){
			
		Database D = Database.getIstance();
		
		frameVS = new JFrame();
		frameVS.getContentPane().setBackground(new Color(245, 245, 245));
		frameVS.setResizable(false);
		frameVS.setTitle("Spedizioni");
		frameVS.setBounds(0, 0, 774, 406);
		frameVS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameVS.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Le tue spedizioni");
		lblNewLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 32));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(228, 11, 285, 56);
		frameVS.getContentPane().add(lblNewLabel);	
	 
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 78, 734, 203);
		frameVS.getContentPane().add(scrollPane_1);
		table_1 = new JTable(D.mostraSpedizioniC(HomePage.getUsername())) {
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
		
		 
	    JButton btnAnnulla = new JButton("Annulla");
	    btnAnnulla.setBackground(Color.BLACK);
	    btnAnnulla.setForeground(Color.WHITE);
	    btnAnnulla.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAnnulla.setBounds(305, 316, 125, 39);
		frameVS.getContentPane().add(btnAnnulla);
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Profilo Pf = new Profilo();
				frameVS.setVisible(false);
				Pf.setVisible(true);
			}
		});
	}


	public void setVisible(boolean bool) {
	
		try {
				if (bool == true) {
					VisualizzaSpedizioni window = new VisualizzaSpedizioni();
						window.frameVS.setVisible(true);
					 }else{
						 VisualizzaSpedizioni window = new VisualizzaSpedizioni();
						window.frameVS.setVisible(false);
					 }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}