package Player;

import Main.Main;
import javazoom.jl.decoder.JavaLayerException;

/**
 * Created by alex on 29/05/2015.
 * "E:/pamPlayer/PamPlayer/src/com/company/legend.mp3"
 */
public class PlayerThread extends Thread {
    Mp3Player mp3Player;
    public static int pause;


    public void run(String file){

        try {
            System.out.println("pause : "+pause);
            Main.player.getPlayer().play(pause, Integer.MAX_VALUE);

        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void interrupt() {

        //pause = mp3Player.getPausedOnFrame();
        Main.player.getPlayer().stop();

    }

    public void reStart() {
        try {
            mp3Player.getPlayer().play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public Mp3Player getMp3Player() {
        return mp3Player;
    }

    public void setMp3Player(Mp3Player mp3Player) {
        this.mp3Player = mp3Player;
    }
}
