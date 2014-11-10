package fr.adavis.rentaco.vues;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import fr.adavis.rentaco.controleur.Controleur;
import fr.adavis.rentaco.entites.Client;
import fr.adavis.rentaco.entites.Vehicule;
import fr.adavis.rentaco.modele.ModeleLocations;
import fr.adavis.rentaco.utilitaires.Dates;

/** Vue dédiée à la saisie des informations d'une nouvelle location
 * 
 * @author xilim
 *
 */
public class VueNouvelleLocation extends JPanel implements ActionListener, DocumentListener {

	private static final long serialVersionUID = 1L;
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private Controleur controleur ;
	private ModeleLocations modele ;
	
	private JComboBox cbClients = new JComboBox() ;
	private JComboBox cbVehicules = new JComboBox() ;
	
	private JTextField tfDateDepart = new JTextField(10) ;
	
	private JButton bEnregistrer = new JButton("Enregistrer") ;
	private JButton bAnnuler = new JButton("Annuler") ;
	
	private List<Integer> numClients = new ArrayList<Integer>() ;
	private List<String> listImmatriculations = new ArrayList<String>() ;
	
	/** Créer la vue de saisie d'une nouvelle location
	 * 
	 * @param modele Le modele
	 * @param controleur Le contrôleur
	 */
	public VueNouvelleLocation(ModeleLocations modele, Controleur controleur) {
		super();
		System.out.println("VueNouvelleLocation::VueNouvelleLocation()") ;
		this.modele = modele ;
		this.controleur = controleur ;
		
		this.tfDateDepart.getDocument().addDocumentListener(this) ;
		
		this.bEnregistrer.setEnabled(false) ;
		this.bEnregistrer.addActionListener(this) ;
		this.bAnnuler.addActionListener(this) ;
		
		for(Client client : this.modele.getClients()){
			this.cbClients.addItem(client.getNom() + " " + client.getPrenom() + " (" + client.getNumero() + ")") ;
			numClients.add(new Integer(client.getNumero())) ;
		}
		
		for(Vehicule vehicule : this.modele.getVehiculesDisponibles()){
			this.cbVehicules.addItem(vehicule.getImmatriculation() + " ") ;
			listImmatriculations.add(new String(vehicule.getImmatriculation())) ;
		}
		
		Box boxPrincipal = Box.createVerticalBox() ;
		Box boxClient = Box.createHorizontalBox() ;
		Box boxDate = Box.createHorizontalBox() ;
		Box boxVehicule = Box.createHorizontalBox() ;
		Box boxBoutons = Box.createHorizontalBox() ;
		
		boxClient.add(new JLabel("Client : ")) ;
		boxClient.add(cbClients) ;
		boxDate.add(new JLabel("Date de départ : ")) ;
		boxDate.add(tfDateDepart) ;
		boxVehicule.add(new JLabel("Vehicule : ")) ;
		boxVehicule.add(cbVehicules) ;
		boxBoutons.add(bEnregistrer) ;
		boxBoutons.add(bAnnuler) ;
		
		boxPrincipal.add(boxClient) ;
		boxPrincipal.add(boxDate) ;
		boxPrincipal.add(boxVehicule) ;
		boxPrincipal.add(boxBoutons) ;
		
		this.add(boxPrincipal) ;
	}
	
	public void actualiser(){
		System.out.println("VueNouvelleLocation::actualiser()") ;
		cbClients.setSelectedIndex(0);
		tfDateDepart.setText("") ;
		cbVehicules.setSelectedIndex(0);
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		System.out.println("VueNouvelleLocation::actionPerformed()") ;
		Object sourceEvt = evt.getSource() ;
		if(sourceEvt == this.bEnregistrer){		
			int indiceClients = cbClients.getSelectedIndex() ;
			int numeroClient = numClients.get(indiceClients).intValue();
			int indiceImmatriculation = cbVehicules.getSelectedIndex() ;
			String numeroImmatriculation = listImmatriculations.get(indiceImmatriculation) ;
			
			System.out.println(numeroClient);
			System.out.println(numeroImmatriculation);
			
			this.controleur.enregistrerLocation(numeroImmatriculation, numeroClient, Dates.parseString(tfDateDepart.getText())) ;
		}
		if(sourceEvt == this.bAnnuler) {
			this.controleur.annulerEnregistrerLocation();
		}
		
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#changedUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	public void changedUpdate(DocumentEvent evt) {
		System.out.println("VueNouvelleLocation::changedUpdate()") ;
		this.repercuterSaisieDate() ;
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#insertUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	public void insertUpdate(DocumentEvent evt) {
		System.out.println("VueNouvelleLocation::insertUpdate()") ;
		this.repercuterSaisieDate() ;
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#removeUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	public void removeUpdate(DocumentEvent evt) {
		System.out.println("VueNouvelleLocation::actionPerformed()") ;
		this.repercuterSaisieDate() ;
	}
	
	/** Modifier l'état du bouton "Valider" en fonction de la valeur saisie dans le champ associé à la date de départ
	 * 
	 */
	private void repercuterSaisieDate(){
		System.out.println("VueNouvelleLocation::repercuterSaisieDate()") ;
		//System.out.println("\t["+tfDateDepart.getText()+"] -> " + tfDateDepart.getText().length());
		//System.out.println("\t"+Dates.estDate(tfDateDepart.getText()));
		if(Dates.estDate(tfDateDepart.getText()) == true) {
			this.bEnregistrer.setEnabled(true) ;
		}
		else {
			this.bEnregistrer.setEnabled(false) ;
		}			
	}
}
