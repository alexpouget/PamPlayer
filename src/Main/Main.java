package Main;


import Graphique.MyWindow;


import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import synchronisation.SynchroMusic;


public class Main {

public static String fileName;

    public static void main(String[] args) {
        System.out.println("Liste des fichiers mp3: ");
        SynchroMusic synchroMusic = new SynchroMusic();
        try {
            synchroMusic.ComparerMusique(new File("E:/pamPlayer/Ressource/folder1/"), (new File("E:/pamPlayer/Ressource/folder/")));
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
