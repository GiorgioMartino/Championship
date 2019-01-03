package classifica;

import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;
import javax.swing.JTextField;

import calendario.Berger;
import squadre.GraficaScelta;
import squadre.ListenerScelta;

public class Rugby extends Sport
{
	private static JTextField puntCasa;
	private static JTextField puntTrasf;
	
	/**
	 * Costruttore che chiama la classe padre {@link Sport}
	 */
	public Rugby()
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
		puntCasa = new JTextField("");
		puntCasa.setMaximumSize(new Dimension(30,20));
		puntCasa.setMinimumSize(new Dimension(30,20));
		puntCasa.setPreferredSize(new Dimension(30,20));
		
		puntTrasf = new JTextField("");
		puntTrasf.setMaximumSize(new Dimension(30,20));
		puntTrasf.setMinimumSize(new Dimension(30,20));
		puntTrasf.setPreferredSize(new Dimension(30,20));
		
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
		String s = puntCasa.getText();
		try
		{
			int p = Integer.parseInt(s);
			return p;
		}
		catch(NumberFormatException e)
		{
			return -1;
		}
		
	}

	/**
	 * Controlla la correttezza del punteggio inserito nella Squadra in trasferta
	 * @return Punteggio
	 */
	public int getPuntTrasf()
	{
		String s = puntTrasf.getText();
		try
		{
			int p = Integer.parseInt(s);
			if (p >= 0 && p < 200)
				return p;
			else
				return -1;
		}
		catch(NumberFormatException e)
		{
			return -1;
		}
			
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
			ListenerScelta.getSquadra(casa).addPunti(4);
			if ( (pc - pt) < 8 )
				ListenerScelta.getSquadra(trasf).addPunti(1);
			break;
			
		case 2:
			ListenerScelta.getSquadra(trasf).addPunti(4);
			if ( (pt - pc) < 8 )
				ListenerScelta.getSquadra(casa).addPunti(1);
			break;
			
		case 3:
			ListenerScelta.getSquadra(casa).addPunti(2);
			ListenerScelta.getSquadra(trasf).addPunti(2);
			break;
		}
		
	}
}
