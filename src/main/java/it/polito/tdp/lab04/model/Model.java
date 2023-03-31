package it.polito.tdp.lab04.model;

import java.sql.SQLException;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	CorsoDAO corsoDAO=new CorsoDAO();
	StudenteDAO studenteDAO=new StudenteDAO();
	
	
	public List<Corso> getTuttiICorsi(){
		return corsoDAO.getTuttiICorsi();
	}

	public Studente getStudente(int matricola) {
		return this.studenteDAO.getStudente(matricola);
		
	}
	
	public List<Studente> getStudentiCorso(Corso c){
		return this.corsoDAO.getStudentiIscrittiAlCorso(c);
	}
	
	public List<Corso> getCorsiStudente(int matricola){
		return this.studenteDAO.getCorsiIscritto(matricola);
	}
	
	public boolean iscriviStudenteACorso(int matricola, Corso c)throws SQLException {
		return this.studenteDAO.iscriviStudenteACorso(matricola, c);
		
		
	}
}
