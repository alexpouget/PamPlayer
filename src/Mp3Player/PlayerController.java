package Mp3Player;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.net.ssl.SSLEngineResult;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by alex on 06/06/2015.
 */
public class PlayerController implements Runnable {
    private SongPlayer player;
    private int position;
    private Status status = Status.INACTIVE;
    private Thread t;
    private String music;
    private PlayerListener listener;

    public PlayerController(String fileName) {
        music = fileName;
    }

    @Override
    public void run() {
        status = Status.ACTIVE;

        try {
            if (player != null)
                player.play(position, Integer.MAX_VALUE);
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
        position = player.getPosition();
        player.stop();
        player = null;
    }

    public void play() {
        try {
            player = new SongPlayer(new FileInputStream(music),music);
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        t = new Thread(this);
        t.start();
    }


    public void stop() {

        if (player == null)
            return;
        status = Status.FINISHED;
        position = 0;
        player.stop();
        player.close();
        player = null;

    }

    public int getFramesNumber() {
        if (player == null)
            return -1;
        return player.getFrameNumber();
    }


    public void avanceTo(int i) {

        position = (i - position);
    }

    public void setListener(PlayerListener listener) {
        this.listener = listener;
    }

    public PlayerListener getListener() {
        return listener;
    }
}
