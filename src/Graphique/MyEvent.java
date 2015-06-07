package Graphique;

import Main.Main;
import Mp3Player.PlayerController;
import Mp3Player.Status;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by alex on 26/05/2015.
 */
public class MyEvent  extends WindowAdapter implements ActionListener{
    private static PlayerController player;
    private int maxLen;



        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand());
            if (e.getActionCommand()=="stop"){
                System.out.println(e.getActionCommand().toString());
                if (player == null) {
                    System.out.println("re");
                    return;
                }
                player.stop();
                player = null;
                MyWindow.play.setText("play");
                try {
                    Thread.sleep(100);
                    MyWindow.jSlider.setValue(0);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
            if(e.getActionCommand()=="play" || e.getActionCommand()=="pause" || e.getActionCommand()=="reprendre") {
                System.out.println(e.getActionCommand().toString());
                Main.fileName = "E:/Music/1.mp3";

                if (Main.fileName == null)
                    return;

                if (player == null) {
                    startPlayer();
                } else if (player.getStatus() == Status.PAUSED) {
                    player.resume();
                    MyWindow.play.setText("pause");
                } else {
                    player.pause();
                    MyWindow.play.setText("reprendre");
                }
            }
        }


    private void startPlayer() {
         player = new PlayerController(Main.fileName);
         MyWindow.jSlider.setValue(0);

        if (player == null) {
            System.out.println("mauvais fichier");
            Main.fileName = null;
            return;
        }

        //player.setListener(new SlideChangeListener());
        //infoLabel.setText(extractFileName(fileName));


            player.play();


        maxLen = player.getFramesNumber();
        MyWindow.play.setText("pause");
    }
}
