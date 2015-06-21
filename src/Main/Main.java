package main;

import org.blinkenlights.jid3.ID3Exception;
import org.blinkenlights.jid3.MP3File;
import org.blinkenlights.jid3.io.IFileSource;
import org.blinkenlights.jid3.v2.ID3V2Tag;
import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import synchronisation.SynchroMusic;



import graphique.MyWindow;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;

import java.io.*;


public class Main {

    /*public static PlayerThread thread;
    public static mp3player player;*/


public static String fileName;

    public static void main(String[] args) {
    	
        try {
            File f= new File("Ressource/folder/729.mp3");
            MP3File mp3File = new MP3File(f);
            mp3File.getID3V2Tag();
            ID3V2Tag tag = mp3File.getID3V2Tag();
            System.out.println(tag.getTitle());
        }catch
         (ID3Exception e) {
            e.printStackTrace();
        }


        System.out.println("Liste des fichiers mp3: ");
        SynchroMusic synchroMusic = new SynchroMusic();
        try {
        	synchroMusic.comparerMusique(new File("Ressource/folder1/"), (new File("Ressource/folder/")));
        } catch (IOException e) {
            e.printStackTrace();
        }


        Runnable runnable = new Runnable() {
            public void run() {
                try {
					new MyWindow();
				} catch (ParserConfigurationException | SAXException
						| IOException | JDOMException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        };
        SwingUtilities.invokeLater(runnable);



    }
}

