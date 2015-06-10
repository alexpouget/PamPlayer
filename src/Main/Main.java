package main;

import synchronisation.SynchroMusic;



import graphique.MyWindow;

import javax.swing.*;

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
        	synchroMusic.ComparerMusique(new File("D:/Users/bmichau/workspace/PAMPlayer_DEV/PAMPlayer/Ressource/folder1/"), (new File("D:/Users/bmichau/workspace/PAMPlayer_DEV/PAMPlayer/Ressource/folder/")));
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
