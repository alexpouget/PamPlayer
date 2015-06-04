package Player;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by alex on 26/05/2015.
 */
public class Mp3Player{

    private AdvancedPlayer player;
    private FileInputStream fileInputStream;
    private BufferedInputStream bufferedInputStream;
    private File file;
    private Integer pausedOnFrame;
    private String url;

    public Mp3Player(String url) {
        this.url = url;
        try {
            fileInputStream = new FileInputStream(this.url);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            player = new AdvancedPlayer(bufferedInputStream);

            player.setPlayBackListener(new PlaybackListener() {
                @Override
                public void playbackStarted(PlaybackEvent event) {
                    System.out.println(PlayerThread.pause);
                    System.out.println(event.getSource());
                    System.out.println(event.getFrame());
                    System.out.println(event.getId());
                }
                
                public void playbackFinished(PlaybackEvent event) {
                    pausedOnFrame = event.getFrame();
                    PlayerThread.pause = event.getFrame();
                    System.out.println(PlayerThread.pause);
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }

    }

    public AdvancedPlayer getPlayer() {
        return player;
    }

    public void setPlayer(AdvancedPlayer player) {
        this.player = player;
    }

    public FileInputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(FileInputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public BufferedInputStream getBufferedInputStream() {
        return bufferedInputStream;
    }

    public void setBufferedInputStream(BufferedInputStream bufferedInputStream) {
        this.bufferedInputStream = bufferedInputStream;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Integer getPausedOnFrame() {
        return pausedOnFrame;
    }

    public void setPausedOnFrame(Integer pausedOnFrame) {
        this.pausedOnFrame = pausedOnFrame;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void playSound(){
        new Thread(){
            public void run() {
                pausedOnFrame = 0;
                try {

                    fileInputStream = new FileInputStream(getUrl());
                    bufferedInputStream = new BufferedInputStream(fileInputStream);
                    player = new AdvancedPlayer(bufferedInputStream);
                    player.setPlayBackListener(new PlaybackListener() {
                        @Override
                        public void playbackStarted(PlaybackEvent event) {
                            System.out.println(pausedOnFrame);
                            System.out.println(event.getSource());
                            System.out.println(event.getFrame());
                            System.out.println(event.getId());
                        }
                        @Override
                        public void playbackFinished(PlaybackEvent event) {
                            pausedOnFrame = event.getFrame();
                            System.out.println(pausedOnFrame);
                        }
                    });
                    player.play();

                } catch (JavaLayerException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void playSound(int frame){

        pausedOnFrame = frame;
        new Thread(){
            public void run(){
                try {
                    System.out.println(pausedOnFrame);
                    fileInputStream =new FileInputStream(getUrl());
                    bufferedInputStream =new BufferedInputStream(fileInputStream);
                    player=new AdvancedPlayer(bufferedInputStream);
                    player.setPlayBackListener(new PlaybackListener() {
                        @Override
                        public void playbackStarted(PlaybackEvent event) {
                            System.out.println(pausedOnFrame);
                            System.out.println(event.getSource());
                            System.out.println(event.getFrame());
                            System.out.println(event.getId());
                        }
                        @Override
                        public void playbackFinished(PlaybackEvent event) {
                            pausedOnFrame = event.getFrame();
                            System.out.println(pausedOnFrame);
                        }
                    });
                    player.play(pausedOnFrame,Integer.MAX_VALUE);

                } catch (JavaLayerException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void Pause() {

        if(!player.equals(null)) {

            player.close();

        }

    }





}
