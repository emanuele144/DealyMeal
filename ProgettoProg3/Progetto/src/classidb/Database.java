package classidb;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import decoratorMenu.Menu;
import java.util.*;

/***********************************************************************
 * Questa classe serve per far interagire il programma con il database *
 **********************************************************************/
public class Database {
	
    // Definisco pubbliche le variabili di connessione al database
    public Connection conn;
    public Statement stmt;
    public PreparedStatement pstmt;
    public ResultSet rs;
    public ResultSet rs1;
    
    private static Database istance = null; // Utilizzo del design pattern Singleton
    // Inizializzo l'instanza della classe privata per essere accessibile solo dal metodo getIstance
 
    //Costruttore della classe che richiama il database
    protected Database() {
        try{
               Class.forName("com.mysql.cj.jdbc.Driver");
            	
        }catch (ClassNotFoundException e){
            	e.printStackTrace();
        }
    }

    //Metodo per inizializzare o passare l'istanza della classe se già inizializzata
    public static Database getIstance() {
        if (istance == null) // Controllo se la classe ancora non è stata istanziata
            	istance = new Database(); // Se non è stata istanziata, instanziala
        return istance; // Ritorna l'istanza
    }


    // Apro connessioni al db
    public void openConnection() {
        try{
	            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	                    "root", "1234");
	            stmt = conn.createStatement();
	            
        } catch (SQLException s){
            	System.out.println(s);
        }
    }
 

    // Chiudo connessioni al db
    public void closeConnection() {
        try{
	            conn.close();
	            stmt.close();

        }catch(SQLException s){
            	System.out.println(s);
        }
    }
    
    
    //Aggiungo un nuovo utente al database
    public void createUser (Utente U) throws SQLIntegrityConstraintViolationException, SQLException
    {

    	try {
		    	Class.forName("com.mysql.jdbc.Driver");
		    	 
		    	openConnection();
		    	 
		    	stmt.executeUpdate("INSERT INTO `dbristorante`.`utente` (`nome`,`cognome`,`username`,`password`,`ruolo`) VALUES ('"
		    	 + U.getNome() + "','" + U.getCognome() + "','" + U.getUsername() + "','" + U.getPassword() +  "' ,'" + U.getRuolo() + "' );");
		
		    	closeConnection();

    	} catch (SQLIntegrityConstraintViolationException s) {

	    		System.out.println(s);
	
	    		throw (s);

    	} catch (ClassNotFoundException e) {

    		 	e.printStackTrace();

    	} catch (SQLException e) {

    		 	throw (e);	 

    	}
    }
        
        
	//Inserisce un piatto nel database
	public int inserisciPiatto(Piatto P, String ristoratore) {
		
		try{
			  	Class.forName("com.mysql.jdbc.Driver");
	            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	                    "root", "1234");
	            stmt = conn.createStatement();
	            
	            stmt.executeUpdate("INSERT INTO `dbristorante`.`piatto` (`nomeP`, `descrizione`, `prezzo`,`tipologia`,`ristoratore`) VALUES ('"+P.getNomePiatto()+"', '"+P.getDescrizione()+"', '"+P.getPrezzo()+"', '"+P.getTipologia()+"', '"+ristoratore+"');");
	            
		}catch(ClassNotFoundException e) {
		        System.out.println(e);
		}
		catch(SQLException e)
		{
		        System.out.println(e);
		}
		
		return 0;   
	}
    	
	//Inserisce una bibita nel database.
	public int inserisciBibita(Bibita B, String nomeBibita, float prezzo, String ristoratore,String tipologia) {
		
		try{
			  	Class.forName("com.mysql.jdbc.Driver");
	            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	                    "root", "1234");
	            stmt = conn.createStatement();
	            
	            stmt.executeUpdate("INSERT INTO `dbristorante`.`aggiunta` (`prezzo`, `nomeBibita`,`usernameRa`,`tipologia`) VALUES ('"+B.getPrezzo()+"', '"+B.getNomeBibita()+"', '"+ristoratore+"' , '"+B.getTipologia()+"');");
	            
		}catch(ClassNotFoundException e) {
		        System.out.println(e);
		}
		catch(SQLException e)
		{
		        System.out.println(e);
		}
		
		return 0;   
	}
    	
	//Inserisce un menu nel database
	public int inserisciMenu(Menu M, String username, float prezzo) {
    		
    	try{
    		  	Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "1234");
                stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO `dbristorante`.`menu` (`giorno`,`usernameRi`,`prezzo`) VALUES ('"+M.getGiorno()+"', '"+username+"', '"+prezzo+"');");
       
    	}catch(ClassNotFoundException e) {
    	        System.out.println(e);
    	}
    	catch(SQLException e)
    	{
    	        System.out.println(e);
    	}
    	return 0;
    }
    	
    
	//Inserisce nel database una tupla nella relazione tra menu e piatto
    public int inserisciContiene(Menu M, String username, String NomeP) {
    	
    	try{
    		  	Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "1234");
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT idPiatto FROM piatto WHERE nomeP= '"+ NomeP +"' AND ristoratore= '"+ username +"' ");
                rs.next();
                int idPiatto2 = rs.getInt("idPiatto");
                rs.close();
                rs1 = stmt.executeQuery("SELECT idmenu FROM menu WHERE giorno= '"+ M.getGiorno() +"' AND usernameRi= '"+ username +"' ");
                rs1.next();
                stmt.executeUpdate("INSERT INTO `dbristorante`.`contiene` (`idmenu`,`idPiatto`) VALUES ('"+rs1.getInt("idmenu")+"','"+idPiatto2+"');");
              
    	}catch(ClassNotFoundException e) {
    	        System.out.println(e);
    	}
    	catch(SQLException e)
    	{
    	        System.out.println(e);
    	}
    	
    	return 0;
    }
    	
    
    //Creo/visualizzo una lista di piatti dato un tipo e un ristoratore, serve per la view CreaMenu
    public ArrayList <Piatto> elencoPiatti(String tipo, String ristoratore) {
    	
    	ArrayList <Piatto> elencoPiatti = new ArrayList <Piatto>();
    		
    	try {
    			
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "1234");
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT nomeP FROM piatto WHERE tipologia='"+ tipo +"' AND ristoratore='"+ ristoratore +"'");
                while (rs.next()==true) {
                	Piatto P = new Piatto(rs.getString("nomeP"));
                	elencoPiatti.add(P);
                }
                
    	}catch(SQLException e){
    	        System.out.println(e);
    	}
    	return elencoPiatti;
    }
    	
    
    //Creo/visualizzo una lista di piatti di un determinato ristoratore, serve per la view ModificaPiatto
    public ArrayList <Piatto> elencoPiatti(String ristoratore) {
    	
    	ArrayList <Piatto> elencoPiatti = new ArrayList <Piatto>();
    	
    	try {
    			
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "1234");
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT nomeP FROM piatto WHERE ristoratore='"+ ristoratore +"'");
                while (rs.next()==true) {
                	Piatto P = new Piatto(rs.getString("nomeP"));
                	elencoPiatti.add(P);
                }
                
    	}catch(SQLException e){
    	        System.out.println(e);
    	}
    	
    	return elencoPiatti;
    }
    	
    
    //Metodo che ritorna il prezzo di un determinato piatto per un determinato ristoratore
    public float ritornaPrezzo(String nomeP, String username) {
    	
    	float prezzo=0;
    	
    	try{
    		  	Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "1234");
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT prezzo FROM piatto WHERE nomeP='"+ nomeP +"' AND ristoratore='"+ username +"'");
                rs.next();
                prezzo = rs.getFloat("prezzo");
    	 
    	}catch(ClassNotFoundException e) {
    	        System.out.println(e);
    	}
    	catch(SQLException e)
    	{
    	        System.out.println(e);
    	}
    		
    		
    	return prezzo;
    }
    	
    
    
  //Metodo che ritorna il prezzo di una determinata bibita per un determinato ristoratore
    public float ritornaPrezzoBibita(String nomeBibita, String username) {
    	
    	float prezzo=0;
    	
    	try{
    		  	Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "1234");
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT prezzo FROM aggiunta WHERE nomeBibita='"+nomeBibita+"' AND usernameRa='"+username+"'");
                rs.next();
                prezzo = rs.getFloat("prezzo");
    	 
    	}catch(ClassNotFoundException e) {
    	        System.out.println(e);
    	}
    	catch(SQLException e)
    	{
    	        System.out.println(e);
    	}
    		
    		
    	return prezzo;
    }
    
    //Metodo che ritorna il prezzo di un menu dato un giorno e un ristoratore.
    public float ritornaPrezzoM(String giorno, String username) {
        
    	float prezzo=0;
    	
        try{
              	
        		Class.forName("com.mysql.jdbc.Driver");
              	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "1234");
              	stmt = conn.createStatement();
              	rs = stmt.executeQuery("SELECT prezzo FROM menu WHERE giorno='"+giorno+"' AND usernameRi='"+ username +"'");
              	rs.next();
              	prezzo = rs.getFloat("prezzo");
        
        }catch(ClassNotFoundException e) {
            	System.out.println(e);
        }
        catch(SQLException e)
        {
            	System.out.println(e);
        }
    
        return prezzo;
    }
    
    
    //Metodo che modifica un piatto nel database.
    public void modificaPiatto(Piatto VecchiaP, Piatto NuovaP, String ristoratore) {
    		
    	String tipologia;
   		 
    	try{
   			  	Class.forName("com.mysql.jdbc.Driver");
   	            stmt = conn.createStatement();
   	            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "1234");
   	            rs = stmt.executeQuery("SELECT tipologia FROM `dbristorante`.`piatto` WHERE (`nomeP` = '" + VecchiaP.getNomePiatto() + "' AND `ristoratore` = '" + ristoratore + "');");
   	            rs.next();
   	            tipologia = rs.getString("tipologia");
   	            stmt.executeUpdate("UPDATE piatto SET nomeP ='"+NuovaP.getNomePiatto()+"', descrizione= '"+NuovaP.getDescrizione()+"', prezzo= '"+NuovaP.getPrezzo()+"', tipologia= '"+tipologia+"', ristoratore= '"+ristoratore+"' WHERE nomeP= '" + VecchiaP.getNomePiatto() + "' AND ristoratore= '" + ristoratore + "'");
   		 }catch(Exception e){
   			 	System.out.println("\nERRORE: \n+ "+e+"\n");
   		 }
    }
    	
      	
    //Costruisce e ritorna la tabella di un menu di un determinato giorno e ristoratore
    public DefaultTableModel mostraMenu(String giorno, String username) {
    		
    	Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    		
    	Vector<String> columnNames = new Vector<String>();
    		
    	try {
    			/******************************************
    			 * Inizio con la connessione al Database. *
    			 ******************************************/
    			openConnection();

    			// Qui prendo il risultato della query e ne estraggo i dati
    			rs = stmt.executeQuery("SELECT tipologia, nomeP, descrizione, p.prezzo, m.idmenu FROM dbristorante.menu m join dbristorante.contiene c on m.idmenu=c.idmenu join dbristorante.piatto p on p.idPiatto=c.idPiatto  WHERE (`giorno` = '"+giorno+"' AND `usernameRi` = '"+username+"');");
    			
    			ResultSetMetaData metaData = rs.getMetaData();

    			// Dai dati prendo i nomi delle colonne
    			int columnCount = metaData.getColumnCount();
    			
    			//Aggiunge i nomi delle colonne della tabella presa precedentemente alla nuova tabella
    			for (int column = 1; column <= columnCount; column++) {
    				columnNames.add(metaData.getColumnName(column));
    			}

    			//Aggiunge le tuple alla tabella presa in considerazione.
    			while (rs.next()) {
    				Vector<Object> vector = new Vector<Object>();
    				for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
    					vector.add(rs.getObject(columnIndex));
    				}
    				data.add(vector);
    			}
    			closeConnection();
    		} catch (SQLException e) {
    			System.out.println(e);
    		}
    		//Infine, chiude la connessione e ritorna la tabella creata.
    		return new DefaultTableModel(data, columnNames);
    	}

    	
    	//Metodo che dato un giorno e il nome del ristoratore mostra il menu associato ad essi.
    	public DefaultTableModel mostraMenuC(String nomeRistorante, String giorno) {
    		
    		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    		Vector<String> columnNames = new Vector<String>();
    		
    		try {
	    			/**
	    			 * Inizio con la connessione al Database.
	    			 */
	    			openConnection();
	    			
	    			// Table menu
	    			rs = stmt.executeQuery("SELECT tipologia, nomeP, p.prezzo FROM dbristorante.menu m join dbristorante.contiene c on m.idmenu=c.idmenu join dbristorante.piatto p on p.idPiatto=c.idPiatto join dbristorante.ristorante ri on ri.usernameRist=m.usernameRi  WHERE (`giorno` = '"+giorno+"', `Nome_ristorante` = '"+nomeRistorante+"');");
	    			ResultSetMetaData metaData = rs.getMetaData();
	
	    			// names of columns
	    			int columnCount = metaData.getColumnCount();
	    			for (int column = 1; column <= columnCount; column++) {
	    				columnNames.add(metaData.getColumnName(column));
	    			}
	
	    			while (rs.next()) {
	    				Vector<Object> vector = new Vector<Object>();
	    				for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	    					vector.add(rs.getObject(columnIndex));
	    				}
	    				data.add(vector);
	    			}
	    			closeConnection();
    		} catch (SQLException e) {
    				System.out.println(e);
    		}
    		return new DefaultTableModel(data, columnNames);
    	}
    	
    	
    	//Mostra ristoranti
    	public DefaultTableModel mostraRistoranti() {
    		
    		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    		Vector<String> columnNames = new Vector<String>();
    		
    		try {
	    			/**
	    			 * Inizio con la connessione al Database.
	    			 */
	    			openConnection();
	
	    			// Table menu
	    			rs = stmt.executeQuery("SELECT Nome_ristorante, Via,citta, usernameRist FROM dbristorante.ristorante");
	    			ResultSetMetaData metaData = rs.getMetaData();
	
	    			// names of columns
	    			int columnCount = metaData.getColumnCount();
	    			for (int column = 1; column <= columnCount; column++) {
	    				columnNames.add(metaData.getColumnName(column));
	    			}
	
	    			while (rs.next()) {
	    				Vector<Object> vector = new Vector<Object>();
	    				for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	    					vector.add(rs.getObject(columnIndex));
	    				}
	    				data.add(vector);
	    			}
	    			closeConnection();
    		} catch (SQLException e) {
    			System.out.println(e);
    		}
    		return new DefaultTableModel(data, columnNames);
    	}
    	
    	
    	
    	//Mostra ristoranti che effettuano spedizioni
    	public DefaultTableModel mostraRistorantiSped() {
    		
    		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    		Vector<String> columnNames = new Vector<String>();
    		
    		try {
	    			/**
	    			 * Inizio con la connessione al Database.
	    			 */
	    			openConnection();
	
	    			// Table menu
	    			rs = stmt.executeQuery("SELECT Nome_ristorante, Via, Citta, usernameRist FROM dbristorante.ristorante WHERE esitoR=1");
	    			ResultSetMetaData metaData = rs.getMetaData();
	
	    			int columnCount = metaData.getColumnCount();
	    			for (int column = 1; column <= columnCount; column++) {
	    				columnNames.add(metaData.getColumnName(column));
	    			}
	
	    			while (rs.next()) {
	    				Vector<Object> vector = new Vector<Object>();
	    				for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	    					vector.add(rs.getObject(columnIndex));
	    				}
	    				data.add(vector);
	    			}
	    			closeConnection();
    		} catch (SQLException e) {
    				System.out.println(e);
    		}
    		return new DefaultTableModel(data, columnNames);
    	}
    	
    	
    	//Mostra i piatti che possono essere spediti.
    	public DefaultTableModel mostraPiattiSped(String tipologia, String username) {
    		
    		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    		Vector<String> columnNames = new Vector<String>();
    		
    		try {
	    			openConnection();
	    			rs = stmt.executeQuery("SELECT nomeP,descrizione,prezzo,idPiatto FROM dbristorante.piatto WHERE (tipologia='"+tipologia+"' AND ristoratore='"+ username +"');");
	    			ResultSetMetaData metaData = rs.getMetaData();
	
	    			int columnCount = metaData.getColumnCount();
	    			for (int column = 1; column <= columnCount; column++) {
	    				columnNames.add(metaData.getColumnName(column));
	    			}
	
	    			while (rs.next()) {
	    				Vector<Object> vector = new Vector<Object>();
	    				for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	    					vector.add(rs.getObject(columnIndex));
	    				}
	    				data.add(vector);
	    			}
	    			closeConnection();
    		} catch (SQLException e) {
    				System.out.println(e);
    		}
    		return new DefaultTableModel(data, columnNames);
    	}
    	
    	

    	//Inserisce un ristorante nel database.
    	public int inserisciRist(Ristorante R) {
    		
    		try{
	    		  	Class.forName("com.mysql.jdbc.Driver");
	                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	                        "root", "1234");
	                stmt = conn.createStatement();
	                stmt.executeUpdate("INSERT INTO `dbristorante`.`ristorante` (`Nome_ristorante`,`Via`, `CAP`,`Citta`,`capienza`,`orarioApertura`,`orarioChiusura`,`usernameRist`,`esitoR`) VALUES ('"+R.getNomeRist()+"','"+R.getVia()+"','"+R.getCAP()+"','"+R.getCitta()+"','"+R.getCapienza()+"','"+R.getOrarioA()+"','"+R.getOrarioC()+"','"+R.getristoratore()+"','"+R.getEsitoR()+"');");
	    
	    	}catch(ClassNotFoundException e) {
	    	        System.out.println(e);
	    	}catch(SQLException e)
	    	{
	    	        System.out.println(e);
	    	}
    		
    		return 0;
    	}
    	
    	    	
    	
    	 //Creo/visualizzo una lista di bibite di un determinato ristoratore.
        public ArrayList <Bibita> mostraBibite(String username) {
        	
        	ArrayList <Bibita> elencoBibite = new ArrayList <Bibita>();
        	
        	try {
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                            "root", "1234");
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery("SELECT nomeBibita FROM dbristorante.aggiunta WHERE (`usernameRa` = '"+username+"');");
                    while (rs.next()==true) {
                    	Bibita B = new Bibita(rs.getString("nomeBibita"));
                    	elencoBibite.add(B);
                    }
                    
        	}catch(SQLException e){
        	        System.out.println(e);
        	}
        	
        	return elencoBibite;
        }

        //Metodo che dato il nome della bibita e del ristoratore restituisce la tipologia di quest'ultima.
        public String ritornaTipBibita(String nomeBibita, String username) {
         
	     	String tipologia=null;
	     	
	        try{
	               	
	         		Class.forName("com.mysql.jdbc.Driver");
	               	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	                     "root", "1234");
	               	stmt = conn.createStatement();
	                rs = stmt.executeQuery("SELECT tipologia FROM aggiunta WHERE nomeBibita='"+nomeBibita+"' AND usernameRa='"+username+"'");
	                rs.next();
	               	tipologia = rs.getString("tipologia");
	         
	         }catch(ClassNotFoundException e) {
	             	System.out.println(e);
	         }
	         catch(SQLException e)
	         {
	             	System.out.println(e);
	         }
	     
	         return tipologia;
	    }
     
        //Metodo che dato il nome della bibita e del ristoratore restituisce l'id di quest'ultima.
        public int ritornaIdBibita(String nomeBibita, String username) {
         
	      	 int idAggiunta=0;
	      	
	         try{
	                	
	          		Class.forName("com.mysql.jdbc.Driver");
	                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	                      "root", "1234");
	                stmt = conn.createStatement();
	                rs = stmt.executeQuery("SELECT idAggiunta FROM aggiunta WHERE nomeBibita='"+nomeBibita+"' AND usernameRa='"+username+"'");
	                rs.next();
	                idAggiunta = rs.getInt("idAggiunta");
	          
	         }catch(ClassNotFoundException e) {
	             	System.out.println(e);
	         }
	         catch(SQLException e)
	         {
	              	System.out.println(e);
	         }
	      
	         return idAggiunta;
	    }
     
     
     
      
	    //Metodo che mostra i clienti che si sono prenotati ad un determinato menù.
	    public DefaultTableModel mostraPrenotati(String username, String giorno) {
	 		
	    	 Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	 		 Vector<String> columnNames = new Vector<String>();
	 		 
	 		 try {
		 			
		 			openConnection();
		 			
		 			rs = stmt.executeQuery("SELECT nome, cognome, idMenuP, numeroPrenotati, nomeBibita, importo FROM dbristorante.pagamento p join dbristorante.utente u on u.username=p.usernameCl join dbristorante.menu m on m.idmenu = p.idMenuP join dbristorante.aggiunta a on a.idaggiunta=p.idAggiunta WHERE giorno = '"+giorno+"' AND usernameRi = '"+username+"'");
		 			ResultSetMetaData metaData = rs.getMetaData();
		 			
		 			int columnCount = metaData.getColumnCount();
		 			for (int column = 1; column <= columnCount; column++) {
		 				columnNames.add(metaData.getColumnName(column));
		 			}
		
		 			while (rs.next()) {
		 				Vector<Object> vector = new Vector<Object>();
		 				for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		 					vector.add(rs.getObject(columnIndex));
		 				}
		 				data.add(vector);
		 			}
		 			closeConnection();
	 		 }catch (SQLException e) {
	 			 	System.out.println(e);
	 		 }
	 		 
	 		 return new DefaultTableModel(data, columnNames);
	 	}

	    
	    //Metodo che mostra la lista dei clienti che hanno richiesto una consegna.
	    public DefaultTableModel mostraConsegne(String username, String giorno){
	 		
	    	 Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	 		 Vector<String> columnNames = new Vector<String>();
	 		
	 		 try {
		 		
		 			openConnection();
		 			
		 			rs = stmt.executeQuery("SELECT nome,cognome,Via, CAP, citta, giorno, ora, nomeP,quantitaP, importo FROM dbristorante.spedizione s join dbristorante.utente u on s.utente=u.username join dbristorante.piatto p on s.idPiattoS=p.idPiatto WHERE giorno = '"+giorno+"' AND ristoratore = '"+username+"'");
		 			ResultSetMetaData metaData = rs.getMetaData();
		
		 			int columnCount = metaData.getColumnCount();
		 			for (int column = 1; column <= columnCount; column++) {
		 				columnNames.add(metaData.getColumnName(column));
		 			}
		
		 			while (rs.next()) {
		 				Vector<Object> vector = new Vector<Object>();
		 				for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		 					vector.add(rs.getObject(columnIndex));
		 				}
		 				data.add(vector);
		 			}
		 			closeConnection();
	 		 } catch (SQLException e) {
	 			 	System.out.println(e);
	 		 }
	 		 return new DefaultTableModel(data, columnNames);
	 	}
	     
     
	    //Metodo che ritorna una tabella di ristoranti data una città.
	    public DefaultTableModel ricercaRistorante(String citta) {
	         
	    	 Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	         Vector<String> columnNames = new Vector<String>();
	         
	         try {
	      
			        openConnection();
			        rs = stmt.executeQuery("SELECT Nome_ristorante, Via, Citta, usernameRist FROM dbristorante.ristorante WHERE Citta='"+citta+"'");
			        ResultSetMetaData metaData = rs.getMetaData();
			         
			        int columnCount = metaData.getColumnCount();
			        for (int column = 1; column <= columnCount; column++) {
			        	columnNames.add(metaData.getColumnName(column));			
			        }
			        while (rs.next()) {
			        	Vector<Object> vector = new Vector<Object>();
			        	for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
			        		vector.add(rs.getObject(columnIndex));
				        }
				        data.add(vector);
				    }
			         
				    closeConnection();
		     }catch (SQLException e){
			        System.out.println(e);
			 }	     
	         
	         return new DefaultTableModel(data, columnNames);	        
	    }

	    //Metodo che ritorna l'orario di apertura di un determinato ristorante dato il suo ristoratore. 
	    public int trovaOrarioA(String usernameRist) {
	    	 
	    	int orarioA=0;
	       	
	        try{	               	
	         		Class.forName("com.mysql.jdbc.Driver");
	               	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	                     "root", "1234");
	               	stmt = conn.createStatement();
	                rs = stmt.executeQuery("SELECT orarioApertura FROM dbristorante.ristorante WHERE usernameRist='"+usernameRist+"'");
	                rs.next();
	                orarioA = rs.getInt("orarioApertura");
	         
	        }catch(ClassNotFoundException e) {
	             	System.out.println(e);
	        }
	        catch(SQLException e)
	        {
	             	System.out.println(e);
	        }
	     
	        return orarioA; 	    	 
	    }
     
	    //Metodo che ritorna l'orario di chiusura di un determinato ristorante dato il suo ristoratore. 
	    public int trovaOrarioC(String usernameRist) {
	    
	    	int orarioC=0;
	       	
	        try{            	
	         		Class.forName("com.mysql.jdbc.Driver");
	               	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	                     "root", "1234");
	               	stmt = conn.createStatement();
	                rs = stmt.executeQuery("SELECT orarioChiusura FROM dbristorante.ristorante WHERE usernameRist='"+usernameRist+"'");
	                rs.next();
	                orarioC = rs.getInt("orarioChiusura");
	         
	        }catch(ClassNotFoundException e) {
	             	System.out.println(e);
	        }
	        catch(SQLException e)
	        {
	             	System.out.println(e);
	        }
	     
	        return orarioC; 
	    	 
	    }
     
	    //Metodo che ritorna la tabella delle prenotazioni effettuate da un determinato cliente.
	    public DefaultTableModel mostraPrenotazioni(String username) {
	    	
	    	Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    	Vector<String> columnNames = new Vector<String>();
	    	
	    	try {
		  		
		  			openConnection();
		  					  			
		  			rs = stmt.executeQuery("SELECT Nome_ristorante, Via, Citta, orarioPrenotazione, numeroPrenotati, giorno, importo, idpagamento FROM dbristorante.pagamento p join dbristorante.utente u on u.username=p.usernameCl join dbristorante.menu m on m.idmenu = p.idMenuP join ristorante r on r.usernameRist = m.usernameRi WHERE usernameCl = '"+username+"'");
		  			ResultSetMetaData metaData = rs.getMetaData();
		
		  			int columnCount = metaData.getColumnCount();
		  			for (int column = 1; column <= columnCount; column++) {
		  				columnNames.add(metaData.getColumnName(column));
		  			}
		
		  			while (rs.next()) {
		  				Vector<Object> vector = new Vector<Object>();
		  				for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		  					vector.add(rs.getObject(columnIndex));
		  				}
		  				data.add(vector);
		  			}
		  			closeConnection();
	    	} catch (SQLException e) {
	    			System.out.println(e);
	    	}
	    	return new DefaultTableModel(data, columnNames);
	    }
	    
	    
	    //Metodo che ritorna la tabella delle spedizioni effettuate da un determinato cliente.
	    public DefaultTableModel mostraSpedizioniC(String username) {
	    	
	    	Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    	Vector<String> columnNames = new Vector<String>();
	    	
	    	try {
		  		
		  			openConnection();
		  			rs = stmt.executeQuery("SELECT Via,citta,CAP,nomeP,descrizione,quantitaP,giorno,ora,importo FROM dbristorante.spedizione s join dbristorante.piatto p on s.idPiattoS=p.idPiatto WHERE utente='"+username+"'");
		  			ResultSetMetaData metaData = rs.getMetaData();
		
		  			int columnCount = metaData.getColumnCount();
		  			for (int column = 1; column <= columnCount; column++) {
		  				columnNames.add(metaData.getColumnName(column));
		  			}
		
		  			while (rs.next()) {
		  				Vector<Object> vector = new Vector<Object>();
		  				for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		  					vector.add(rs.getObject(columnIndex));
		  				}
		  				data.add(vector);
		  			}
		  			closeConnection();
	    	} catch (SQLException e) {
	    			System.out.println(e);
	    	}
	    	return new DefaultTableModel(data, columnNames);
	    }
     
     
	    //Metodo che ritorna la spesa totale di un determinato cliente. 
	    public float ritornaSpesa(String username) {
	    	
	    	float SpesaTot=0;
       	
	    	try{
               	
	         		Class.forName("com.mysql.jdbc.Driver");
	               	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	                     "root", "1234");
	               	stmt = conn.createStatement();
	                rs = stmt.executeQuery("SELECT sum(importo) as SpesaTot FROM dbristorante.pagamento WHERE usernameCl='"+username+"'");
	                rs.next();
	                SpesaTot = rs.getFloat("SpesaTot");
         
	    	}catch(ClassNotFoundException e) {
             	System.out.println(e);
	    	}
	    	catch(SQLException e)
	    	{
             	System.out.println(e);
	    	}
     
	    	return SpesaTot; 
    	 
	    }
     
     
	 	//Metodo che disdice una prenotazione.
	 	public void disdiciPrenotazione(String username) {
	 		
	 		int idPagamento = 0;
	 		
	 		try{
		 		  	Class.forName("com.mysql.jdbc.Driver");
		            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		                     "root", "1234");
		            stmt = conn.createStatement();
		            rs = stmt.executeQuery("SELECT idpagamento FROM dbristorante.menu m join dbristorante.pagamento p on m.idmenu = p.idMenuP WHERE usernameCl='"+username+"' AND idpagamento = (select max(idpagamento) from dbristorante.pagamento where usernameCl='"+username+"')");
		            rs.next();
		            idPagamento = rs.getInt("idpagamento");
		            stmt.executeUpdate("DELETE FROM dbristorante.pagamento WHERE idpagamento='"+idPagamento+"'");
		 
	 		}catch(ClassNotFoundException e){
	 	        	System.out.println(e);
	 	    }
	 	    catch(SQLException e)
	 	    {
	 	        	System.out.println(e);
	 	    }
	 	}
 	
	 	//Metodo che disidice una data prenotazione.
	 	public void disdiciPrenotazioneSelezionata(int idPagamento) {
 		
	 		try{
		 		  	Class.forName("com.mysql.jdbc.Driver");
		            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		                     "root", "1234");
		            stmt = conn.createStatement();
		            stmt.executeUpdate("DELETE FROM dbristorante.pagamento WHERE idpagamento='"+idPagamento+"'");
		 
	 		}catch(ClassNotFoundException e) {
	 	        	System.out.println(e);
	 	    }
	 	    catch(SQLException e)
	 	    {
	 	        	System.out.println(e);
	 	    }
	 	}
     
	 	//Metodo che ritorna il nome di un dato cliente.
	 	public String ritornaNome(String username) {
        
	 		String nome=null;
     	
     		try{
               	
	         		Class.forName("com.mysql.jdbc.Driver");
	               	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	                     "root", "1234");
	               	stmt = conn.createStatement();
	                rs = stmt.executeQuery("SELECT nome FROM utente WHERE username='"+username+"'");
	                rs.next();
	               	nome = rs.getString("nome");
         
	     	}catch(ClassNotFoundException e) {
	             	System.out.println(e);
	        }
	        catch(SQLException e)
	        {
	             	System.out.println(e);
	        }
     
     		return nome;
	 	}
     
	 	//Metodo che controlla se è già stato creato un ristorante di un determinato ristoratore. 
	 	public int controlRist(String username) {
	 		
	 		int esiste=0;
      	
	 		try{
              	
	        	   	Class.forName("com.mysql.jdbc.Driver");
	        	   	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	                    "root", "1234");
	              	stmt = conn.createStatement();
	              	rs = stmt.executeQuery("SELECT count(idristorante) as esito from dbristorante.ristorante where usernameRist='"+username+"'");
	              	rs.next();
	              	esiste = rs.getInt("esito");
	        
	        }catch(ClassNotFoundException e) {
	            	System.out.println(e);
	        }
	        catch(SQLException e)
	        {
	            	System.out.println(e);
	        }
    
	 		return esiste; 
   	 
	 	}
    
	 	//Metodo che ritorna il guadagno di un determinato ristoratore.
	 	public float ritornaRicavoTot(String username){
	 		
	 		float ricavoTot=0;
      	
	 		try{
	              	
	        		Class.forName("com.mysql.jdbc.Driver");
	              	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	                    "root", "1234");
	              	stmt = conn.createStatement();
	              	rs = stmt.executeQuery("SELECT sum(importo) as Guadagno FROM dbristorante.pagamento p join dbristorante.menu m on p.idMenuP=m.idmenu where usernameRi = '"+username+"'");
	               	rs.next();
	               	ricavoTot = rs.getFloat("Guadagno");
        
	 		}catch(ClassNotFoundException e) {
	 				System.out.println(e);
	 		}
	 		catch(SQLException e)
	 		{
	 				System.out.println(e);
	 		}
    
	 		return ricavoTot; 
    
	 	}
    
	 	//Metodo che controlla se un determinato cliente si è gia prenotato per un giorno specifico.
	 	public int ritornaPrenotazione(String username, String giorno, String usernameRist) {
	 		
	 		int numP=0;
         	
	 		try{
                 	
	           		Class.forName("com.mysql.jdbc.Driver");
	                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	                       "root", "1234");
	                stmt = conn.createStatement();
	                rs = stmt.executeQuery("SELECT count(usernameCl) as numP FROM dbristorante.pagamento p join dbristorante.menu m on p.idMenuP=m.idmenu where usernameCl = '"+username+"' and giorno='"+giorno+"' and usernameRi ='"+usernameRist+"'");
	                rs.next();
	                numP = rs.getInt("numP");
           
	 		}catch(ClassNotFoundException e) {
               		System.out.println(e);
	 		}
	 		catch(SQLException e)
	 		{
               		System.out.println(e);
	 		}
       
	 		return numP; 
   	 
	 	}
    
	 	//Metodo che controlla se per un determinato ristoratore è gia stato creato un piatto con lo stesso nome di uno inserito precedentemente.
	 	public int checkPiatto(String username,String nomeP){
	 		
	 		int numPi=0;
        	
	 		try{
                	
	          		Class.forName("com.mysql.jdbc.Driver");
	                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	                      "root", "1234");
	                stmt = conn.createStatement();
	                rs = stmt.executeQuery("SELECT count(nomeP) as numPi FROM dbristorante.piatto where ristoratore = '"+username+"' AND nomeP='"+nomeP+"'");
	                rs.next();
	                numPi = rs.getInt("numPi");
          
	 		}catch(ClassNotFoundException e) {
              		System.out.println(e);
	 		}
	 		catch(SQLException e)
	 		{
              		System.out.println(e);
	 		}
      
	 		return numPi; 
  	 
	 	}
	 	
	 	//Metodo che controlla se per un determinato ristoratore è gia stata inserita una bibita con lo stesso nome di una inserita precedentemente.
	 	public int checkBibita(String username,String bibita){
	 		
	 		int numB=0;
        	
	 		try{
                	
	          		Class.forName("com.mysql.jdbc.Driver");
	                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	                      "root", "1234");
	                stmt = conn.createStatement();
	                rs = stmt.executeQuery("SELECT count(nomeBibita) as numPi FROM dbristorante.aggiunta where usernameRa = '"+username+"' AND nomeBibita='"+bibita+"'");
	                rs.next();
	                numB = rs.getInt("numPi");
          
	 		}catch(ClassNotFoundException e) {
              		System.out.println(e);
	 		}
	 		catch(SQLException e)
	 		{
              		System.out.println(e);
	 		}
      
	 		return numB; 
  	 
	 	}
	 	
	 	//Metodo che controlla se per un determinato ristoratore è gia stato creato un menù in un determinato giorno.
	 	public int checkMenu(String username,String giorno) {
	 		
	 		int numM=0;
       	
	 		try{
	               	
	         		Class.forName("com.mysql.jdbc.Driver");
	               	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	                     "root", "1234");
	               	stmt = conn.createStatement();
	                rs = stmt.executeQuery("SELECT count(giorno) as numM FROM dbristorante.menu where usernameRi = '"+username+"' AND giorno='"+giorno+"'");
	                rs.next();
	                numM = rs.getInt("numM");
         
	 		}catch(ClassNotFoundException e) {
             		System.out.println(e);
	 		}
	 		catch(SQLException e)
	 		{
             		System.out.println(e);
	 		}
     
	 		return numM; 
 	 
	 	}
	 	
	 	//Metodo che controlla i posti disponibili di un determinato giorno e ristorante. 
	 	public int numPosti(String username,String giorno) {
    	
		   	int numPo=0;
		   	int pren=0;
		   	try{
              	
	        		Class.forName("com.mysql.jdbc.Driver");
	              	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	                    "root", "1234");
	              	stmt = conn.createStatement();
	              	rs = stmt.executeQuery("SELECT capienza FROM dbristorante.ristorante where usernameRist='"+username+"'");
	              	rs.next();
	              	numPo = rs.getInt("capienza");
	              	rs.close();
	              	rs1 = stmt.executeQuery("SELECT sum(numeroPrenotati) as numPren FROM dbristorante.pagamento p join dbristorante.menu m on p.idMenuP=m.idmenu where giorno='"+giorno+"' AND usernameRi='"+username+"'");
	              	rs1.next();
	              	pren = rs1.getInt("numPren");
	              	numPo = numPo - pren;
               
	        }catch(ClassNotFoundException e) {
	            	System.out.println(e);
	        }
	        catch(SQLException e)
	        {
	            	System.out.println(e);
	        }
    
		   	return numPo; 
	 
	 	}
	 	
	 	//Metodo che controlla che ci sia almeno un piatto per tipologia appartenente al ristoratore loggato. 
	 	public boolean numPiatti(String username) {
	 		int numPa,numPp,numPs,numPc,numPd;
		   	ResultSet rs2,rs3,rs4;
			
		   	try{
              	
	        		Class.forName("com.mysql.jdbc.Driver");
	              	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	                    "root", "1234");
	              	stmt = conn.createStatement();
	              	rs = stmt.executeQuery("SELECT count(tipologia) as a FROM dbristorante.piatto where ristoratore='"+username+"' and tipologia='Antipasto'");
	              	rs.next();
	              	numPa = rs.getInt("a");
	              	rs.close();
	              	rs1 = stmt.executeQuery("SELECT count(tipologia) as p FROM dbristorante.piatto where ristoratore='"+username+"' and tipologia='Primo piatto'");
	              	rs1.next();
	              	numPp = rs1.getInt("p");
	              	rs1.close();
	              	rs2 = stmt.executeQuery("SELECT count(tipologia) as s FROM dbristorante.piatto where ristoratore='"+username+"' and tipologia='Secondo piatto'");
	              	rs2.next();
	              	numPs = rs2.getInt("s");
	              	rs2.close();
	              	rs3 = stmt.executeQuery("SELECT count(tipologia) as c FROM dbristorante.piatto where ristoratore='"+username+"' and tipologia='Contorno'");
	              	rs3.next();
	              	numPc = rs3.getInt("c");
	              	rs3.close();
	              	rs4 = stmt.executeQuery("SELECT count(tipologia) as d FROM dbristorante.piatto where ristoratore='"+username+"' and tipologia='Dolce'");
	              	rs4.next();
	              	numPd = rs4.getInt("d");
	              	rs4.close();
	              	if(numPa <1 || numPp<1 || numPs<1 || numPc<1 || numPd<1) {
	              		return false;
	              	}
               
	        }catch(ClassNotFoundException e) {
	            	System.out.println(e);
	        }
	        catch(SQLException e)
	        {
	            	System.out.println(e);
	        }
    
		   	return true; 
	 	}
	 	
	 	
	 	//Metodo che controlla se un ristorante accetta spedizioni.
	 	public String checkSped(String username) {
	 		
	 		String esito=null;
       	
	 		try{
	               	
	         		Class.forName("com.mysql.jdbc.Driver");
	               	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	                     "root", "1234");
	               	stmt = conn.createStatement();
	                rs = stmt.executeQuery("SELECT esitoR FROM dbristorante.ristorante WHERE usernameRist = '"+username+"'");
	                rs.next();
	                esito = rs.getString("esitoR");
	                
	 		}catch(ClassNotFoundException e) {
             		System.out.println(e);
	 		}
	 		catch(SQLException e)
	 		{
             		System.out.println(e);
	 		}
	 		return esito;  
	 	}
 
}
    	
    	
    	
    
         