package Graphique;

import mp3.PlayerThread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

/**
 * Created by alex on 26/05/2015.
 */
public class MyEvent extends WindowAdapter implements ActionListener {
   // Mp3Player mp3Player  = new Mp3Player("E:/pamPlayer/PamPlayer/src/com/company/legend.mp3");
    PlayerThread thread = new PlayerThread();

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println(e.getActionCommand().toString());
        if(e.getActionCommand().toString().equals("play")) {
            System.out.println("bn");
            thread.run();
        }
        if(e.getActionCommand().toString().equals("pause")){
            System.out.println(Main.thread.getName());
            thread.interrupt();

            // mp3Player.Pause();
        }

    }
}
