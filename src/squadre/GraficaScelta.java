package squadre;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe che disegna la prima finestra per scegliere lo sport
 * @author Giorgio Martino
 * @version 1.0
 * 
 */
public class GraficaScelta
{
	private static JFrame frame;
	
	/**
	 * Questo metodo permette la scelta dello sport, tramite tre pulsanti
	 * Una volta effettuata la scelta si passa a {@link ListenerScelta}
	 * @param f JFrame crato nella funzione Main
	 */
	public static void avvio(JFrame f)
	{
		frame = f;
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		frame.add(panel);
		
		JLabel scelta = new JLabel("Scegli uno sport");
		scelta.setHorizontalAlignment(0);
		
		JButton calcio = new JButton("Calcio");
		JButton basket = new JButton("Rugby");
		JButton volley = new JButton("Volley");
		
		calcio.addActionListener(new ListenerScelta(frame));
		basket.addActionListener(new ListenerScelta(frame));
		volley.addActionListener(new ListenerScelta(frame));

//		c.insets = new Insets(0,0,0,5);			//Dimensione aggiuntiva esterna (up,left,down,right)
		
		setGrid(c, 0, 0, 3, 30, 0);
	    panel.add(scelta,c);
				
		setGrid(c, 0, 1, 1, 5, 0);
		panel.add(calcio,c);
		
		setGrid(c, 1, 1, 1, 5, 0);
		panel.add(basket,c);
		
		setGrid(c, 2, 1, 1, 5, 0);
		panel.add(volley,c);
		
		frame.setVisible(true);
		frame.pack();
	}
	
	
	/**
	 * Imposta i parametri del GridLayout prima di aggiungere ogni componente
	 * @param c GridBagConstraints
	 * @param gridx Colonna
	 * @param gridy Riga
	 * @param gridwidth Quante colonne occupare
	 * @param ipady Dimensione aggiuntiva interna (asse y)
	 * @param ipadx Dimensione aggiuntiva interna (asse x)
	 */
	public static void setGrid(GridBagConstraints c, int gridx, int gridy,
			int gridwidth, int ipady, int ipadx)
	{
		c.gridx = gridx;				//Colonna
		c.gridy = gridy;				//Riga
		c.gridwidth = gridwidth;		//Quante colonne occupare
		c.ipady = ipady;				//Dimensione aggiuntiva interna (asse y)
		c.ipadx = ipadx;				//Dimensione aggiuntiva interna (asse x)
	}

}
