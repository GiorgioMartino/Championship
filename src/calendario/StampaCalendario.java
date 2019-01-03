package calendario;

import java.io.FileInputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

/**
 * Classe che gestisce la stampa delle viste del calendario su stampante. 
 * Il file viene creato in {@link StampaFile} e passato a questo costruttore. 
 * @author Giorgio Martino
 * @version 1.0
 */
public class StampaCalendario {

	private Doc mydoc;	
	private DocFlavor flavor;
	private PrintService[] services ;
	private PrintRequestAttributeSet attributes;
	private FileInputStream calendario;
	private DocPrintJob job;

	/**
	 * Costruttore della classe, identifica quali stampanti ci sono. In caso positivo apre una 
	 * finestra interattiva per la stampa, altrimenti mostra un errore.
	 * @param f File da stampare
	 */
	public StampaCalendario(FileInputStream f) {
		
		calendario = f;
		
		flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
    
        //Trova stampanti		
        services = PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.AUTOSENSE, null);
		
        attributes = new HashPrintRequestAttributeSet();
		
        mydoc = new SimpleDoc(calendario, flavor, null);
		
        //se ci sono stampanti stampa, altrimenti mostra errore
		if (services.length > 0) 
		{	
		    job = services[0].createPrintJob();	

            PrintService service =  ServiceUI.printDialog(null, 50, 50,services, services[0],null, attributes);
			   
			if (service != null) 
			{
				try 
				{ 
					job.print(mydoc, attributes);
				} 
			    catch (PrintException e1) 
				{
			    	e1.printStackTrace();
			    } 
			}			    
		}
		else
			StampaPartite.erroreStampa();
	}	
}





