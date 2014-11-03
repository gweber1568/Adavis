package rentaco;

import java.util.* ;

class HeureException extends Exception {
	public HeureException(String message){
		super(message) ;
	}
}

/**
 * @author Guillaume Weber
 * @version 1.0
 *
 */
public class Heure {
	private int heures ;
	private int minutes ;
	private int secondes ;
	
	
	
	public Heure(int heures, int minutes, int secondes) throws HeureException {
		super();
		if(heures<00 || heures >24) {
			throw new HeureException("Format d'heure impossible.") ;
		}
		else {
			this.heures = heures;
		}
		if(minutes<0 || minutes>=60) {
			throw new HeureException("Format de minute impossible.") ;
		}
		else {
			this.minutes = minutes;
		}
		if(secondes<0 || secondes>=60) {
			throw new HeureException("Format de seconde impossible.") ;
		}
		else {
			this.secondes = secondes;
		}
	}

	/**
	 * La méthode getHeures() permet d'obtenir l'heure de l'objet Heure.
	 * @return heures
	 */
	public int getHeures() {
		return heures;
	}

	/**
	 * La méthode getMinutes() permet d'obtenir les minutes de l'objet Heure.
	 * @return minutes
	 */
	public int getMinutes() {
		return minutes;
	}

	/**
	 * La méthode getSecondes() permet d'obtenir les secondes de l'objet Heure.
	 * @return secondes
	 */
	public int getSecondes() {
		return secondes;
	}
	

	/**
	 * La méthode setHeures() est un altérateur qui agit sur l'objet heures.
	 * @param heures
	 * @throws HeureException
	 */
	public void setHeures(int heures) throws HeureException {
		if(heures<00 || heures >24) {
			throw new HeureException("Format d'heure impossible.") ;
		}
		else {
			this.heures = heures;
		}
	}

	/**
	 * La méthode setMinutes() est un altérateur qui agit sur l'objet minutes.
	 * @param minutes
	 * @throws HeureException
	 */
	public void setMinutes(int minutes) throws HeureException {
		if(minutes<00 || minutes>=60) {
			throw new HeureException("Format de minute impossible.") ;
		}
		else {
			this.minutes = minutes;
		}
	}

	/**
	 * La méthode setSecondes() est un altérateur qui agit sur l'objet secondes.
	 * @param secondes
	 * @throws HeureException
	 */
	public void setSecondes(int secondes) throws HeureException {
		if(secondes<00 || secondes>=60) {
			throw new HeureException("Format de seconde impossible.") ;
		}
		else {
			this.secondes = secondes;
		}
	}

	@Override
	public String toString() {
		return "Heure :" + heures + "/" + minutes
				+ "/" + secondes + "";
	}
	
	/**
	 * La méthode main permet de tester le code de manière autonome.
	 * @param args
	 * @throws HeureException 
	 */
	public static void main(String [] args) throws HeureException{
		try {
			Heure h1 = new Heure(14,30,00) ;
			System.out.println(h1) ;
			Heure h2 = new Heure(20,48,12) ;
			System.out.println(h2) ;
			
			System.out.println(h1.getHeures()) ;
			System.out.println(h1.getMinutes()) ;
			System.out.println(h1.getSecondes()) ;
		}
		catch(HeureException e) {
			System.out.println(e.getMessage()) ;
		}
	}
}