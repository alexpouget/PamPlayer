package main;


import com.sun.xml.internal.ws.developer.SerializationFeature;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.JavaLayerException;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import mp3Player.SongPlayer;
import mp3tag.Tag;
import music.Album;
import music.Artiste;
import music.ListMusic;
import music.Music;
import org.blinkenlights.jid3.ID3Exception;
import org.blinkenlights.jid3.MP3File;
import org.blinkenlights.jid3.io.IFileSource;
import org.blinkenlights.jid3.v1.ID3V1Tag;
import org.blinkenlights.jid3.v2.ID3V2Tag;
import synchronisation.SynchroMusic;



import graphique.MyWindow;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

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
        	synchroMusic.ComparerMusique(new File("Ressource/folder1/"), (new File("Ressource/folder/")));
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
