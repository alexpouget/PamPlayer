package main;


import music.ListMusic;
import music.Music;
import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import speechReco.SpeechReco;
import synchronisation.SynchroMusic;



import graphique.MyWindow;

import javax.swing.*;

import javax.xml.parsers.ParserConfigurationException;

import java.io.*;


public class Main {

public static String fileName;

    public static void main(String[] args) {
        ListMusic l = new ListMusic();
//        SynchroMusic synchroMusic = new SynchroMusic();
//        try {
//
//        	synchroMusic.comparerMusique(new File("Ressource/folder1/"), (new File("Ressource/folder/")));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        // lancement de la fenetre MyWindow
        Runnable runnable = new Runnable() {
            public void run() {
					new MyWindow();

            }
        };
        SwingUtilities.invokeLater(runnable);



    }
}

