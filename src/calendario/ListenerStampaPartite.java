package calendario;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import squadre.GraficaScelta;

/**
 * ActionListener per visualizzare tutte le partite di una sola Squadra oppure per cambiare giornata
 * @author Giorgio Martino
 * @version 1.0
 */
public class ListenerStampaPartite implements ActionListener
{
	private static int num_gg = 0;
	private JFrame f;
	
	/**
	 * Costruttore che inizializza il JFrame e il numero della giornata corrente
	 * @param f JFrame
	 * @param n Giornata
	 */
	public ListenerStampaPartite(JFrame f, int n)
	{
		num_gg = n;
		this.f = f;
	}

	/**
	 * Nel caso in cui si vogliano visualizzare tutte le partite, oppure tutte le partite di una sola 
	 * Squadra, viene aperta una nuova finestra.
	 * Oppure viene visualizzata la Giornata precedente o successiva. 
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand() == "comboBoxChanged")
		{
			@SuppressWarnings("unchecked")
			JComboBox<String> cb = (JComboBox<String>)e.getSource();
			String sq = (String)cb.getSelectedItem();
					
			JFrame f2 = new JFrame(sq);
			f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			f2.setBounds(900, 150, 0, 0);
			JPanel p2 = new JPanel(new GridBagLayout());
			GridBagConstraints c2 = new GridBagConstraints();
			f2.add(p2);
			
			if (sq.equals("Tutte le giornate"))
			{
				for (int i = 0; i < Berger.numGiornate(); i++)
				{
					JLabel giornata = new JLabel("Giornata "+(i+1) );
					giornata.setFont(new Font("Arial", Font.BOLD, 20));

					c2.insets = new Insets(30, 0, 0, 0);
					GraficaScelta.setGrid(c2, 1, (i*(Berger.numPartite()+1)), 1, 0, 0);
					p2.add(giornata, c2);
					
					for (int j = 0; j < Berger.numPartite(); j++)
					{
						JLabel nomeCasa = new JLabel(Berger.getPartita(i,j).getCasa().getNome());
						nomeCasa.setMinimumSize(new Dimension(200, 20));
						nomeCasa.setMaximumSize(new Dimension(200, 20));
						nomeCasa.setPreferredSize(new Dimension(200, 20));
						nomeCasa.setHorizontalAlignment(JLabel.CENTER); 
						
						String label = " - ";
						if (Berger.getPartita(i, j).getEsito() != 0)
							label = Berger.getPartita(i, j).getPuntCasa() + "   -   " + 
									Berger.getPartita(i, j).getPuntTrasferta();
						
						JLabel vs = new JLabel(label);
						
						JLabel nomeTrasf = new JLabel(Berger.getPartita(i,j).getTrasferta().getNome());
						nomeTrasf.setMinimumSize(new Dimension(200, 20));
						nomeTrasf.setMaximumSize(new Dimension(200, 20));
						nomeTrasf.setPreferredSize(new Dimension(200, 20));
						nomeTrasf.setHorizontalAlignment(JLabel.CENTER); 
																							
						c2.insets = new Insets(10, 10, 0, 0);
						GraficaScelta.setGrid(c2, 0, (i*(Berger.numPartite()+1)+j+1), 1, 0, 0);
						p2.add(nomeCasa, c2);
						
						c2.insets = new Insets(10, 0, 0, 0);
						GraficaScelta.setGrid(c2, 1, (i*(Berger.numPartite()+1)+j+1), 1, 0, 0);
						p2.add(vs, c2);
						
						c2.insets = new Insets(10, 0, 0, 20);
						GraficaScelta.setGrid(c2, 2, (i*(Berger.numPartite()+1)+j+1), 1, 0, 0);
						p2.add(nomeTrasf, c2);			
					}
				}
				c2.insets = new Insets(20, 10, 10, 0);
				JButton stampa = new JButton("Stampa");
				stampa.addActionListener(new StampaFile());
				GraficaScelta.setGrid(c2, 0, (Berger.numGiornate()*(Berger.numPartite()+1)+1), 1, 0, 0);
				p2.add(stampa,c2);
			}
			else
			{
				for (int i = 0; i < Berger.numGiornate(); i++)
				{
					JLabel giornata = new JLabel("Giornata "+(i+1) );
					giornata.setFont(new Font("Arial", Font.BOLD, 20));
	
					c2.insets = new Insets(30, 0, 0, 0);
					GraficaScelta.setGrid(c2, 2, i*2, 1, 0, 0);
					p2.add(giornata, c2);
					
					for (int j = 0; j < Berger.numPartite(); j++)
					{
						if ( (sq.equals(Berger.getPartita(i,j).getCasa().getNome()) ) || 
								(sq.equals(Berger.getPartita(i,j).getTrasferta().getNome()) ) )
						{
							JLabel logoCasa = new JLabel( new ImageIcon( Berger.getPartita(i,j).getCasa().getLogo().
									getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
		
							JLabel nomeCasa = new JLabel(Berger.getPartita(i,j).getCasa().getNome());
							nomeCasa.setMinimumSize(new Dimension(200, 20));
							nomeCasa.setMaximumSize(new Dimension(200, 20));
							nomeCasa.setPreferredSize(new Dimension(200, 20));
							nomeCasa.setHorizontalAlignment(JLabel.CENTER); 
							
							String label = " - ";
							if (Berger.getPartita(i, j).getEsito() != 0)
								label = Berger.getPartita(i, j).getPuntCasa() + "   -   " + 
										Berger.getPartita(i, j).getPuntTrasferta();
							
							JLabel vs = new JLabel(label);
							
							JLabel nomeTrasf = new JLabel(Berger.getPartita(i,j).getTrasferta().getNome());
							nomeTrasf.setMinimumSize(new Dimension(200, 20));
							nomeTrasf.setMaximumSize(new Dimension(200, 20));
							nomeTrasf.setPreferredSize(new Dimension(200, 20));
							nomeTrasf.setHorizontalAlignment(JLabel.CENTER); 
							
							JLabel logoTrasf = new JLabel( new ImageIcon( Berger.getPartita(i,j).getTrasferta().getLogo().
									getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
							
							c2.insets = new Insets(0, 20, 0, 0);
							GraficaScelta.setGrid(c2, 0, i*2+1, 1, 0, 0);
							p2.add(logoCasa, c2);
							
							c2.insets = new Insets(0, 0, 0, 0);
							GraficaScelta.setGrid(c2, 1, i*2+1, 1, 0, 0);
							p2.add(nomeCasa, c2);
							
							GraficaScelta.setGrid(c2, 2, i*2+1, 1, 0, 0);
							p2.add(vs, c2);
							
							GraficaScelta.setGrid(c2, 3, i*2+1, 1, 0, 0);
							p2.add(nomeTrasf, c2);
							
							c2.insets = new Insets(0, 0, 0, 30);
							GraficaScelta.setGrid(c2, 4, i*2+1, 1, 0, 0);
							p2.add(logoTrasf, c2);
						}
							
					}
				}
				c2.insets = new Insets(20, 10, 10, 0);
				JButton stampa = new JButton("Stampa");
				stampa.addActionListener(new StampaFile(sq));
				GraficaScelta.setGrid(c2, 1, (Berger.numGiornate()*2)+1, 1, 0, 0);
				p2.add(stampa,c2);
			}
			
			final JScrollPane scroll = new JScrollPane(p2,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			f2.setLayout(new BorderLayout());
			f2.add(scroll,BorderLayout.CENTER);

			scroll.getVerticalScrollBar().setUnitIncrement(10);
			
			f2.setVisible(true);
			f2.pack();
				
		}
		else
		{
			if (e.getActionCommand() == ">")
				StampaPartite.drawCalendario(f, (num_gg+1));
			else
				StampaPartite.drawCalendario(f, (num_gg-1));
		}
	}

}
