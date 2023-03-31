package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public  Studente getStudente(int matricola) {
		//Studente s;
		
		
		String sql="SELECT matricola, nome, cognome, CDS "
				+ "FROM studente "
				+ "WHERE matricola=?";
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			//Statement st=conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs=st.executeQuery();
			rs.next();
			Studente s=new Studente(rs.getInt("matricola"),rs.getString("nome"),rs.getString("cognome"),rs.getString("CDS"));
				//s=new Studente(rs.getInt("matricola"),rs.getString("nome"),rs.getString("cognome"),rs.getString("CDS"));
			
			conn.close();
			return s;
			
			
		}catch(SQLException e) {
			//e.printStackTrace();
			System.out.println("eccezione lettura database");
			return null;
		}
		
		
	}
	
	public List<Corso> getCorsiIscritto(int matricola){
		
		List<Corso> listaCorsi=new LinkedList<Corso>();
		
		String sql="SELECT c.codins,c.crediti,c.nome,c.pd "
				+ "FROM studente s,iscrizione i, corso c "
				+ "WHERE i.matricola=s.matricola AND c.codins=i.codins AND  i.matricola=? ";
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");
				Corso corso=new Corso(codins,numeroCrediti,nome,periodoDidattico);
				listaCorsi.add(corso);
				
				
			}
			conn.close();
			return listaCorsi;
			
			
			}catch(SQLException e) {
				//e.printStackTrace();
				System.out.println("eccezione lettura database");
				return null;
			}
		
		
	}
	
	public boolean iscriviStudenteACorso(int matricola,Corso c) throws SQLException {
		boolean risultato=true;
		String sql="INSERT INTO iscrizione(matricola,codins) "
				+ "VALUES (?,?)";
		Studente s=this.getStudente(matricola);
		if (s==null) {
			return false;
		}
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, s.getMatricola());
			st.setString(2, c.getCodins());	
			ResultSet rs=st.executeQuery();
			return true;
		}catch(SQLException e){
			//e.printStackTrace();
			System.out.println("impossibile inserire la matricola");
			throw new SQLException();
		}
		
	}
	
	
	

}
