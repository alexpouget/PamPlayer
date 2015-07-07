package mp3Player;

import javazoom.jl.decoder.*;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by alex on 06/06/2015.
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

    public SongPlayer(FileInputStream fileInputStream,String title) throws JavaLayerException, FileNotFoundException {
        super(fileInputStream);

        fileStream = fileInputStream;
        bitstream = new Bitstream(fileStream);
        maxFramesNumber = getFramesCount(title);

    }


    public int getPosition() {
        return currentFrame;
    }

    private int getFramesCount(String string){
        Bitstream tmp = new Bitstream(getInputStream(string));
        int frame = 0;
        try {
            while (skipFrame(tmp)){
                frame++;
            }
            tmp.close();
        } catch (BitstreamException e1) {
            e1.printStackTrace();
        }
        return frame;
    }

    private InputStream getInputStream(String name) {
        try {
            return new FileInputStream(name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public SongPlayer(InputStream inputStream, AudioDevice audioDevice) throws JavaLayerException {
        super(inputStream, audioDevice);
    }

    private boolean skipFrame(Bitstream stream) throws BitstreamException {
        Header header = stream.readFrame();
        if (header != null) {
            stream.closeFrame();
            return true;
        } else {
            return false;
        }
    }

    public void play(int start, int end) throws JavaLayerException {

        isActive = true;
        while (currentFrame < start) {
            skipFrame(bitstream);
            currentFrame++;
        }

        audioDevice = FactoryRegistry.systemRegistry().createAudioDevice();
        decoder = new Decoder();
        audioDevice.open(decoder);

        while(isActive && currentFrame < maxFramesNumber) {
            decodeFrame();
            currentFrame++;
            if(listener != null){
                listener.start(currentFrame);
            }
        }

        if (audioDevice != null) {
            audioDevice.flush();
        }
    }

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
