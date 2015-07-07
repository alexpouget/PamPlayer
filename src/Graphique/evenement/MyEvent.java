package graphique.evenement;

import graphique.MyWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.event.ChangeListener;

import main.Main;
import mp3Player.PlayerController;
import mp3Player.Status;
import mp3tag.Tag;

import org.blinkenlights.jid3.ID3Exception;
//import javazoom.jl.decoder.JavaLayerException;
//import javazoom.jl.player.Player;
//import javazoom.jl.player.advanced.AdvancedPlayer;
import javax.swing.event.ChangeEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 * Created by alex on 26/05/2015.
 */
public class MyEvent  extends WindowAdapter implements ChangeListener,ActionListener, MouseListener {
    private static PlayerController player;
    public static int maxLen;
    private boolean pause;

    @Override
    public void windowClosing(WindowEvent e) {
        int rep = JOptionPane.showConfirmDialog(null, "Quitter PamPlayer ?");
        if(rep==0)
            System.exit(0);
    }

        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand());
            if (e.getActionCommand()=="stop"){
                System.out.println(e.getActionCommand().toString());
                if (player == null) {
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
            if(e.getActionCommand()=="play" || e.getActionCommand()=="pause") {
                System.out.println(e.getActionCommand().toString());
                if(Main.fileName == null) {
                    Main.fileName = MyWindow.listMusic.get(0).getPath();
                }

                if (Main.fileName == null)
                    return;

                if (player == null) {
                    startPlayer();
                } else if (player.getStatus()== Status.PAUSED) {
                    player.resume();
                    MyWindow.play.setText("pause");
                } else {
                    player.pause();
                    MyWindow.play.setText("play");
                }
            }
        }


    private void startPlayer() {
         player = new PlayerController(Main.fileName);
         MyWindow.jSlider.setValue(0);
        try {
            Tag t = new Tag(Main.fileName);
            MyWindow.infoMusic.setText(t.getTitle());
        } catch (ID3Exception e1) {
            e1.printStackTrace();
        }
        if (player == null) {
            System.out.println("mauvais fichier");
            Main.fileName = null;
            return;
        }
            player.play();


        maxLen = player.getFramesNumber();
        MyWindow.play.setText("pause");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 1){
            JTable target = (JTable)e.getSource();
            int row = target.getSelectedRow();
            Main.fileName = MyWindow.listMusic.get(row).getPath();
        }
        if (e.getClickCount() == 2) {
            JTable target = (JTable)e.getSource();
            int row = target.getSelectedRow();
            Main.fileName = MyWindow.listMusic.get(row).getPath();

            if (Main.fileName == null)
                return;

            if (player == null) {
                startPlayer();
            } else if (player.getStatus() == Status.PAUSED) {
                player.resume();
                MyWindow.play.setText("pause");
            } else {
                player.stop();
                player = null;
                try {
                    Thread.sleep(100);
                    MyWindow.jSlider.setValue(0);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                startPlayer();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(player == null){
            return;
        }
        if(player.getStatus() == Status.PAUSED){
            pause = true;
        }
        else{
            player.pause();
            pause =false;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (player == null || MyWindow.jSlider.getValue() >=100) {
            return;
        }
        if (pause) {
        }
        else{
            player.resume();
        }
        //MyWindow.jSlider.getValue() );
        System.out.println(MyWindow.jSlider.getValue()*maxLen / 100);
        player.avanceTo(MyWindow.jSlider.getValue()*maxLen / 100);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}



