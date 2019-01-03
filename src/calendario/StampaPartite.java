package calendario;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import classifica.Classifica;
import risultati.ListenerRisultati;
import squadre.GraficaScelta;
import squadre.ListenerScelta;

/**
 * Classe che stampa le partite di una singola giornata e gestisce la finestra principale
 * @author Giorgio Martino
 * @version 1.0
 */
public class StampaPartite 
{
	private static JFrame f;
	
	/**
	 * Questo metodo stampa tutte le partite della giornata data, inoltre aggiunge i pulsanti per 
	 * altre visualizzazioni {@link ListenerStampaPartite} oppure per l'inserimento di risultati {@link ListenerRisultati}
	 * @param frame Finestra principale
	 * @param num_gg Giornata da stampare
	 */
	public static void drawCalendario(JFrame frame, int num_gg)
	{
		f = frame;
		f.getContentPane().removeAll();
		f.setExtendedState(0);
		f.setEnabled(true);
		
		JPanel p = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		f.add(p);
		
		final JScrollPane scroll = new JScrollPane(p,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		f.setLayout(new BorderLayout());
		f.add(scroll,BorderLayout.CENTER);

		scroll.getVerticalScrollBar().setUnitIncrement(50);
				
		
		/*
		 * ComboBox and Buttons
		 */
		String [] squadre = new String[ListenerScelta.getSize()+1];
		for (int i = 0; i < (squadre.length - 1); i++)
			squadre[i] = ListenerScelta.getSquadra(i).getNome();
		squadre[ListenerScelta.getSize()] = "Tutte le giornate";
		
		final JComboBox<String> sqList = new JComboBox<String>(squadre);
		sqList.addActionListener(new ListenerStampaPartite(f,num_gg));
		
		
		JLabel ar = new JLabel();
		if (num_gg < (Berger.numGiornate() / 2))
			ar.setText("Andata");
		else
			ar.setText("Ritorno");
		
		JButton prev = new JButton("<");
		prev.addActionListener(new ListenerStampaPartite(f,num_gg));
		if (num_gg == 0)
			prev.setEnabled(false);
		
		JLabel giornata = new JLabel("Giornata "+(num_gg+1) );
		giornata.setFont(new Font("Arial", Font.BOLD, 20));
		
		JButton next = new JButton(">");
		next.addActionListener(new ListenerStampaPartite(f,num_gg));
		if (num_gg == (Berger.numGiornate()-1))
			next.setEnabled(false);
			
		c.insets = new Insets(10, 10, 10, 0);
		GraficaScelta.setGrid(c, 0, 0, 1, 0, 0);
		p.add(sqList,c);
		
		GraficaScelta.setGrid(c, 3, 0, 1, 0, 0);
		p.add(ar,c);
		
		c.insets = new Insets(0, 0, 10, 0);
		GraficaScelta.setGrid(c, 2, 1, 1, 0, 0);
		p.add(prev,c);
		
		GraficaScelta.setGrid(c, 3, 1, 1, 0, 0);
		p.add(giornata,c);
		
		GraficaScelta.setGrid(c, 4, 1, 1, 0, 0);
		p.add(next,c);
		
		int riga = 0;
		/*
		 * Stampa le partite di una singola giornata
		 */
		for (int i = 0; i < Berger.numPartite(); i++)
		{			
			riga = i+2;
			
			JButton ris = new JButton("Inserisci risultato");
			ris.addActionListener(new ListenerRisultati(num_gg,f));
			ris.setName(""+i);

			JLabel logoCasa = new JLabel( new ImageIcon( Berger.getPartita(num_gg,i).getCasa().getLogo().
										getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
			
			JLabel nomeCasa = new JLabel(Berger.getPartita(num_gg,i).getCasa().getNome());
			nomeCasa.setMinimumSize(new Dimension(200, 20));
			nomeCasa.setMaximumSize(new Dimension(200, 20));
			nomeCasa.setPreferredSize(new Dimension(200, 20));
			nomeCasa.setHorizontalAlignment(JLabel.CENTER); 
			
			String label = " - ";
			if (Berger.getPartita(num_gg, i).getEsito() != 0)
				label = Berger.getPartita(num_gg, i).getPuntCasa() + "   -   " + 
						Berger.getPartita(num_gg, i).getPuntTrasferta();
				
			JLabel vs = new JLabel(label);
			
			JLabel nomeTrasf = new JLabel(Berger.getPartita(num_gg,i).getTrasferta().getNome());
			nomeTrasf.setMinimumSize(new Dimension(200, 20));
			nomeTrasf.setMaximumSize(new Dimension(200, 20));
			nomeTrasf.setPreferredSize(new Dimension(200, 20));
			nomeTrasf.setHorizontalAlignment(JLabel.CENTER); 
			
			JLabel logoTrasf = new JLabel( new ImageIcon( Berger.getPartita(num_gg,i).getTrasferta().getLogo().
					getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
			
			
			c.insets = new Insets(0, 20, 0, 40);
			GraficaScelta.setGrid(c, 0, riga, 1, 0, 0);
			p.add(ris,c);
			
			c.insets = new Insets(0, 0, 10, 0);
			GraficaScelta.setGrid(c, 1, riga, 1, 0, 0);
			p.add(logoCasa,c);
			
			c.insets = new Insets(0, 0, 10, 20);
			GraficaScelta.setGrid(c, 2, riga, 1, 0, 0);
			p.add(nomeCasa,c);
			
			GraficaScelta.setGrid(c, 3, riga, 1, 0, 0);
			p.add(vs,c);
			
			GraficaScelta.setGrid(c, 4, riga, 1, 0, 0);
			p.add(nomeTrasf,c);
			
			c.insets = new Insets(0, 0, 10, 30);
			GraficaScelta.setGrid(c, 5, riga, 1, 0, 0);
			p.add(logoTrasf,c);
		}
		
		JButton classifica = new JButton("Classifica");
		classifica.addActionListener(new Classifica(f));
		GraficaScelta.setGrid(c, 0, riga+2, 1, 0, 0);
		p.add(classifica,c);
		
		JButton salva = new JButton("Salva Calendario");
		salva.addActionListener(new Classifica(f));
		GraficaScelta.setGrid(c, 3, riga+1, 1, 0, 0);
		p.add(salva,c);
		
		JButton elimina = new JButton("Elimina tutti i risultati");
		elimina.addActionListener(new Classifica(f));
		GraficaScelta.setGrid(c, 3, riga+2, 1, 0, 0);
		p.add(elimina,c);
		
		JButton stampa = new JButton("Stampa");
		stampa.addActionListener(new StampaFile(num_gg));
		GraficaScelta.setGrid(c, 0, riga+1, 1, 0, 0);
		p.add(stampa,c);
		
		f.pack();
	}

	/**
	 * Metodo che si occupa di mostrare un errore nella stampa, ovvero quando non viene trovata alcuna stampante
	 */
	public static void erroreStampa()
	{
		JOptionPane.showConfirmDialog(f,"Nessuna stampante trovata. \n"
				+ "È comunque possibile consultare il file \"stampa.txt\"","Errore",
				JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE, null);
	}
}
