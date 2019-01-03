package calendario;

import java.io.Serializable;

import squadre.Squadra;

/**
 * Classe che istanzia l'entità singola Partita con tutti i metodi utili
 * @author Giorgio Martino
 * @version 1.0
 */
public class Partita implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Squadra casa;
	private Squadra trasferta;
	private int puntCasa;
	private int puntTrasferta;
	
	//	0 non giocata
	//	1 vittoria casa
	//	2 vittoria trasferta
	//	3 pareggio
	private int esito;	

	/**
	 * Costruttore che inizializza una partita
	 * @param c Squadra casa
	 * @param t Squadra trasferta
	 */
	public Partita(Squadra c, Squadra t)
	{
		casa = c;
		trasferta = t;
		puntCasa = 0;
		puntTrasferta = 0;
		esito = 0;
	}
		
	/**
	 * Getter Squadra di casa
	 * @return Squadra
	 */
	public Squadra getCasa() 
	{
		return casa;
	}

	/**
	 * Setter Squadra di casa
	 * @param casa Squadra
	 */
	public void setCasa(Squadra casa)
	{
		this.casa = casa;
	}

	/**
	 * Getter Squadra in trasferta
	 * @return Squadra
	 */
	public Squadra getTrasferta() 
	{
		return trasferta;
	}

	/**
	 * Setter Squadra in trasferta
	 * @param trasferta Squadra
	 */
	public void setTrasferta(Squadra trasferta)
	{
		this.trasferta = trasferta;
	}

	/**
	 * Getter punteggio Squadra di casa
	 * @return Punteggio
	 */
	public int getPuntCasa() 
	{
		return puntCasa;
	}

	/**
	 * Setter punteggio Squadra di casa
	 * @param puntCasa Punteggio
	 */
	public void setPuntCasa(int puntCasa) 
	{
		this.puntCasa = puntCasa;
	}

	/**
	 * Getter punteggio Squadra in trasferta
	 * @return Punteggio
	 */
	public int getPuntTrasferta() 
	{
		return puntTrasferta;
	}

	/**
	 * Setter punteggio Squadra in trasferta
	 * @param puntTrasferta Punteggio
	 */
	public void setPuntTrasferta(int puntTrasferta)
	{
		this.puntTrasferta = puntTrasferta;
	}
	
	/**
	 * Getter esito Partita
	 * @return Esito
	 */
	public int getEsito() 
	{
		return esito;
	}

	/**
	 * Setter Esitp partita
	 * @param esito Esito
	 */
	public void setEsito(int esito) 
	{
		this.esito = esito;
	}

}
