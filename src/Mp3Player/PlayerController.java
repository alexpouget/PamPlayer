package Mp3Player;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.net.ssl.SSLEngineResult;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by alex on 06/06/2015.
 */
public class PlayerController implements Runnable {
    private PlayerListener listener;
    private SongPlayer player;
    private int currentPos;
    private Status status = Status.INACTIVE;
    private Thread t;
    private String music;

    public PlayerController(String fileName) {
        music = fileName;
    }

    @Override
    public void run() {
        status = Status.ACTIVE;

        try {
            if (player != null)
                player.play(currentPos, Integer.MAX_VALUE);
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public Status getStatus() {
        return status;
    }

    public void resume() {
        play();
    }

    public void pause() {
        if (player == null)
            return;
        status = Status.PAUSED;
        currentPos = player.getPosition();
        player.stop();
        player = null;
    }

    public void play() {
        try {
            player = new SongPlayer(new FileInputStream(music));
            if (listener != null)
                player.addListener(listener);
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        t = new Thread(this);
        t.start();
    }

    public void setListener(PlayerListener playerListener){
        listener = playerListener;
    }

    public void stop() {

        if (player == null)
            return;
        status = Status.FINISHED;
        currentPos = 0;
        player.stop();
        player.close();
        player = null;

    }

    public int getFramesNumber() {
        if (player == null)
            return -1;
        return player.getFrameNumber();
    }

    public void skip(int s) {
        currentPos += (s - currentPos);
    }



}
