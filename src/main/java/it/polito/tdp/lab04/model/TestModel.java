package it.polito.tdp.lab04.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		
		/*
		 * 	Write here your test model
		 */
		List<Corso> listaCorsi=model.getTuttiICorsi();
		for (Corso c:listaCorsi) {
			System.out.println(c);
		}

	}

}
