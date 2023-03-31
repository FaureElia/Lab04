package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				Corso corso=new Corso(codins,numeroCrediti,nome,periodoDidattico);
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				corsi.add(corso);
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		// TODO
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		List<Studente> listaStudenti=new LinkedList<Studente>();
		//String codinsCorso=corso.getCodins();
		
		String sql="SELECT s.matricola, s.nome, s.cognome, s.CDS "
				+ "FROM studente s, corso c,iscrizione i "
				+ "WHERE c.codins=i.codins AND i.matricola=s.matricola AND c.codins=?";
		try {
			String codinsCorso=corso.getCodins();
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1, codinsCorso);
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				Studente s=new Studente(rs.getInt("matricola"),rs.getString("nome"),rs.getString("cognome"),rs.getString("CDS"));
				listaStudenti.add(s);
			}
			conn.close();
			return listaStudenti;
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("eccezione nella lettura");	
			return null;
		}
		
		
		
		
		
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}

}
