package Main;

import Graphique.MyWindow;
/*import Player.Mp3Player;
import Player.PlayerThread;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

import synchronisation.SynchroMusic;*/


public class Main {
    /*public static PlayerThread thread;
    public static Mp3Player player;*/


    public static void main(String[] args) {
        /*System.out.println("Liste des fichiers mp3: ");
        SynchroMusic synchroMusic = new SynchroMusic();
        try {
            synchroMusic.ComparerMusique(new File("C:/Users/Public/Music/Sample Music"), (new File("D:/Users/bmichau/Pictures/Sample Music/")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        player = new Mp3Player("E:/pamPlayer/PamPlayer/src/com/company/legend.mp3");
        Runnable runnable = new Runnable() {
            public void run() {
                new MyWindow();
            }
        };
        SwingUtilities.invokeLater(runnable);
*/
    	MyWindow fen = new MyWindow();

    }
}
