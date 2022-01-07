package menu.loadgame;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import carte.Carte;


/** 
 * Class Sauvegarde.
 * 
 * Gestion des sauvegardes du jeu
 * 
 */
public class Sauvegarde implements ISauvegarde{
	
	/** Constante separateur. */
	private static final SimpleDateFormat separateur = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");  	
	/**
	 * Instancieune nouvelle sauvegarde.
	 *
	 * @param c la Carte
	 */
	public Sauvegarde(Carte c){		
		/** nom de la sauvegarde different en fonction du mode */
		String nom = Carte.modeConfig ? "WarConf " : "WarGame ";
		
		/** Si il reste de la place on creer une nouvelle sauvegarde */
		if(listeSauvegarde.size() <= MAX_SAUVEGARDE)
			listeSauvegarde.add((chemin + nom + separateur.format(new Date()) + ".ser"));
		/** Sinon on supprime la derniere on on rajoute la nouvelle */
		else {
			deleteSauvegarde();
			listeSauvegarde.add((chemin + nom + separateur.format(new Date()) + ".ser"));
		}
		
		try {   
			/** On recupere la derniere sauvegarde dans la liste */
			FileOutputStream fichier = new FileOutputStream(listeSauvegarde.get(listeSauvegarde.size() - 1));
			ObjectOutputStream sortie = new ObjectOutputStream(fichier);
			
			sortie.writeObject(c);
			sortie.close();	
			// Pour le buffer
			sortie.flush(); 
			
			fichier.close();  
		} catch(IOException ex) {
			System.out.println("IOException : " + ex);
			ex.printStackTrace();
		}
	}
	
	/**
	 * recuperation d'une sauvegarde.
	 *
	 * @param index : index de la sauvegarde que l'on veut charger
	 * @return carte
	 */
	public static Carte recupSauvegarde(int index){
		Carte c = null;
		
		try {   
			FileInputStream fichier = new FileInputStream(listeSauvegarde.get(index));
			ObjectInputStream in = new ObjectInputStream(fichier);
			
			c = (Carte)in.readObject();
			
			in.close();
			fichier.close();
		} catch(IOException ex) {
			System.out.println("IOException : " + ex + " -> ");
			ex.printStackTrace();
		} catch(ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException : " + ex);
		}
		
		return c;
	}
	
	/**
	 * Supression d'une sauvegarde.
	 */
	private static void deleteSauvegarde() {
		try 
		{
            Files.delete(Paths.get(listeSauvegarde.get(MAX_SAUVEGARDE)));
            System.out.println("Supression du fichier : " + listeSauvegarde.get(MAX_SAUVEGARDE));
            listeSauvegarde.remove(MAX_SAUVEGARDE);
		}
		catch (IOException e) 
		{
			System.out.println("e : " + e);
			e.printStackTrace();
        }

	}
}
