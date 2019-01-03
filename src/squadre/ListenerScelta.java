package squadre;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import calendario.StampaPartite;
import calendario.ThreadBar;
import classifica.Classifica;

/**
 * Questa classe implementa l'ActionListener della scelta dello sport
 * @author Giorgio Martino
 * @version 1.0
 */
public class ListenerScelta implements ActionListener
{
	private static ArrayList<Squadra> vett = new ArrayList<Squadra>();
	private static ArrayList<JTextField> array = new ArrayList<JTextField>();
	private static JFrame f;
	private static int n = 0;
	private Thread thread;
	private static JProgressBar progressBar;
		
	/**
	 * Costruttore che inizializza il JFrame
	 * @param frame finestra principale
	 */
	public ListenerScelta(JFrame frame)
	{	
		f = frame;
	}
	
	/**
	 * Metodo che apre una finestra per far scegliere all'utente se vuole iniziare un campionato da zero
	 * oppure caricare squadre e/o calendario.
	 * Si occupa anche di chiamare {@link InputOutput} per caricare le squadre oppure {@link ThreadBar} 
	 * per caricare il campionato
	 * @param e ActionEvent di {@link GraficaScelta}
	 */
	public void actionPerformed(ActionEvent e) 
	{		
		Object [] opzioni = { "Nuovo", "Carica Squadre", "Carica Campionato" };
		n = JOptionPane.showOptionDialog(f,"Vuoi Iniziare un nuovo campionato, usare squadre di" +
				"\n" + "default oppure caricare un campionato già in corso?\n\n","Messaggio",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opzioni, opzioni[0]);
		
		if (n == -1)
			n = 0;
		
		f.getContentPane().removeAll();
		
		switch (n)
		{
		case 0:
			vett.clear();
			for (int i = 0; i<4; i++)
				addSquadra(new Squadra("Città "+i,"Squadra "+i,"/home/giorgio/Scrivania/campionati/loghi/logo_null.png"));
			break;
				
		case 1:
			vett.clear();
			InputOutput.upload(e.getActionCommand());
			break;
			
		case 2:
			f.setBounds(500, 150, 0, 0);
			f.setTitle("Carico");
			
			JPanel panelThread = new JPanel();
			f.add(panelThread);
			
			progressBar = new JProgressBar();
			progressBar.setStringPainted(true);
			progressBar.setMinimum(0);
			progressBar.setMaximum(100);
			
			progressBar.setMinimumSize(new Dimension(300, 40));
			progressBar.setMaximumSize(new Dimension(300, 40));
			progressBar.setPreferredSize(new Dimension(300, 40));

			panelThread.add(progressBar);
			
			f.setVisible(true);	
			f.pack();			
			
			vett.clear();
			InputOutput.upload(e.getActionCommand());
			
			thread = new ThreadBar(e.getActionCommand());
			thread.start();
			
			break;			
		
		}
		
		if (n != 2)
			openSport(e.getActionCommand());
	}
	
	
	/**
	 * Questo metodo viene chiamato nel caso di nuovo campionato oppure caricamento delle squadre.
	 * Permette di aggiungere modificare ed eliminare le squadre.
	 * Il tutto viene gestito da {@link ListenerAddRemove}
	 * @param s Sport scelto
	 */
	public static void openSport(String s)
	{
		//Imposta le label iniziali delle squadre
		array.clear();
		f.setTitle(s);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(250, 100, 0, 0);
				
		if (getSize() > 7)
			f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		f.add(panel);
				
		JLabel titoloLogo = new JLabel("Logo");
		JLabel titoloSquadra = new JLabel("Squadra");
		JLabel titoloCitta = new JLabel("Città");
		
		c.insets = new Insets(10,0,10,0);
		GraficaScelta.setGrid(c, 1, 0, 1, 0, 0);
	    panel.add(titoloLogo,c);
	    GraficaScelta.setGrid(c, 2, 0, 1, 0, 0);
	    panel.add(titoloSquadra,c);
	    GraficaScelta.setGrid(c, 3, 0, 1, 0, 0);
	    panel.add(titoloCitta,c);
		
	    //Stampa tutte le squadre
		for(int i=0; i<getSize(); i++)
		{
			JButton logo = new JButton(getSquadra(i).getLogo());
			logo.setIcon(new ImageIcon(getSquadra(i).getLogo().getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
			logo.setPreferredSize(new Dimension(80, 80));
			logo.setMaximumSize(new Dimension(80, 80));
			logo.setMinimumSize(new Dimension(80, 80));
			logo.addActionListener(new ListenerAddRemove(s, f));
			logo.setName(""+i);

			JTextField nome = new JTextField(getSquadra(i).getNome());
			nome.setMinimumSize(new Dimension(170, 20));
			nome.setMaximumSize(new Dimension(170, 20));
			nome.setPreferredSize(new Dimension(170, 20));
			array.add(nome);
			
			JTextField citta = new JTextField(getSquadra(i).getCittà());
			citta.setMinimumSize(new Dimension(170, 20));
			citta.setMaximumSize(new Dimension(170, 20));
			citta.setPreferredSize(new Dimension(170, 20));
			array.add(citta);
			
			JButton rimuovi = new JButton("Rimuovi");
			rimuovi.addActionListener(new ListenerAddRemove(s,f));
			rimuovi.setName(""+i);
		
			c.insets = new Insets(0,10,0,50);			//Dimensione aggiuntiva esterna (up,left,down,right)
			GraficaScelta.setGrid(c, 0, i+1, 1, 0, 0);
		    panel.add(rimuovi,c);
						
		    c.insets = new Insets(5,0,5,10);	
		    GraficaScelta.setGrid(c, 1, i+1, 1, 0, 10);
		    panel.add(logo,c);
		    
		    c.insets = new Insets(0,0,0,10);
		    GraficaScelta.setGrid(c, 2, i+1, 1, 0, 10);
		    panel.add(nome,c);
		    
		    c.insets = new Insets(0,0,0,20);
		    GraficaScelta.setGrid(c, 3, i+1, 1, 0, 10);
		    panel.add(citta,c);
		}	
		
		c.insets = new Insets(10,0,10,0);
		
		JButton aggiungi = new JButton("Aggiungi");
		aggiungi.addActionListener(new ListenerAddRemove(s,f));
		GraficaScelta.setGrid(c, 1, getSize()+1, 2, 0, 0);
		panel.add(aggiungi,c);
		
		JButton conferma = new JButton("Conferma");
		GraficaScelta.setGrid(c, 3, getSize()+1, 1, 0, 0);
		conferma.addActionListener(new ListenerAddRemove(s,f));
		panel.add(conferma,c);
		
		JButton salva = new JButton("Salva su File");
		GraficaScelta.setGrid(c, 0, getSize()+1, 1, 0, 0);
		salva.addActionListener(new ListenerAddRemove(s,f));
		panel.add(salva,c);
		
		final JScrollPane scroll = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
									JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		f.setLayout(new BorderLayout());
		f.add(scroll,BorderLayout.CENTER);
		
		scroll.getVerticalScrollBar().setUnitIncrement(10);
		
		f.setVisible(true);
		f.pack();

	}
	
	/**
	 * Restituisce una squadra in base ad un indice
	 * @param a Indice della squadra
	 * @return una {@link Squadra}
	 */
	public static Squadra getSquadra(int a)
	{
		return vett.get(a);
	}
	
	/**
	 * Aggiunge una squadra al vettore
	 * @param sq Una {@link Squadra}
	 */
	public static void addSquadra(Squadra sq)
	{
		vett.add(sq);
	}
	
	/**
	 * Rimuove una squadra e i relativi campi dal JFrame
	 * @param a Indice della Squadra
	 */
	public static void removeSquadra(int a)
	{
		vett.remove(a);
		array.remove(a*2);
		array.remove(a*2);
	}
	
	/**
	 * Imposta il logo di una {@link Squadra}
	 * @param a Indice della Squadra
	 * @param logo url del logo
	 */
	public static void setLogo(int a, String logo) 
	{
		vett.get(a).setLogo(logo);
	}
	
	/**
	 * Restituisce la lunghezza del vettore
	 * @return numero di squadre
	 */
	public static int getSize()
	{
		return vett.size();
	}
	
	/**
	 * Imposta il nome di una {@link Squadra}
	 * @param a Indice della Squadra
	 * @param nome Il nome
	 */
	public static void setNome(int a, String nome)
	{
		vett.get(a).setNome(nome);
	}
	
	/**
	 * Imposta la Città di una {@link Squadra}
	 * @param a Indice
	 * @param citta La città
	 */
	public static void setCitta(int a, String citta)
	{
		vett.get(a).setCittà(citta);
	}
	
	/**
	 * Legge il testo da una JTextField
	 * @param i Indice
	 * @return Testo (nome o città di una Squadra)
	 */
	public static String getText(int i)
	{
		return array.get(i).getText();
	}

	/**
	 * Cancella tutte le squadre
	 */
	public static void clear()
	{
		vett.clear();
	}
	
	/**
	 * Cerca una squadra per nome e ne restituisce l'indice
	 * @param nome Nome da cercare
	 * @return Indice
	 */
	public static int searchSquadra(String nome)
	{
		for(int i=0; i<vett.size(); i++)
		{
			if (nome.equals(vett.get(i).getNome()))
				return i;
		}
		return -1;
	}
	
	/**
	 * Ordina le squadre in base al punteggio
	 * {@link Classifica}
	 */
	public static void sortSquadre()
	{
		Collections.sort(vett, new Comparator<Squadra>() 
			{
				@Override
				public int compare(Squadra sq2, Squadra sq1)
				{
					int p1 = sq1.getPunti();
					int p2 = sq2.getPunti();
					
					if(p1 > p2)
						return 1;
					else
					{
						if (p1 < p2)
							return -1;
						else
							return 0;
					}
				}
			});
	}
	
	/**
	 * Durante il caricamento di un calendario imposta progressivamente l'avanzamento dell'operazione
	 * @param n Valore da impostare da 0 a 100
	 */
	public static void setProgressBar(double n)
	{
		progressBar.setValue( (int)n );
	}

	/**
	 * Metodo chiamato da {@link ThreadBar} quando ha finito
	 * @param sport Sport scelto
	 */
	public static void finish(String sport)
	{
		f.setBounds(250, 100, 0, 0);
		f.setTitle(sport);
		StampaPartite.drawCalendario(f, 0);
	}

}



