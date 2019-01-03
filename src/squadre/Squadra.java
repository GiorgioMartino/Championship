package squadre;

import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 * Implementazione di una singola squadra
 * @author Giorgio Martino
 * @version 1.0
 */
public class Squadra implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String città;
	private ImageIcon logo;
	private int punti = 0;
	
	/**
	 * Costruttore vuoto
	 */
	public Squadra()
	{	}
	
	/**
	 * Costruttore dell'oggetto Squadra con tre parametri
	 * @param città Città della Squadra
	 * @param nome Nome della Squadra
	 * @param logo Icona o Logo
	 */
	public Squadra(String città, String nome, String logo)
	{
		setNome(nome);
		setCittà(città);
		this.logo = new ImageIcon(logo);
	}
	
	/**
	 * Restituisce il nome di una squadre
	 * @return Nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Imposta il nome di una Squadra, troncandolo se necessario
	 * @param nome Nome
	 */
	public void setNome(String nome) {
		nome = nome.substring(0, Math.min(nome.length(), 20));
		this.nome = nome;
	}
	
	/**
	 * Restiruisce la Città di una squadra
	 * @return Città
	 */
	public String getCittà() {
		return città;
	}
	
	/**
	 * Imposta la Città di una Squadra, troncandola se necessaria
	 * @param città Città
	 */
	public void setCittà(String città) {
		città = città.substring(0, Math.min(città.length(), 20));
		this.città = città;
	}
	
	/**
	 * Restituisce il logo di una Squadra
	 * @return Logo
	 */
	public ImageIcon getLogo() {
		return logo;
	}	
	
	/**
	 * Imposta il logo di una Squadra
	 * @param logo Logo
	 */
	public void setLogo(String logo)
	{
		this.logo = new ImageIcon(logo);
	}
	
	/**
	 * Aggiunge punti alla Squadra
	 * @param p Punti
	 */
	public void addPunti(int p)
	{
		punti+=p;
	}
	
	/**
	 * Restituisce i punti della Squadra
	 * @return Punti
	 */
	public int getPunti()
	{
		return punti;
	}
	
	/**
	 * Azzera i punti di una Squadra
	 */
	public void azzeraPunti()
	{
		punti = 0;
	}
	
}
