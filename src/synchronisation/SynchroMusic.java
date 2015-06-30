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
import java.util.Date;
import java.util.List;

public class SynchroMusic {


	public static void comparerMusique(File repertoire, File repertoire2) throws IOException{

		// liste des fichiers du repertoire 1 et 2
		File [] listefichiers;
		File [] listefichiers2;

		// liste des fichiers à ajouter pour chaque dossier
		ArrayList<File> fichiersAAjouterDossier1Vers2=new ArrayList<File>();
		ArrayList<File> fichiersAAjouterDossier2Vers1=new ArrayList<File>();
		listefichiers=repertoire.listFiles();
		listefichiers2=repertoire2.listFiles();
		boolean test=true;
		Date creationDate1;
		Date creationDate2;

		// si le dossier 1 est vide alors on copie le continu du dossier 2 dans le 1
		if(listefichiers.length==0)
		{
			for(File fichier: listefichiers2)
				fichiersAAjouterDossier2Vers1.add(fichier);
		}

		// si le dossieer 2 est vide alors on copie le contenu du dossier 1 dans le 2
		if(listefichiers2.length==0)
		{
			for(File fichier: listefichiers)
				fichiersAAjouterDossier1Vers2.add(fichier);
		}

		// on parcours le dossier 1 afin de voir si il faut ajouter ou non des musiques on test les fichiers sur leur nom, date et taille
		for(int i=0;i<listefichiers.length;i++)
		{
			creationDate1= new Date(listefichiers[i].lastModified());


			for(int j=0; j<listefichiers2.length;j++)
			{
				creationDate2= new Date(listefichiers2[j].lastModified());
				if(!(listefichiers[i].getName().equals(listefichiers2[j].getName())) || !(listefichiers[i].length()==listefichiers2[j].length()) || !(creationDate1.equals(creationDate2)))
					test=false;
				else
				{
					test=true;
					break;
				}
			}
			if(test==false)
			{
				fichiersAAjouterDossier1Vers2.add(listefichiers[i]);
			}

		} 

		// on parcours le dossier 2 afin de voir si il faut ajouter ou non des musiques on test les fichiers sur leur nom, date et taille
		for(int i=0;i<listefichiers2.length;i++)
		{
			creationDate1= new Date(listefichiers2[i].lastModified());
			for(int j=0; j<listefichiers.length;j++)
			{
				creationDate2= new Date(listefichiers[j].lastModified());
				if(!(listefichiers2[i].getName().equals(listefichiers[j].getName())) || !(listefichiers[j].length()==listefichiers2[i].length()) || !(creationDate1.equals(creationDate2)))
					test=false;
				else
				{
					test=true;
					break;
				}
			}
			if(test==false)
			{
				fichiersAAjouterDossier2Vers1.add(listefichiers2[i]);
			}

		} 

		// ajout des musiques si nécessaire du dossier 1 vers 2
		if(fichiersAAjouterDossier1Vers2!=null)
		{
			for(File son: fichiersAAjouterDossier1Vers2){


				Path monFichier =Paths.get(repertoire.getAbsolutePath()+"/"+son.getName());

				Path monFichierCopie = Paths.get(repertoire2.getAbsolutePath()+"/"+son.getName());

				Path file = Files.copy(monFichier, monFichierCopie);
			}
		}

		// ajout des musiques si nécessaire du dossier 2 vers 1
		if(fichiersAAjouterDossier2Vers1!=null)
		{
			for(File son: fichiersAAjouterDossier2Vers1){


				Path monFichier =Paths.get(repertoire2.getAbsolutePath()+"/"+son.getName());

				Path monFichierCopie = Paths.get(repertoire.getAbsolutePath()+"/"+son.getName());

				Path file = Files.copy(monFichier, monFichierCopie);
			}
		}
		System.out.println(test);

		System.out.println(fichiersAAjouterDossier1Vers2);
		System.out.println(fichiersAAjouterDossier2Vers1);




	}




}