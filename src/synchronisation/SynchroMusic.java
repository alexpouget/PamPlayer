package synchronisation;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SynchroMusic {


	public static void ComparerMusique(File repertoire, File repertoire2) throws IOException{

		// liste des fichiers du repertoire 1 et 2
		File [] listefichiers;
		File [] listefichiers2;

		// on stocke le nom des fichiers mp3 dans des lists
		List<String> nomsFichier1 = new ArrayList<String>();
		List<String> nomsFichier2 = new ArrayList<String>(); 

		// on prépare une liste pour mémoriser les musiques à ajouter
		List<String> musiqueAAjouterDossier1 = new ArrayList<String>(); 
		List<String> musiqueAAjouterDossier2= new ArrayList<String>();

		String nomfichier="";
		String nomfichier2="";
		int diffNbFichier=0;
		boolean testSame=true;
		boolean RepSupRep2 = false;
		int parcours=0;
		listefichiers=repertoire.listFiles();
		listefichiers2=repertoire2.listFiles();


		// on parcours la liste de fichiers mp3 du repertoire 1 et on remplie la liste avec les noms des fichiers Mp3
		for(int i=0;i<listefichiers.length;i++)
		{
			if(listefichiers[i].getName().endsWith(".mp3"))
			{
				nomsFichier1.add(listefichiers[i].getName());
			}

		}

		// on parcours la liste de fichiers mp3 du repertoire 2 et on remplie la liste avec les noms des fichiers Mp3
		for(int i=0;i<listefichiers2.length;i++)
		{
			if(listefichiers2[i].getName().endsWith(".mp3"))
			{
				nomsFichier2.add(listefichiers2[i].getName());
			}

		}

		// affichage des fichiers dans les deux dossiers
		System.out.println("Liste nom des fichiers du dossier 1: "+nomsFichier1);
		System.out.println("Liste nom des fichiers du dossier 2: "+nomsFichier2);

		// on compare les deux listes des fichiers mp3 afin de savoir si deux dossier sont les mêmes et on mémorise les musiques à ajouter

		// on parcours les fichiers du dossier 1
		for(int i=0; i<nomsFichier1.size();i++)
		{

			// si un fichier du dossier 1 n'est pas contenue dans le dossier 2 on l'ajoute a liste des musiques a ajouter
			if(!nomsFichier2.contains(nomsFichier1.get(i)))
			{
				testSame=false;

				musiqueAAjouterDossier1.add(nomsFichier1.get(i));
				RepSupRep2=true;
				//					System.out.println("Il n'y pas la méme chose dans les deux dossiers");

			}
		}

		// si un fichier du dossier 2 n'est pas contenue dans le dossier 1 on l'ajoute a liste des musiques a ajouter
		for(int i=0; i<nomsFichier2.size();i++)
		{

			if(!nomsFichier1.contains(nomsFichier2.get(i)))
			{

				testSame=false;
				musiqueAAjouterDossier2.add(nomsFichier2.get(i));
				RepSupRep2=false;
				//					System.out.println("Il n'y pas la méme chose dans les deux dossiers");


			}
		}

		if(testSame==false)
		{
			//System.out.println("Musiques à ajouter "+musiqueAAjouter);

			// si il y a plus fichiers dans le repertoire 1 alors on copie les fichiers manquants dans le dossier 2
			if(RepSupRep2==true)
			{
				// ajout du dossie 1 dans le dossier 2
				for(int i=0;i<musiqueAAjouterDossier1.size();i++)
				{
					Path monFichier =Paths.get(repertoire.getAbsolutePath()+"/"+musiqueAAjouterDossier1.get(i));

					Path monFichierCopie = Paths.get(repertoire2.getAbsolutePath()+"/"+musiqueAAjouterDossier1.get(i));

					Path file = Files.copy(monFichier, monFichierCopie);
				}
				System.out.println("COPIE EFFECTUEE");
			}


			// si il y a plus fichiers dans le repertoire 2 alors on copie les fichiers manquants dans le dossier 1
			else
			{
				// ajout du dossie 2 dans le dossier 1

				for(int i=0;i<musiqueAAjouterDossier2.size();i++)
				{
					Path monFichier =Paths.get(repertoire2.getAbsolutePath()+"/"+musiqueAAjouterDossier2.get(i));

					Path monFichierCopie = Paths.get(repertoire.getAbsolutePath()+"/"+musiqueAAjouterDossier2.get(i));

					Path file = Files.copy(monFichier, monFichierCopie);
				}
				System.out.println("COPIE EFFECTUEE");
			}

		}


		else
		{
			System.out.println("Les dossiers sont-ils les memes: "+testSame+ " Aucune musique à ajouter");
		}


	}


	public static void main(String[] args) throws IOException
	{
		System.out.println("Liste des fichiers mp3: ");
		ComparerMusique(new File("C:/Users/Public/Music/Sample Music"),(new File("D:/Users/bmichau/Pictures/Sample Music/")));
	}

}