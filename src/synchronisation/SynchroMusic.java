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
		List<String> musiqueAAjouter = new ArrayList<String>(); 

		String nomfichier="";
		String nomfichier2="";
		int diffNbFichier=0;
		boolean testSame=true;
		boolean RepSupRep2 = false;
		int parcours=0;
		listefichiers=repertoire.listFiles();
		listefichiers2=repertoire2.listFiles();

		/*
		if(listefichiers.length!=listefichiers2.length)
		{
			testSame=false;
			diffNbFichier=listefichiers2.length-listefichiers.length;
			System.out.println("Il y a "+diffNbFichier+" fichier(s) de plus dans le dossier 2");
		}
		 */
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

		if(nomsFichier1.size()>nomsFichier2.size())
		{
	
			parcours=nomsFichier1.size();
		}
		else
		{
			
			parcours=nomsFichier2.size();
		}


		for(int i=0; i<parcours;i++)
		{
			
			// si le premier dossier contient plus de fichiers que le second dossier
			if(nomsFichier1.size()>nomsFichier2.size())
			{
				if(!nomsFichier2.contains(nomsFichier1.get(i)))
				{
					testSame=false;
					musiqueAAjouter.add(nomsFichier1.get(i));
					RepSupRep2=true;
					System.out.println("Il n'y pas la méme chose dans les deux dossiers");

				}
			}
			
			// si le second dossier contient plus de fichier que le premier dossier
			if(nomsFichier1.size()<nomsFichier2.size())
			{
				if(!nomsFichier1.contains(nomsFichier2.get(i)))
				{
					System.out.println("jdjd");
					testSame=false;
					musiqueAAjouter.add(nomsFichier2.get(i));
					RepSupRep2=false;
					System.out.println("Il n'y pas la méme chose dans les deux dossiers");


				}
			}
		}

		// si les deux dossiers n'ont pas le même contenue
		if(testSame==false)
		{
			System.out.println("Musiques à ajouter "+musiqueAAjouter);

			// si il y a plus fichiers dans le repertoire 1 alors on copie les fichiers manquants dans le dossier 2
			if(RepSupRep2==true)
			{
				for(int i=0;i<musiqueAAjouter.size();i++)
				{
				Path monFichier =Paths.get(repertoire.getAbsolutePath()+"/"+musiqueAAjouter.get(i));

				Path monFichierCopie = Paths.get(repertoire2.getAbsolutePath()+"/"+musiqueAAjouter.get(i));

				Path file = Files.copy(monFichier, monFichierCopie);
				}
				System.out.println("COPIE EFFECTUEE");
			}

			// si il y a plus fichiers dans le repertoire 2 alors on copie les fichiers manquants dans le dossier 1
			else
			{
				for(int i=0;i<musiqueAAjouter.size();i++)
				{
				Path monFichier =Paths.get(repertoire2.getAbsolutePath()+"/"+musiqueAAjouter.get(i));

				Path monFichierCopie = Paths.get(repertoire.getAbsolutePath()+"/"+musiqueAAjouter.get(i));

				Path file = Files.copy(monFichier, monFichierCopie);
				}
				System.out.println("COPIE EFFECTUEE");
			}
		}
		else
		{
			System.out.println("Les dossiers sont-ils les memes: "+testSame+ " Aucune musique à ajouter");
		}

		/*
		for(i=0;i<diffNbFichier;i++){

		if(!(listefichiers[i].getName().endsWith(".mp3")==true &&listefichiers2[i].getName().endsWith(".mp3")==true && nomfichier.equals(nomfichier2))){

			testSame=false;

		}

		}
		 */

	}



	public static void main(String[] args) throws IOException
	{
		System.out.println("Liste des fichiers mp3 : ");
		ComparerMusique(new File("C:/Users/Public/Music/Sample Music"),(new File("D:/Users/bmichau/Pictures/Sample Music/")));
	}

}
