package mp3player;

import graphique.evenement.MyEvent;
import graphique.MyWindow;
import main.Main;
import mp3tag.Tag;

/**
 * Created by alex on 08/06/2015.
 */
public class PlayerListener {
    private String nextSong;

    public void start(int frame) {
        if (MyEvent.maxLen != 0) {
            //System.out.println(frame);
            MyWindow.jSlider.setValue(100 * frame / MyEvent.maxLen);
        }
    }

    public void stop() {

        MyWindow.jSlider.setValue(0);
        MyWindow.infoMusic.setText("");
        nextSong=next();
        MyEvent.player.stop();
        MyEvent.player = null;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        MyWindow.play.setText("play");
        if(nextSong == null){
            return;
        }
        Main.fileName =nextSong;
        MyEvent.player = new PlayerController(Main.fileName);
        MyWindow.jSlider.setValue(0);
        try {
            Tag t = new Tag(Main.fileName);
            MyWindow.infoMusic.setText(t.getTitle());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        if (MyEvent.player == null) {
            System.out.println("mauvais fichier");
            Main.fileName = null;
            return;
        }
        MyEvent.player.play();

        MyEvent.maxLen = MyEvent.player.getFramesNumber();
        MyWindow.play.setText("pause");

    }

    public String next(){
        for(int i = 0;MyWindow.listMusic.size()-1>i;i++){
            if(MyWindow.listMusic.get(i).getPath()==MyEvent.player.getMusic()){
                return MyWindow.listMusic.get(i+1).getPath();
            }
        }
        return null;
    }
}

