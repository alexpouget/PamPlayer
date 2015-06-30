package main;


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

        System.out.println("Liste des fichiers mp3: ");
        SynchroMusic synchroMusic = new SynchroMusic();
        try {

        	synchroMusic.comparerMusique(new File("Ressource/folder1/"), (new File("Ressource/folder/")));

        } catch (IOException e) {
            e.printStackTrace();
        }
            System.out.println("start");


        /*Mixe
        try {
            FileOutputStream fos = new FileOutputStream(new File("Ressource/folder/789.mp3"));
            FileInputStream fis = new FileInputStream(new File("Ressource/folder1/729.mp3"));
            FileInputStream fis2 = new FileInputStream(new File("Ressource/folder1/legend.mp3"));
            int i = 0;
            for(i = 0;i<fis.available();i++){
                System.out.println(fis.read());
            }
            for(;i<fis2.available();i++){
                System.out.println(fis2.read());
            }
            fos.flush();
            fos.close();
            fis.close();
            fis2.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            System.out.println(" end");
        //fin mixe*/
        Runnable runnable = new Runnable() {
            public void run() {
					new MyWindow();

            }
        };
        SwingUtilities.invokeLater(runnable);



    }
}

