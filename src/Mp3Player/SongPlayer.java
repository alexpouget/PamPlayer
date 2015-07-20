package mp3player;

import javazoom.jl.decoder.*;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import main.Main;

/**
 * Created by alex on 06/06/2015.
 *
 * classe qui etends la classe Player de javazoom pour réecrire certaines de ses methodes
 *
 */
public class SongPlayer extends Player {
    private int maxFramesNumber;
    private Bitstream bitstream;
    private AudioDevice audioDevice;
    private Decoder decoder;
    private FileInputStream fileStream;
    private int currentFrame;
    private boolean isActive;
    private PlayerListener listener;

    //constructeur du player
    public SongPlayer(FileInputStream fileInputStream,String path) throws JavaLayerException, FileNotFoundException {
        super(fileInputStream);
        fileStream = fileInputStream;
        bitstream = new Bitstream(fileStream);
        maxFramesNumber = getFramesCount(path);
    }


    public int getPosition() {
        return currentFrame;
    }

    //parcour le fichier et retourne le nombre de frame
    private int getFramesCount(String path){
        Bitstream tmp = new Bitstream(getInputStream(path));
        int frame = 0;
        try {
            while (skipFrame(tmp)){
                frame++;
            }
            tmp.close();
        } catch (BitstreamException e1) {
            e1.printStackTrace();
            Main.logger.error(e1.getMessage());
        }
        return frame;
    }

    private InputStream getInputStream(String name) {
        try {
            return new FileInputStream(name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Main.logger.error(e.getMessage());
            return null;
        }
    }

    //return true si il reste des frame a lire
    private boolean skipFrame(Bitstream stream) throws BitstreamException {
        Header header = stream.readFrame();
        if (header != null) {
            stream.closeFrame();
            return true;
        } else {
            return false;
        }
    }

    // method de lecture end est le plus souvent a Max integer et on ne s'en sert pas
    public void play(int start, int end) throws JavaLayerException {
        isActive = true;
        //on avance dans le fichier tant que la frame n'est pas au niveau du start
        while (currentFrame < start) {
            skipFrame(bitstream);
            currentFrame++;
        }

        //on defini la sortie audio sur laquelle on va ecrire
        audioDevice = FactoryRegistry.systemRegistry().createAudioDevice();
        //on defini le decoder qui va lire le fichier frame par frame
        decoder = new Decoder();
        audioDevice.open(decoder);

        while(isActive && currentFrame < maxFramesNumber) {
            //decode frame et ecrit sur la sortie audio
            decodeFrame();
            currentFrame++;
            if(listener != null){
                //on fait avancer la frame dans listener
                listener.start(currentFrame);
            }
        }

        //on libere le buffer
        if (audioDevice != null) {
            audioDevice.flush();
        }

        //a la fin de la musique si elle n'est pas stopper on stop le listener et le player
        if(listener !=null && currentFrame >= maxFramesNumber){
            listener.stop();
            this.stop();
        }
    }

    //on arrete le lecteur et declare le boolean false
    public void stop()  {
        isActive = false;
    }

    public int getFrameNumber() {
        return maxFramesNumber;
    }

    public PlayerListener getListener() {
        return listener;
    }

    public void setListener(PlayerListener listener) {
        this.listener = listener;
    }
}
