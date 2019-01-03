package classifica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import calendario.Berger;
import calendario.StampaPartite;
import squadre.GraficaScelta;
import squadre.InputOutput;
import squadre.ListenerScelta;

/**
 * Implementa l'ActionListener per tre pulsanti: Classifica, Salva Calendario e Elimina tutti i risultati.
 * @author Giorgio Martino
 * @version 1.0
 */
public class Classifica implements ActionListener
{
	private static JFrame f;
	int num_gg;
	
	/**
	 * Inizializza il numero delle giornate chiamando {@link Berger}
	 * @param frame Finestra principale
	 */
	public Classifica(JFrame frame)
	{
		f = frame;
		num_gg = Berger.numGiornate();		
	}
	
	/**
	 * Per ogni pulsante premuto esegue specifiche operazioni.
	 * - Calcola la classifica
	 * - Salva il calendario su file
	 * - Elimina tutti i risultati correnti
	 */
	public void actionPerformed(ActionEvent e) 
	{
		switch(e.getActionCommand())
		{
		case "Classifica":
			int num_p = Berger.numPartite();
			
			for (int i = 0; i < ListenerScelta.getSize(); i++)
					ListenerScelta.getSquadra(i).azzeraPunti();
		
			
			for(int i = 0; i < num_gg; i++)
			{
				for(int j = 0; j < num_p; j++)
				{
					int esito = Berger.getPartita(i, j).getEsito();

					if (esito != 0)
						Berger.getSport().setPunteggio(esito,i,j);
				}
			}
			
			ListenerScelta.sortSquadre();
			
			drawClassifica();
			
			break;
			
		case "Salva Calendario":
			int n = JOptionPane.showConfirmDialog(f,"Sovrascrivere il calendario esistente?"
					,"Attenzione",JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null);

			if (n == 0)
				InputOutput.saveCampionato(f.getTitle());
			
			break;
			
		case "Elimina tutti i risultati":
			int a = JOptionPane.showConfirmDialog(f,"Sei sicuro di voler eliminare tutti i risultati?"
					,"Attenzione",JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null);
			if (a == 0)
			{
				for(int i = 0; i < Berger.numGiornate(); i++)
				{
					for(int j = 0; j < Berger.numPartite(); j++)
						Berger.getPartita(i, j).setEsito(0);
				}
			}
//			InputOutput.saveCampionato(f.getTitle());

			StampaPartite.drawCalendario(f, 0);
			break;
		}
	}
	
	/**
	 * Metodo che apre una nuova finestra e disegna la classifica
	 */
	public static void drawClassifica()
	{
		JFrame f2 = new JFrame("Classifica");
		f2.setBounds(450, 50, 0, 0);
		f2.getContentPane().removeAll();
		f2.setExtendedState(0);
		
		JPanel p2 = new JPanel(new GridBagLayout());
		GridBagConstraints c2 = new GridBagConstraints();
		f2.add(p2);
		
		final JScrollPane scroll = new JScrollPane(p2,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		f2.setLayout(new BorderLayout());
		f2.add(scroll,BorderLayout.CENTER);

		scroll.getVerticalScrollBar().setUnitIncrement(50);
		
		for (int i = 0; i < ListenerScelta.getSize(); i++)
		{
			JLabel pos = new JLabel((i+1)+"^");
			
			JLabel logo = new JLabel(new ImageIcon( ListenerScelta.getSquadra(i).getLogo().
					getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
			
			JLabel nome = new JLabel(ListenerScelta.getSquadra(i).getNome());
			nome.setMinimumSize(new Dimension(200, 20));
			nome.setMaximumSize(new Dimension(200, 20));
			nome.setPreferredSize(new Dimension(200, 20));
			nome.setHorizontalAlignment(JLabel.CENTER); 
			
			JLabel punti = new JLabel(""+ListenerScelta.getSquadra(i).getPunti());
			
		    c2.insets = new Insets(10,30,10,10);
			GraficaScelta.setGrid(c2, 0, i, 1, 0, 0);
			p2.add(pos, c2);
			
		    c2.insets = new Insets(10,10,10,0);
			GraficaScelta.setGrid(c2, 1, i, 1, 0, 0);
			p2.add(logo, c2);
			
			GraficaScelta.setGrid(c2, 2, i, 1, 0, 0);
			p2.add(nome, c2);

			c2.insets = new Insets(10,20,10,30);
			GraficaScelta.setGrid(c2, 3, i, 1, 0, 0);
			p2.add(punti, c2);
			
			f2.setVisible(true);
			f2.pack();				
		}

	}

}
