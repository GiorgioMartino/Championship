package calendario;

import java.util.ArrayList;
import java.util.Collections;

import classifica.Calcio;
import classifica.Rugby;
import classifica.Sport;
import classifica.Volley;
import squadre.ListenerScelta;
import squadre.Squadra;

/**
 * Classe che si occupa di creare le giornate di campionato, oltre che gestire tutte le operazioni relative alle partite.
 * @author Giorgio Martino
 * @version 1.0
 */
public class Berger 
{
	private static ArrayList<Partita[]> giornate = new ArrayList<Partita[]>();
    private static int numero_squadre = ListenerScelta.getSize();
    private static int num_giornate = numero_squadre - 1;
	private static Sport sport;

	/**
	 * Metodo che genera il girone di andata e quello di ritorno
	 * @param s Sport scelto
	 */
	public static void AlgoritmoDiBerger(String s)
	{ 	    

		if (s.equals("Calcio"))
			sport = new Calcio();
		else
		{
			if (s.equals("Rugby"))
				sport = new Rugby();
			else
				sport = new Volley();
		}
		
	    Squadra[] casa = new Squadra[numero_squadre /2];
	    Squadra[] trasferta = new Squadra[numero_squadre /2];
	    
	    for (int i = 0; i < num_giornate; i++)
			giornate.add(new Partita[numero_squadre/2]);

	    for (int i = 0; i < numero_squadre /2; i++) 
	    {
	        casa [i] = ListenerScelta.getSquadra(i); 
	        trasferta[i] = ListenerScelta.getSquadra(numero_squadre -1 -i); 
	    }
	    
	    for (int i = 0; i < num_giornate; i++)
	    {
	    	if (i % 2 == 0)
	    	{
	    		for (int j = 0; j< numero_squadre / 2; j++)
	    			giornate.get(i)[j] = new Partita(casa[j], trasferta[j]);
	    	}
	    	else
	    	{
	    		for (int j = 0; j< numero_squadre / 2; j++)
	    			giornate.get(i)[j] = new Partita(trasferta[j], casa[j]);
	    	}
	    	
	    	Squadra pivot = casa[0];
	    	Squadra riporto = trasferta[trasferta.length - 1];
	    	
	    	trasferta = shiftRight(trasferta, casa[1]);
	    	
	    	casa = shiftLeft(casa, riporto);
	    	
	    	casa[0] = pivot;
	    }  
	    
	    Collections.shuffle(giornate);
	    	    
	    for (int i = 0; i < num_giornate; i++)
	    {
			giornate.add(new Partita[numero_squadre/2]);

			for (int j = 0; j < numero_squadre/2; j++)
	    	{
	    		giornate.get( (i+num_giornate) )[j] = new Partita(giornate.get(i)[j].getTrasferta(), 
	    												giornate.get(i)[j].getCasa());
	    	}
	    }
	}
	
	/**
	 * Metodo utile per AlgoritmodiBerger
	 */
	private static Squadra[] shiftLeft(Squadra[] data, Squadra add)
	{
		Squadra [] temp = new Squadra[data.length];
		for (int i = 0; i < data.length-1; i++)
			temp[i] = data[i+1];
		
		temp[data.length-1] = add;
		return temp;	
	}
	
	/**
	 * Metodo utile per AlgoritmodiBerger
	 */
	private static Squadra[] shiftRight(Squadra[] data, Squadra add) 
	{
		Squadra[] temp = new Squadra[data.length];
		for (int i = 1; i < data.length; i++) {
			temp[i] = data[i - 1];
		}
		temp[0] = add;
		return temp;
	}
	
	/**
	 * Restituisce il numero di giornate presenti nel campionato
	 * @return Giornate
	 */
	public static int numGiornate()
	{
		return giornate.size();
	}
	
	/**
	 * Restituisce il numero di partite per ogni giornata
	 * @return Partite
	 */
	public static int numPartite()
	{
		return numero_squadre/2;
	}
	
	/**
	 * Ritorna una {@link Partita} specifica in base a due indici
	 * @param giornata Indice Giornata
	 * @param partita Indice Partita
	 * @return Partita
	 */
	public static Partita getPartita(int giornata, int partita)
	{
		return giornate.get(giornata)[partita];
	}

	/**
	 * Restituisce il tipo di Sport scelto
	 * @return {@link Sport}
	 */
	public static Sport getSport() 
	{
		return sport;
	}

	/**
	 * Inizializza la struttura dati
	 * @param g Giornate
	 * @param p Partite
	 * @param s Sport
	 */
	public static void inizializza(int g, int p, String s)
	{
		if (s.equals("Calcio"))
			sport = new Calcio();
		else
		{
			if (s.equals("Rugby"))
				sport = new Rugby();
			else
				sport = new Volley();
		}
		
		for(int i = 0; i < g; i++)
			giornate.add(new Partita[p]);
	}
	
	/**
	 * Data una Partita e due indici, tale Partita viene scritta nella struttura dati
	 * @param p Partita
	 * @param i Indice Giornata
	 * @param j Indice Partita
	 */
	public static void setPartita(Partita p, int i, int j)
	{
		giornate.get(i)[j] = p;
	}
	
}