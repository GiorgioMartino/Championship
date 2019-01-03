package classifica;

import java.awt.GridBagConstraints;

import javax.swing.JPanel;

/**
 * Classe Astratta per l'implementazione di metodi specifici di ogni sport
 * @author Giorgio Martino
 * @version 1.0
 */
public abstract class Sport 
{
	/**
	 * Si faccia riferimento al metodo specifico delle tre classi che estendono 
	 * questa {@link Rugby}, {@link Calcio} e {@link Volley}.
	 */
	public Sport()
	{	}
	
	/**
	 * Si faccia riferimento al metodo specifico delle tre classi che estendono 
	 * questa {@link Rugby}, {@link Calcio} e {@link Volley}.
	 */
	public void insertRis(JPanel p3, GridBagConstraints c3)
	{	}
	
	/**
	 * Si faccia riferimento al metodo specifico delle tre classi che estendono 
	 * questa {@link Rugby}, {@link Calcio} e {@link Volley}.
	 */
	public int getPuntCasa()
	{	
		return -1;
	}

	/**
	 * Si faccia riferimento al metodo specifico delle tre classi che estendono 
	 * questa {@link Rugby}, {@link Calcio} e {@link Volley}.
	 */
	public int getPuntTrasf()
	{	
		return -1;
	}
	
	/**
	 * Si faccia riferimento al metodo specifico delle tre classi che estendono 
	 * questa {@link Rugby}, {@link Calcio} e {@link Volley}.
	 */
	public void setPunteggio(int esito, int g, int p)
	{
		
	}
	
}
