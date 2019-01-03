package classifica;

import java.awt.GridBagConstraints;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import calendario.Berger;
import squadre.GraficaScelta;
import squadre.ListenerScelta;

public class Volley extends Sport
{
	private static JComboBox<Integer> puntCasa;
	private static JComboBox<Integer> puntTrasf;
	
	/**
	 * Costruttore che chiama la classe padre {@link Sport}
	 */
	public Volley()
	{
		super();
	}

	/**
	 * Metodo che si occupa di permettere l'inserimento di risultati specifici per questo Sport
	 * @param p3 Pannello in cui andranno inseriti i risultati
	 * @param c3 GridBagConstraints
	 */
	public void insertRis(JPanel p3, GridBagConstraints c3)
	{
		Integer [] punteggi = new Integer[4];
		for (int i = 0; i < 4; i++)
			punteggi[i] = i;
		
		puntCasa = new JComboBox<Integer>(punteggi);
		
		puntTrasf = new JComboBox<Integer>(punteggi);
		
		GraficaScelta.setGrid(c3, 2, 1, 1, 0, 0);
		p3.add(puntCasa,c3);
		
		GraficaScelta.setGrid(c3, 4, 1, 1, 0, 0);
		p3.add(puntTrasf,c3);		
	}
	
	/**
	 * Controlla la correttezza del punteggio inserito nella Squadra di casa
	 * @return Punteggio
	 */
	public int getPuntCasa()
	{
		return (int)puntCasa.getSelectedItem();		
	}

	/**
	 * Controlla la correttezza del punteggio inserito nella Squadra in trasferta
	 * @return Punteggio
	 */
	public int getPuntTrasf()
	{		
		int c = getPuntCasa();
		int t = (int)puntTrasf.getSelectedItem();
		if ( (c < 3 && t < 3) || ((c+t) > 5) )
			return -1;
		return t;
	}
	
	
	/**
	 * Controlla l'esito della partita e in base alle regole dello Sport assegna i punti alla Squadra/e	
	 * @param esito Esito
	 * @param g Giornata
	 * @param p Partita 
	 */
	public void setPunteggio(int esito, int g, int p)
	{
		int casa = ListenerScelta.searchSquadra( Berger.getPartita(g, p).getCasa().getNome() );
		int trasf = ListenerScelta.searchSquadra( Berger.getPartita(g, p).getTrasferta().getNome() );
		
		int pc = Berger.getPartita(g, p).getPuntCasa();
		int pt = Berger.getPartita(g, p).getPuntTrasferta();
		
		switch(esito)
		{
		case 1: 
			if (pt != 2)
				ListenerScelta.getSquadra(casa).addPunti(3);
			else
			{
				ListenerScelta.getSquadra(casa).addPunti(2);
				ListenerScelta.getSquadra(trasf).addPunti(1);
			}
			break;
			
		case 2:
			if (pc != 2)
				ListenerScelta.getSquadra(trasf).addPunti(3);
			else
			{
				ListenerScelta.getSquadra(casa).addPunti(1);
				ListenerScelta.getSquadra(trasf).addPunti(2);
			}
			break;
		}
		
	}

}