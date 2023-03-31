package it.polito.tdp.lab04.model;

public class Corso {
	String codins;
	int numeroCrediti;
	String nomeCorso;
	int periodoDidattico;
	
	
	public Corso(String codins, int numeroCrediti, String nomeCorso, int periodoDidattico) {
		this.codins = codins;
		this.numeroCrediti = numeroCrediti;
		this.nomeCorso = nomeCorso;
		this.periodoDidattico = periodoDidattico;
	}


	public String getCodins() {
		return codins;
	}


	public void setCodins(String codins) {
		this.codins = codins;
	}


	public int getNumeroCrediti() {
		return numeroCrediti;
	}


	public void setNumeroCrediti(int numeroCrediti) {
		this.numeroCrediti = numeroCrediti;
	}


	public String getNomeCorso() {
		return nomeCorso;
	}


	public void setNomeCorso(String nomeCorso) {
		this.nomeCorso = nomeCorso;
	}


	public int getPeriodoDidattico() {
		return periodoDidattico;
	}


	public void setPeriodoDidattico(int periodoDidattico) {
		this.periodoDidattico = periodoDidattico;
	}


	@Override
	public String toString() {
		return "Corso [codins=" + codins + ", numeroCrediti=" + numeroCrediti + ", nomeCorso=" + nomeCorso
				+ ", periodoDidattico=" + periodoDidattico + "]";
	}
	
	

}
