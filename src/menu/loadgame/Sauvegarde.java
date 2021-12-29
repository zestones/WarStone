/********************************************************************
 * 							WarStone								*
 *  -------------------------------------------------------------	*
 * |	 Université Jean-Monnet    L3-Infos 		    2021	 |	*
 *  -------------------------------------------------------------	*
 * 	  BEGGARI ISLEM - CHATAIGNIER ANTOINE - BENGUEZZOU Idriss		*
 * 																	*
 * 														utile		*
 * ******************************************************************/

package menu.loadgame;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import carte.Carte;


/**
 * The Class Sauvegarde.
 */
public class Sauvegarde implements ISauvegarde{
	
	/**
	 * Instantiates a new sauvegarde.
	 *
	 * @param c the c
	 */
	// Creaction d'une nouvelle sauvegarde dans monFichier
	public Sauvegarde(Carte c){		
	
		if(listeSauvegarde.size() <= MAX_SAUVEGARDE)
			listeSauvegarde.add((chemin + "wargame-" + java.time.LocalDate.now() + "-"+ listeSauvegarde.size() + ".ser"));
		else 
			deleteSauvegarde();
		
		try
		{   
			FileOutputStream fichier = new FileOutputStream(listeSauvegarde.get(listeSauvegarde.size() - 1));
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
	
	/**
	 * Recup sauvegarde.
	 *
	 * @param c the c
	 * @return the carte
	 */
	// Chargement de la sauvegarde dans nomFichier
	public static Carte recupSauvegarde(int index){
		System.out.println("------------" + listeSauvegarde.get(index));
		Carte c = null;
		
		try
		{   
			FileInputStream fichier = new FileInputStream(listeSauvegarde.get(index));
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
	
	private static void deleteSauvegarde() {
		try 
		{
            Files.delete(Paths.get(listeSauvegarde.get(MAX_SAUVEGARDE - 1)));
            System.out.println("Supression du fichier : " + listeSauvegarde.get(MAX_SAUVEGARDE - 1));
            listeSauvegarde.remove(MAX_SAUVEGARDE - 1);
		}
		catch (IOException e) 
		{
			System.out.println("e : " + e);
			e.printStackTrace();
        }

	}
}
