package it.polito.tdp.spellchecker.model;

import java.util.*;
import java.io.*;

public class Modello {

	Set <String> vocabolario = new LinkedHashSet<> ();
	private int cntErr = 0;
	
	public int getCntErr() {
		return cntErr;
	}

	public String traduci (String raw, String language) {
		List <String> parole = new LinkedList <> ();
		List <String> ritorno = new LinkedList <> ();
		
		if (raw.length() == 0) return "Prima devi inserire un testo!";
		String worked = raw.toLowerCase().replaceAll("[.,\\/#!$%\\^&\\*;:?!{}=\\-_`~()\\[\\]\"]", " ").replace("\n", " ");
		String[] words = worked.split(" ");
		for (int i = 0; i<words.length; i++) {
			if (!words[i].equals("") && words[i] != null)
				parole.add(words[i]);
		}
		String nomeFile = getClass().getResource("/"+language+".txt").getFile();
		try {
			caricaVocabolario (nomeFile);
		} catch (IOException e) {
			return "Errore nel caricamento del vocabolario "+nomeFile+"\n"+e.getMessage();
		}
		ritorno = confrontaParole(parole, vocabolario);
		String s = "Le parole errate sono: ";
		cntErr = 0;
		for (String st : ritorno) {
			s+= st+" ";
			cntErr++;
		}
		if (s.contentEquals("Le parole errate sono: "))
			return "Non ci sono parole errate!";
		
		//s+= "\n("+nomeFile+").";
		return s;
	}
	
	public void caricaVocabolario(String nomeFile) throws IOException {
		vocabolario.clear();
		FileReader fr = new FileReader(nomeFile);
		BufferedReader br = new BufferedReader(fr);
		String word;
		while ((word = br.readLine()) != null) {
			vocabolario.add(word.toLowerCase());
		}
		br.close();
	}
	
	public List <String> confrontaParole(List <String> parole, Set <String> Vocabolario) {
		List <String> paroleNonTrovate = new LinkedList <> (parole);
		paroleNonTrovate.removeAll(vocabolario);
		return paroleNonTrovate;
	}

}
