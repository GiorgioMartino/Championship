package squadre;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import calendario.Berger;

/**
 * Classe che gestisce salvataggio e caricamento di squadre e calendario
 * @author Giorgio Martino
 * @version 1.0
 */
public class InputOutput 
{
	
	/**
	 * Metodo che salva le squadre su file
	 * @param f Sport scelto
	 */
	public static void saveToFile(String f)
	{
		try
		{		
			f = f + ".txt";
			FileOutputStream fileout = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fileout);
			
			oos.writeInt(ListenerScelta.getSize());
			
			for (int i = 0; i < ListenerScelta.getSize(); i++)
				oos.writeObject(ListenerScelta.getSquadra(i));
			
			oos.close();
		}
		catch(IOException e)
		{
			System.err.println("Apertura file squadre fallita "+e);
			System.exit(1);
		}
		
	}
	
	/**
	 * Metodo che legge da file le squadre e le aggiunge al vettore
	 * @param f Sport scelto
	 */
	public static void upload(String f)
	{
		Squadra sq = null;

		try
		{
			f = f +".txt";
			FileInputStream filein = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(filein);
			
			int x = ois.readInt();
						
			for (int i = 0; i < x; i++)
			{
				sq = (Squadra) ois.readObject();
				ListenerScelta.addSquadra(sq);
			}
			ois.close();
		}
		catch(IOException e)
		{
			System.err.println("Lettura squadre fallita "+e);
			System.exit(2);
		}
		catch(ClassNotFoundException e)
		{
			System.err.println("Classe squadre non trovata "+e);
			System.exit(3);
		}
	}
	
	/**
	 * Salva tutte le partite di campionato su file 
	 * @param sport Sport scelto
	 */
	public static void saveCampionato(String sport)
	{
		try
		{		
			String file = "Calendario"+sport+".txt";
			FileOutputStream fileout = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fileout);
				
			oos.writeInt(Berger.numGiornate());
			oos.writeInt(Berger.numPartite());
			
			for(int i = 0; i < Berger.numGiornate(); i++)
			{
				for (int j = 0; j < Berger.numPartite(); j++)
					oos.writeObject(Berger.getPartita(i, j));
			}
			
			oos.close();
		}
		catch(IOException e)
		{
			System.err.println("Apertura file campionato fallita "+e);
			System.exit(4);
		}
		
		
	}

}
