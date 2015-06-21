package main;

import mp3tag.Tag;
import music.Album;
import music.Artiste;
import music.Music;
import org.blinkenlights.jid3.ID3Exception;
import org.blinkenlights.jid3.MP3File;
import org.blinkenlights.jid3.io.IFileSource;
import org.blinkenlights.jid3.v1.ID3V1Tag;
import org.blinkenlights.jid3.v2.ID3V2Tag;
import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import synchronisation.SynchroMusic;



import graphique.MyWindow;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Main {

    /*public static PlayerThread thread;
    public static mp3player player;*/


public static String fileName;

    public static void main(String[] args) {

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

