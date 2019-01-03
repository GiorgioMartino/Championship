package calendario;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import squadre.ListenerScelta;

/**
 * Classe che implementa il Thread che gestisce caricamento del calendario e ProgressBar
 * @author Giorgio Martino
 * @version 1.0
 */
public class ThreadBar extends Thread
{
	private String sport;
	private String file;
	
	/**
	 * Inizializzazione del Thread	
	 * @param s Sport scelto
	 */
	public ThreadBar(String s)
	{
		sport = s;
		file = "Calendario"+sport+".txt";
	}
		
	/**
	 * Metodo chimato quando il Thread viene fatto partire, gestisce l'input delle partite del calendario.
	 * Ad ogni partita viene aggiornata la ProgressBar
	 */
	public void run()
	{
		Partita p = null;

		try
		{
			FileInputStream filein = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(filein);
			
			int giornate = ois.readInt();
			int partite = ois.readInt();
			double incremento = 100 / (double)(giornate*partite);
			double val = 0;
						
			Berger.inizializza(giornate, partite, sport);
						
			for (int i = 0; i < giornate; i++)
			{
				for (int j = 0; j < partite; j++)
				{
					p = (Partita) ois.readObject();
					Berger.setPartita(p, i, j);
					Thread.sleep(20);
					val += incremento;
					ListenerScelta.setProgressBar(val);
				}
			}
			
			ListenerScelta.setProgressBar(100);
			ListenerScelta.finish(sport);
		
			ois.close();
		}
		catch(IOException e)
		{
			System.err.println("Lettura campionato fallita "+e);
			System.exit(5);
		}
		catch(ClassNotFoundException e)
		{
			System.err.println("Lettura squadre fallita "+e);
			System.exit(6);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}





