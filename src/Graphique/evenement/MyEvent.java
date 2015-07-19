package graphique.evenement;

import graphique.MyWindow;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import main.Main;
import mp3Player.PlayerController;
import mp3Player.Status;
import mp3tag.Tag;
import music.ListMusic;
import music.Music;
import speechReco.SpeechReco;
import javax.swing.event.ChangeEvent;

/**
 * Created by alex on 26/05/2015.
 *
 * Traite les evenement du tableau bibliotheque
 */
public class MyEvent  extends WindowAdapter implements ChangeListener,ActionListener, MouseListener {
    public static PlayerController player;
    public static int maxLen;
    private boolean pause;
    private boolean speechActive;
    private Thread t;
    private JDialog error;

    @Override
    public void windowClosing(WindowEvent e) {
        int rep = JOptionPane.showConfirmDialog(null, "Quitter PamPlayer ?");
        if(rep==0)
            System.exit(0);
    }

        public void actionPerformed(ActionEvent e) {
            //click sur le bouton stop
            if (e.getActionCommand()=="stop"){
                if (player == null) {
                    return;
                }
                player.stop();
                player = null;
                MyWindow.play.setText("play");
                try {
                    Thread.sleep(100);
                    MyWindow.jSlider.setValue(0);
                    MyWindow.infoMusic.setText("");
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
            //click sur le bouton play/pause
            if(e.getActionCommand()=="play" || e.getActionCommand()=="pause") {
                if(Main.fileName == null) {
                    Main.fileName = MyWindow.listMusic.get(0).getPath();
                }
                if (Main.fileName == null)
                    return;
                if (player == null) {
                    startPlayer();
                }else if (player.getStatus()== Status.PAUSED) {
                    player.resume();
                    MyWindow.play.setText("pause");
                } else {
                    player.pause();
                    MyWindow.play.setText("play");
                }
            }
            //click sur activer la reconaissance vocale
            if(e.getActionCommand()=="Reconaissance vocale") {
            	if(speechActive!=true){
            		speechActive = true;
                    t = new Thread() {
                        public void run(){
                            SpeechReco speechReco = new SpeechReco();
                            speechReco.run();
                        }
                    };t.start();
            	}else{
            		speechActive = false;
            		t.interrupt();
            		t=null;
            	}
            }
            // click sur next
            if(e.getActionCommand()=="||>") {
                for(int i = 0;MyWindow.listMusic.size()-1>i;i++){
                    if(MyWindow.listMusic.get(i).getPath()== player.getMusic()){
                        Main.fileName = MyWindow.listMusic.get((i+1)).getPath();
                        if (player != null) {
                            player.stop();
                            player = null;
                            MyWindow.play.setText("play");
                            try {
                                Thread.sleep(100);
                                MyWindow.jSlider.setValue(0);
                                MyWindow.infoMusic.setText("");
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                        }
                        startPlayer();
                        break;
                    }
                }
            }
            //click sur previous
            if(e.getActionCommand()=="<||") {
                for(int i = 1;MyWindow.listMusic.size()>i;i++){
                    if(MyWindow.listMusic.get(i).getPath()== player.getMusic()){
                        Main.fileName = MyWindow.listMusic.get(i-1).getPath();
                        if (player != null) {
                            player.stop();
                            player = null;
                            MyWindow.play.setText("play");
                            try {
                                Thread.sleep(100);
                                MyWindow.jSlider.setValue(0);
                                MyWindow.infoMusic.setText("");
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                        }
                        startPlayer();
                        break;
                    }
                }
            }
        }

    //method startPlayer pour cree un player
    public void startPlayer() {
         player = new PlayerController(Main.fileName);
         MyWindow.jSlider.setValue(0);
        try {
            Tag t = new Tag(Main.fileName);
            MyWindow.infoMusic.setText(t.getTitle());
        } catch (Exception e1) {
            notPresent();
            return;
        }
        if (player == null) {
            System.out.println("mauvais fichier");
            Main.fileName = null;
            return;
        }
        player.play();
        maxLen = player.getFramesNumber();
        MyWindow.play.setText("pause");
    }


    //gere le cas ou la musique n'a pas ete trouver
    public void notPresent(){
        Frame[] frames=MyWindow.getFrames();
        error =new JDialog(frames[0], true);
        error.setLocationRelativeTo(null);
        JPanel pan=new JPanel();
        pan.setLayout(new FlowLayout());
        JLabel labelLogin= new JLabel("La musique n'a pas été trouvez voulez vous la supprimmez ? \n");
        JButton buttonConnect= new JButton("Supprimez");
        buttonConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = MyWindow.tableau.getSelectedRow();
                Object cellule = MyWindow.tableau.getValueAt(row, 0);
                Music music = null;
                for(Music m : MyWindow.listMusic)
                {
                    if(m.getTitle().equals(cellule.toString()))
                        music = m;
                }

                ListMusic listMusic = new ListMusic();
                listMusic.removeMusic(music);
                MyWindow.persoTableModel.removeMusic(music);
                error.dispose();
            }
        });
        pan.add(labelLogin);
        pan.add(buttonConnect);
        error.add(pan);

        error.setTitle("Erreur");
        error.setSize(400,100);
        error.setVisible(true);
    }

    //gere les evenment de click
    @Override
    public void mouseClicked(MouseEvent e) {
        //on commence par recuperer l'objet sur lequel on a clicker
    	String path=null;
    	if(e.getComponent()==MyWindow.tableau)
    	{
    	int row = MyWindow.tableau.getSelectedRow();
        Object cellule = MyWindow.tableau.getValueAt(row, 0);
        path = null;
        for(Music music : MyWindow.listMusic)
        {
        	if(music.getTitle().equals(cellule.toString()))
        		path = music.getPath();
        }
    	}
    	if(e.getComponent()==MyWindow.tableauPlaylist)
        {
         	int row = MyWindow.tableauPlaylist.getSelectedRow();
         	Object cellule = MyWindow.tableauPlaylist.getValueAt(row, 0);
         	  for(Music music : MyWindow.listMusic)
               {
         		
         			 
               	if(music.getTitle().equals(cellule.toString()))
               	{
               		path = music.getPath();
               		break;
               	}
               		
               }
         	
        }
        //double click on set la musique et on lance
        if (e.getClickCount() == 2) {
            Main.fileName = path;
            if (Main.fileName == null)
                return;

            if (player == null) {
                startPlayer();
            } else if (player.getStatus() == Status.PAUSED) {
                player.resume();
                MyWindow.play.setText("pause");
            } else {
                player.stop();
                player = null;
                try {
                    Thread.sleep(100);
                    MyWindow.jSlider.setValue(0);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                startPlayer();
            }
        }
        //un seul click ou premier evenement d'un double click
        //on definit dans filename le path de la musique selectionner
        if(e.getClickCount() == 1){
            Main.fileName = path;
        }

    }

    // en cas de click sur le jslide
    @Override
    public void mousePressed(MouseEvent e) {
        if(player == null){
            return;
        }
        if(player.getStatus() == Status.PAUSED){
            pause = true;
        }
        else{
            player.pause();
            pause =false;
        }

    }

    //quand le click sur le jslide est relacher
    @Override
    public void mouseReleased(MouseEvent e) {
        if (player == null || MyWindow.jSlider.getValue() >=100) {
            return;
        }
        if (pause) {
        }
        else{
            player.resume();
        }
        player.avanceTo(MyWindow.jSlider.getValue()*maxLen / 100);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}



