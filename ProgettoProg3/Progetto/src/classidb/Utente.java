package classidb;

/*****************************************************************
 * Questa classe serve per definire le informazioni degli utenti.* 				                    *
 ****************************************************************/
public class  Utente{

		private String nome;
		private String cognome;
		private String username;
		private String password;
		private int ruolo;
      
      
    	public Utente (String nome, String cognome, String username, String password, int ruolo) {
           this.cognome = cognome;
           this.nome = nome;
           this.username=username;
           this.password=password;
           this.ruolo=ruolo;          
    	}
      
        public String getNome() {
           return this.nome;
        }
        
        public String getCognome() {
           return this.cognome;
        }
        
        public String getPassword() {
           return this.password;
        }
        
        public String getUsername() {
           return this.username;
        }
        
        public int getRuolo() {
           return this.ruolo;
        }
       
        public Utente (Utente C) {
           this.nome =C.nome;
           this.cognome=C.cognome;
           this.username=C.username;
           this.password=C.password;
           this.ruolo=C.ruolo;
        }
      
    
        @Override
        public String toString() {
           return nome + "," +cognome;
        }
      
}