package calendario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Classe che implementa l'ActionListener dei bottoni "Stampa"
 * @author Giorgio Martino
 * @version 1.0
 */
public class StampaFile implements ActionListener
{
	private int giornata;
	private String squadra;
	/*
	 * 1 = stampa giornata
	 * 2 = stampa squadra
	 * 3 = stampa tutto
	 */
	private int stampa=0;
	
	/**
	 * Costruttore per la stampa di una singola giornata
	 * @param g Giornata
	 */
	public StampaFile(int g)
	{ 
		giornata = g;
		stampa = 1;
	}

	/**
	 * Costruttore per la stampa di tutte le partite di una squadra
	 * @param sq Squadra
	 */
	public StampaFile(String sq)
	{ 
		squadra = sq;
		stampa = 2;
	}
	
	/**
	 * Costruttore per la stampa di tutte le partite del calendario
	 */
	public StampaFile()
	{
		stampa = 3;
	}
	
	/**
	 * Questo metodo genera il file che poi verrà passato a {@link StampaCalendario} per erssere stampato
	 */
	public void actionPerformed(ActionEvent e) 
	{
		try
		{
			PrintStream ps = new PrintStream(new FileOutputStream("stampa.txt"));
			
			switch (stampa)
			{
				case 1: 
					ps.println("Giornata " + (giornata+1) + "\n");
					
					for(int i = 0; i<Berger.numPartite(); i++)
					{
						String ris = " - ";
						
						if(Berger.getPartita(giornata, i).getEsito() != 0)
							ris = " " + Berger.getPartita(giornata, i).getPuntCasa() + " - " 
											+ Berger.getPartita(giornata, i).getPuntTrasferta() + " ";
							
						ps.println(Berger.getPartita(giornata, i).getCasa().getNome() + ris + 
								Berger.getPartita(giornata, i).getTrasferta().getNome());
					}
					break;
					
				case 2:
					for(int i = 0; i<Berger.numGiornate(); i++)
					{
						ps.println("Giornata " + (i+1) );
						
						for(int j = 0; j<Berger.numPartite(); j++)
						{
							if ( (squadra.equals(Berger.getPartita(i,j).getCasa().getNome()) ) || 
									(squadra.equals(Berger.getPartita(i,j).getTrasferta().getNome()) ) )
							{
								String ris = " - ";
								
								if(Berger.getPartita(i, j).getEsito() != 0)
									ris = " " + Berger.getPartita(i, j).getPuntCasa() + " - " 
													+ Berger.getPartita(i, j).getPuntTrasferta() + " ";
									
								ps.println(Berger.getPartita(i, j).getCasa().getNome() + ris + 
										Berger.getPartita(i, j).getTrasferta().getNome() );
								ps.println();

							}
						}
					}
					ps.println();
					ps.println();
					ps.println();

					break;
					
				case 3:
					for(int i = 0; i<Berger.numGiornate(); i++)
					{
						ps.println("Giornata " + (i+1) );
						ps.println();
						
						for(int j = 0; j<Berger.numPartite(); j++)
						{
							String ris = " - ";
							
							if(Berger.getPartita(i, j).getEsito() != 0)
								ris = " " + Berger.getPartita(i, j).getPuntCasa() + " - " 
												+ Berger.getPartita(i, j).getPuntTrasferta() + " ";
								
							ps.println(Berger.getPartita(i, j).getCasa().getNome() + ris + 
									Berger.getPartita(i, j).getTrasferta().getNome());
						}
						ps.println();
						ps.println();
					}
					ps.println();
					ps.println();	
					break;
					
			}
			ps.close();
			
			FileInputStream fis = new FileInputStream("stampa.txt");
			@SuppressWarnings("unused")
			StampaCalendario sc = new StampaCalendario(fis);
		}
		catch(FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
	}

}
