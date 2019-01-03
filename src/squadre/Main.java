package squadre;

import javax.swing.JFrame;

/**
 * <h1> Gestione Campionati </h1>
 * Software per la gestione dei campionati, squadre, calendario e classifica
 * Possibilità di scegliere tra diversi sport
 * @author Giorgio Martino
 * @version 1.0
 * */
public class Main
{
	private static JFrame f;
	
	/**
	 * Funzione Main
	 * Disegna il primo frame e chiama la funzione {@link GraficaScelta}
	 * @param args
	 */
	public static void main(String [] args)
	{

		f = new JFrame("Squadre");
		f.setBounds(500, 150, 0, 0);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GraficaScelta.avvio(f);
		
	}
		
}
