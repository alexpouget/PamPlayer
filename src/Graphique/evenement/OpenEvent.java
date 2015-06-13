package graphique.evenement;

import graphique.MyWindow;
import graphique.Table;
import javafx.stage.FileChooser;
import music.ListMusic;
import music.Music;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by alex on 11/06/2015.
 */
public class OpenEvent implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser dialogue = new JFileChooser(new File("."));
        Music music;
        if (dialogue.showOpenDialog(null)== JFileChooser.APPROVE_OPTION) {
            music = new Music(dialogue.getSelectedFile().getPath(), dialogue.getSelectedFile().getName());
            Boolean isPresent = false;
            for (int i = 0; i < MyWindow.listMusic.size(); i++) {
                if (MyWindow.listMusic.get(i).getTitle().equals(music.getTitle())) {
                    isPresent = true;
                }
            }
            if(!isPresent){
                ListMusic listMusic = new ListMusic();
                listMusic.addMusic(music);
            }
            //ListMusic list = new ListMusic();
            //MyWindow.listMusic = list.getList();
            //Table nTable = new Table(MyWindow.listMusic);

            //MyWindow.tab1.add(nTable.getjScrollPane());
            //MyWindow.tab1.repaint();

        }
    }
}
