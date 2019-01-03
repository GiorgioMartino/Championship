package squadre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import calendario.Berger;
import calendario.StampaPartite;


/**
 * Listener di tutti i pulsanti della gestione squadre
 * @author Giorgio Martino
 * @version 1.0
 */
public class ListenerAddRemove implements ActionListener
{
	private static String sport;
	private static JFrame frame; 
	
	/**
	 * Metodo Costruttore
	 * @param s Sport scelto
	 * @param f Finestra principale
	 */
	public ListenerAddRemove(String s, JFrame f)
	{
		sport = s;
		frame = f;
	}
	
	/**
	 * Metodo che gestisce la gestione delle squadre.
	 * - Rimuove una squadra
	 * - Aggiunge una squadra
	 * - Modifica il logo tramite {@link JFileChooser}
	 * - Salva le squadre su file
	 * - Conferma le squadre, crea le giornate di campionato {@link Berger} e stampa le partite {@link StampaPartite}
	 */
	public void actionPerformed(ActionEvent e) 
	{				
		switch ( e.getActionCommand() )
		{
			case "Rimuovi" :
				int x = getIndex(e);
				ListenerScelta.removeSquadra(x);
				update();
				
				frame.getContentPane().removeAll();
				ListenerScelta.openSport(sport);
				break;
				
			case "Aggiungi" :

				update();
				ListenerScelta.addSquadra( new Squadra("Nome Città","Nome Squadra",
						"/home/giorgio/Scrivania/campionati/loghi/logo_null.png") );
				
				frame.getContentPane().removeAll();
				ListenerScelta.openSport(sport);
				break;
				
			case "" :

				update();
				JFileChooser filechooser = new JFileChooser(new File("/home/giorgio/Scrivania/campionati/loghi/"+sport));
				filechooser.setDialogTitle("Scelta icona");
				
				int a = filechooser.showOpenDialog(frame);
							
				if (a == 0)
				{
					int y = getIndex(e);
					ListenerScelta.setLogo(y, ""+filechooser.getSelectedFile());
				}
				
				frame.getContentPane().removeAll();
				ListenerScelta.openSport(sport);
				break;
				
			case "Conferma" :

				update();
				if ( (ListenerScelta.getSize() % 2) != 0 )
				{
					JOptionPane.showConfirmDialog(frame,"Il numero di squadre deve essere pari.","Errore",
							JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE, null);
					
					frame.getContentPane().removeAll();
					ListenerScelta.openSport(sport);
					break;
				}
								
//				double inizio = System.currentTimeMillis();
				Berger.AlgoritmoDiBerger(frame.getTitle());
//				double fine = System.currentTimeMillis();
//				System.out.println("Berger = " + (fine-inizio)/1000);
						
//				inizio = System.currentTimeMillis();
				StampaPartite.drawCalendario(frame,0);
//				fine = System.currentTimeMillis();
//				System.out.println("Disegna calendario = " + (fine-inizio)/1000);

				
				break;
				
			case "Salva su File":

				update();
				if ( (ListenerScelta.getSize() % 2) != 0 )
				{
					JOptionPane.showConfirmDialog(frame,"Il numero di squadre deve essere pari.","Errore",
							JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE, null);
					
					frame.getContentPane().removeAll();
					ListenerScelta.openSport(sport);
					break;
				}
						
				int n = JOptionPane.showConfirmDialog(frame,"Vuoi veramente sovrascrivere le squadre salvate?"
						,"Attenzione",JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null);

				if (n == 0)
					InputOutput.saveToFile(sport);
				
				frame.getContentPane().removeAll();
				ListenerScelta.openSport(sport);
				break;
				
		}
		
	}
	
	/**
	 * Restituisce l'indice del pulsante premuto
	 * @param e ActionEvent
	 * @return Indice
	 */
	public static int getIndex(ActionEvent e)
	{		
		String s = ( (JButton) e.getSource() ).getName();
		int x = Integer.parseInt(s);
		return x;
	}
	
	/**
	 * Aggiorna il vettore delle squadre
	 */
	public static void update()
	{
		String nome;
		String citta;
		for (int i = 0; i < (ListenerScelta.getSize()*2); i+=2)
		{
			nome = ListenerScelta.getText(i);
			citta = ListenerScelta.getText(i+1);
					
			if (!(nome.equals(ListenerScelta.getSquadra(i/2).getNome())))
				ListenerScelta.getSquadra(i/2).setNome(nome);
			
			if (!(citta.equals(ListenerScelta.getSquadra(i/2).getCittà())))
				ListenerScelta.getSquadra(i/2).setCittà(citta);
										
		}
	}

}