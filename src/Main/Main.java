package Main;

import synchronisation.SynchroMusic;



import javax.swing.*;

import Graphique.MyWindow;

import java.io.File;
import java.io.IOException;



public class Main {

    /*public static PlayerThread thread;
    public static Mp3Player player;*/


public static String fileName;

    public static void main(String[] args) {
        System.out.println("Liste des fichiers mp3: ");
        SynchroMusic synchroMusic = new SynchroMusic();
        try {
        	synchroMusic.ComparerMusique(new File("Ressource/folder1/"), (new File("Ressource/folder/")));
        } catch (IOException e) {
            e.printStackTrace();
        }


        Runnable runnable = new Runnable() {
            public void run() {
                new MyWindow();
            }
        };
        SwingUtilities.invokeLater(runnable);



    }
}
