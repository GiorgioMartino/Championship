package risultati;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import calendario.Berger;
import calendario.StampaPartite;
import squadre.GraficaScelta;
import squadre.ListenerAddRemove;

/**
 * Classe che gestisce la finestra di inserimento dei risultati di una partita.
 * Chiamato da {@link StampaPartite}
 * @author Giorgio Martino
 * @version 1.0
 */
public class ListenerRisultati implements ActionListener 
{
	private int giornata = 0;
	private JFrame frame;
	private static JFrame f3;
	private static int partita;
	
	/**
	 * Costruttore che riceve in ingresso la giornata corrente e la finestra principale
	 * @param g Giornata
	 * @param f JFrame
	 */
	public ListenerRisultati(int g, JFrame f)
	{
		this.giornata = g;
		this.frame = f;
	}
	
	/**
	 * Controlla i punteggi inseriti e in caso positivo torna a {@link StampaPartite}
	 */
	public void actionPerformed(ActionEvent e) 
	{
				
		if (e.getActionCommand() != "OK")
		{
			drawFrame(e);
		}
		else
		{			
			int val1 = Berger.getSport().getPuntCasa();
			int val2 = Berger.getSport().getPuntTrasf();

			if (val1 == (-1) || val2 == (-1))
			{
				JOptionPane.showConfirmDialog(f3,"Punteggio errato","Errore",
						JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE, null);
			}
			else
			{
				Berger.getPartita(giornata, partita).setPuntCasa(val1);
				Berger.getPartita(giornata, partita).setPuntTrasferta(val2);
				
				int esito;
				if (val1 > val2)
					esito = 1;
				else
				{
					if (val1 < val2)
						esito = 2;
					else
						esito = 3;
				}
				
				Berger.getPartita(giornata, partita).setEsito(esito);
							
				f3.dispose();
							
				StampaPartite.drawCalendario(frame, giornata);
				frame.setEnabled(true);
			}
		}
	}
	
	/**
	 * Disegna e gestisce la finestra di inserimento dei risultati
	 * @param e ActionEvent
	 */
	public void drawFrame(ActionEvent e)
	{
		frame.setEnabled(false);
		
		partita = ListenerAddRemove.getIndex(e);
		
		f3 = new JFrame();
		f3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f3.setBounds(350, 250, 0, 0);
		JPanel p3 = new JPanel(new GridBagLayout());
		GridBagConstraints c3 = new GridBagConstraints();
		f3.add(p3);
		f3.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				frame.setEnabled(true);
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				frame.setEnabled(true);				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {				
			}
		});
		
		JLabel gior = new JLabel("Giornata " + (giornata+1) );
		gior.setFont(new Font("Arial", Font.BOLD, 20));
		
		JLabel logoCasa = new JLabel( new ImageIcon( Berger.getPartita(giornata,partita).getCasa().getLogo().
				getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
		
		JLabel casa = new JLabel(Berger.getPartita(giornata, partita).getCasa().getNome());
		casa.setMinimumSize(new Dimension(200, 20));
		casa.setMaximumSize(new Dimension(200, 20));
		casa.setPreferredSize(new Dimension(200, 20));
		casa.setHorizontalAlignment(JLabel.CENTER); 
							
		JLabel vs = new JLabel(" - ");
		
		JLabel trasf = new JLabel(Berger.getPartita(giornata, partita).getTrasferta().getNome());
		trasf.setMinimumSize(new Dimension(200, 20));
		trasf.setMaximumSize(new Dimension(200, 20));
		trasf.setPreferredSize(new Dimension(200, 20));
		trasf.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel logoTrasf = new JLabel( new ImageIcon( Berger.getPartita(giornata,partita).getTrasferta().getLogo().
				getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
		
		JButton ok = new JButton("OK");
		ok.addActionListener(this);

		c3.insets = new Insets(10, 0, 0, 0);
		GraficaScelta.setGrid(c3, 3, 0, 1, 0, 0);
		p3.add(gior, c3);
		
		c3.insets = new Insets(0, 10, 0, 0);
		GraficaScelta.setGrid(c3, 0, 1, 1, 0, 0);
		p3.add(logoCasa,c3);
		
		c3.insets = new Insets(0, 0, 0, 0);
		GraficaScelta.setGrid(c3, 1, 1, 1, 0, 0);
		p3.add(casa,c3);
		
		GraficaScelta.setGrid(c3, 3, 1, 1, 0, 0);
		p3.add(vs,c3);
				
		GraficaScelta.setGrid(c3, 5, 1, 1, 0, 0);
		p3.add(trasf,c3);
		
		c3.insets = new Insets(0, 0, 0, 10);
		GraficaScelta.setGrid(c3, 6, 1, 1, 0, 0);
		p3.add(logoTrasf,c3);
		
		c3.insets = new Insets(0, 0, 10, 0);
		GraficaScelta.setGrid(c3, 3, 2, 1, 0, 0);
		p3.add(ok,c3);
		
		Berger.getSport().insertRis(p3,c3);
		
		f3.pack();
		f3.setVisible(true);
	}

}
