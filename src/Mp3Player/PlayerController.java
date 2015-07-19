package mp3Player;


import graphique.MyWindow;
import javazoom.jl.decoder.JavaLayerException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * Created by alex on 06/06/2015.
 *
 * class qui crée un objet player et implementes les methodes pour lancer et gerer ce player dans
 * un thread a part c'est pour cela qu'il implements la class runnable
 */
public class PlayerController implements Runnable {
    private SongPlayer player;
    private int position;
    private Status status = Status.INACTIVE;
    private Thread t;
    private String music;
    private PlayerListener listener;

    //constructeur
    public PlayerController(String fileName) {
        music = fileName;
    }

    @Override
    public void run() {
        //on set le status actif pour indiquer une lecture en cours
        status = Status.ACTIVE;
        try {
            if (player != null)
                //on fait appel a la method play de songplayer
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

    //pour la pause on stop le player et on stock l'emplacement ou on s'est arreté
    public void pause() {
        if (player == null)
            return;
        status = Status.PAUSED;
        position = player.getPosition();
        player.stop();
        player = null;
    }

    //pour lancer la musique on crée un objet songplayer et un thread
    public void play() {
        try {
            player = new SongPlayer(new FileInputStream(music), music);
            player.setListener(new PlayerListener());
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        t = new Thread(this);
        t.start();
    }

    //pour stopper on detruit le lecteur et on remet la position a 0
    public void stop() {
        if (player == null)
            return;
        status = Status.FINISHED;
        position = 0;
        player.stop();
        player.close();
        player = null;
        MyWindow.infoMusic.setText("");
    }

    public String getMusic() {
        return music;
    }

    public int getFramesNumber() {
        if (player == null)
            return -1;
        return player.getFrameNumber();
    }

    //method pour l'avance rapide
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
