package main;


import music.ListMusic;
import music.Music;





import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public static final Logger logger = LogManager.getLogger(Main.class.getName());


    public static void main(String[] args) {
        ListMusic l = new ListMusic();
    
        // lancement de la fenetre MyWindow
        try{
        Runnable runnable = new Runnable() {
            public void run() {
					new MyWindow();

            }
        };
        SwingUtilities.invokeLater(runnable);
        }catch(Exception e){
        	logger.error("Salut"+e.getMessage());
        }


    }
}

