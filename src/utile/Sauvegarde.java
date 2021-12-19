package utile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import carte.Carte;


public class Sauvegarde {
	private static String nomFichier = "./res/wargame.ser";
	
	// Creaction d'une nouvelle sauvegarde dans monFichier
	public Sauvegarde(Carte c){
		try
		{   
			FileOutputStream fichier = new FileOutputStream(nomFichier);
			ObjectOutputStream sortie = new ObjectOutputStream(fichier);
			
			sortie.writeObject(c);
			sortie.close();			
			sortie.flush(); // Pour le buffer
			
			fichier.close();  
		}
		catch(IOException ex)
		{
			System.out.println("IOException : " + ex);
			ex.printStackTrace();
		}
	}
	
	// Chargement de la sauvegarde dans nomFichier
	public static Carte recupSauvegarde(Carte c){
		try
		{   
			FileInputStream fichier = new FileInputStream(nomFichier);
			ObjectInputStream in = new ObjectInputStream(fichier);
			
			c = (Carte)in.readObject();
			
			in.close();
			fichier.close();
		}
		catch(IOException ex)
		{
			System.out.println("IOException : " + ex + " -> ");
			ex.printStackTrace();
		}    	        
		catch(ClassNotFoundException ex)
		{
			System.out.println("ClassNotFoundException : " + ex);
		}
		
		return c;
	}
}
