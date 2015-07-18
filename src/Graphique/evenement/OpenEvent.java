package graphique.evenement;

import graphique.MyWindow;
import mp3tag.Tag;
import music.Album;
import music.Artiste;
import music.ListMusic;
import music.Music;

import org.blinkenlights.jid3.ID3Exception;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.WatchService;

/**
 * Created by alex on 11/06/2015.
 */
public class OpenEvent implements ActionListener {
    JFileChooser dialogue;
    @Override
    public void actionPerformed(ActionEvent e) {

        Music music;
        if (e.getActionCommand().equalsIgnoreCase("Ajouter music")) {
             dialogue = new JFileChooser(new File("."));

            if (dialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                music = new Music(dialogue.getSelectedFile().getPath(), dialogue.getSelectedFile().getName());
                addToList(music);
                
                /* Creation d'un defaultTreeModel charg� sur le JTree de MyWindow. On cree le noeud
                 correspondant � l'artiste et celui de l'album de la musique selectionee.
                 */
                
                MyWindow.listArtist.add(music.getArtiste().getName().toUpperCase().trim());
                
                MyWindow.listAlbum.add(music.getAlbum().toString().trim());
                
                DefaultTreeModel model = (DefaultTreeModel)MyWindow.arbre.getModel();
                
                DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
                
                DefaultMutableTreeNode artiste = new DefaultMutableTreeNode(music.getArtiste().getName().toUpperCase().trim());
                
                DefaultMutableTreeNode album = new DefaultMutableTreeNode(music.getAlbum().getName().trim());
                
                boolean newNodeArtist=true;
				boolean isAlbum = false;
                for (int i = 0; i < root.getChildCount(); i++)
        		{
        			if (root.getChildAt(i).toString().equals(music.getArtiste().getName().toUpperCase().trim())){
						newNodeArtist=false;
        				DefaultMutableTreeNode noeud = (DefaultMutableTreeNode)root.getChildAt(i);
        					for(int j = 0; j < noeud.getChildCount(); j++)
        					{
        						if(!isAlbum){
	        						if(noeud.getChildAt(j).toString().equals(music.getAlbum().getName().trim())){
	        							isAlbum=true;
	        						}
	        						else{
	        						continue;
	        						}
        						}
        					}
        					if(!isAlbum){
            					noeud.add(album);
    	        				MyWindow.arbre.updateUI();
    	        				}
        					if(isAlbum){
        						MyWindow.arbre.updateUI();
        					}
        			}
        			else 
        				continue;
        		}	
                if(newNodeArtist){
                root.add(artiste);
                artiste.add(album);
                model.reload(root);
            }
        }
        }
  
            
        if (e.getActionCommand().equalsIgnoreCase("Ajouter album")) {
            dialogue = new JFileChooser(new File("."));
            dialogue.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            dialogue.setAcceptAllFileFilterUsed(false);
            if (dialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File[] list = dialogue.getSelectedFile().listFiles();
                for (int i = 0; i < list.length; i++) {
                    if (list[i].getName().endsWith(".mp3")) {
                        addToList(new Music(list[i].getPath(), list[i].getName()));
                    }
                }
                
            } else {
                System.out.println("pas dossier selectionner");
            }
        }
        if (e.getActionCommand().equalsIgnoreCase("Fin")) {
            System.exit(0);
        }
    }
  
    public boolean isPresent(Music music) {
        for (int i = 0; i < MyWindow.listMusic.size(); i++) {
            if (MyWindow.listMusic.get(i).getTitle().equals(music.getTitle())) {
                return true;
            }
        }
        return false;
    }

    public void addToList(Music music) {
        try {
            Tag tag = new Tag(music.getPath());
            if (!(tag.getAlbum() == null)) {
                music.setAlbum(new Album(tag.getAlbum()));
            }
            if (!(tag.getArtiste() == null)) {
                music.setArtiste(new Artiste(tag.getArtiste()));
            }
            if (!tag.getTitle().equals(null)) {
                music.setTitle(tag.getTitle());
            }
        } catch (ID3Exception e1) {
            e1.printStackTrace();
        } catch (Exception e) {
			e.printStackTrace();
		}
        if (!isPresent(music)) {
            ListMusic listMusic = new ListMusic();
            listMusic.addMusic(music);
            MyWindow.listMusic.add(music);
            MyWindow.persoTableModel.addMusic(music);
        }
    }
}

