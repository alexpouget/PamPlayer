package graphique;

import javax.swing.*;

import com.sun.javafx.scene.control.skin.TableColumnHeader;

import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import music.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.Iterator;

/**
 * Created by alex on 26/05/2015.
 */
public class MyWindow extends JFrame {
    public static JButton play;
    private JButton stop;
    public static JSlider jSlider;
    public static  JLabel infoMusic;
    public static Music[] listMusic;

    public MyWindow() {


        setTitle("PamPlayer");
        setSize(1000, 725);
        setResizable(false);
        setLocationRelativeTo(null);
        addWindowListener(new MyEvent());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Container cp = getContentPane();
        JPanel biblio = new JPanel();
        JPanel music = new JPanel();
        JPanel player = new JPanel();
        JPanel recherche = new JPanel();
        JPanel nothing = new JPanel();
        JButton btnRechercher = new JButton("Rechercher");
        JTextField txtRechercher = new JTextField(15);

        //biblio.setBackground(Color.orange);
        player.setBackground(Color.blue);
        //music.setBackground(Color.red);
        //recherche.setBackground(Color.green);

        biblio.setSize(245, 555);
        biblio.setLocation(5, 105);
        player.setSize(750, 100);
        player.setLocation(5, 0);
        music.setSize(725, 555);
        music.setLocation(255, 105);
        recherche.setSize(220, 100);
        recherche.setLocation(760, 0);

    /*---------------Player ----------------*/

    /*---------------fin Player----------------*/
        play = new JButton("play");
        stop = new JButton("stop");

        play.addActionListener(new MyEvent());
        stop.addActionListener(new MyEvent());

        jSlider = new JSlider(0, 100, 0);
        jSlider.addMouseListener(new MyEvent());

        infoMusic = new JLabel("");


        player.add(play);
        player.add(stop);

        player.add(jSlider);
        player.add(infoMusic);


	/*---------------BARRE DE MENU----------------*/
        //Bar de menu
        JMenuBar menu_bar1 = new JMenuBar();
        //diff�rents menus
        JMenu menu1 = new JMenu("Fichier");
        JMenu menu2 = new JMenu("Edition");
        //differents choix de chaque menu
        JMenuItem demarrer = new JMenuItem("D�marrer");
        JMenuItem fin = new JMenuItem("Fin");
        JMenuItem annuler = new JMenuItem("Annuler");
        JMenuItem copier = new JMenuItem("Copier");
        JMenuItem coller = new JMenuItem("Coller");

        //Ajouter les choix au menu
        menu1.add(demarrer);
        menu1.add(fin);
        menu2.add(annuler);
        menu2.add(copier);
        menu2.add(coller);
        //Ajouter les menu sur la bar de menu
        menu_bar1.add(menu1);
        menu_bar1.add(menu2);
        //Ajouter la bar du menu � la frame
        setJMenuBar(menu_bar1);
	 /*-------------FIN BARRE DE MENU--------*/

	/*--------------PANEL PRINCIPAL-------------*/
        JTabbedPane onglets = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        JPanel tab1 = new JPanel();
        JPanel tab2 = new JPanel();
        JPanel tab3 = new JPanel();
        JPanel tab4 = new JPanel();
        JPanel tab5 = new JPanel();
        tab1.setPreferredSize(new Dimension(710, 520));

        listMusic = new Music[]{new Music("Ressource/folder1/legend.mp3", "legend"), new Music("Ressource/folder1/729.mp3", "ya rien a faire")};
        //JList lstMusic = new JList(listMusic);

        final String[] column = new String[]{"Titre","Artiste","Album","durée"};
        AbstractTableModel model = new AbstractTableModel() {
            public int getColumnCount() { return 4 ; }
            public int getRowCount() { return listMusic.length; }
            public String getColumnName(int col) {
                return column[col];
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                if(columnIndex==0) {
                    return listMusic[rowIndex].getTitle();
                }
                if(columnIndex==1) {
                    if(listMusic[rowIndex].getArtiste()!=null) {
                        return listMusic[rowIndex].getArtiste().getName();
                    }
                }
                if(columnIndex==2) {
                    if(listMusic[rowIndex].getAlbum()!=null) {
                        return listMusic[rowIndex].getAlbum().getName();
                    }
                }
                if(columnIndex==3) {
                    return listMusic[rowIndex].getDuree()/1000;
                }
                return listMusic[rowIndex].getPath();
            }
        };

        JTable table = new JTable(model);

        table.addMouseListener(new MyEvent());
        JScrollPane spMusic = new JScrollPane(table);
        //spMusic.setRowHeaderView(rowHeader);
        tab1.add(spMusic);

        onglets.addTab("Biblioth�que", tab1);
        onglets.addTab("Playlist", tab2);
        onglets.addTab("Mix", tab3);
        onglets.addTab("Quizz", tab4);
        onglets.addTab("News", tab5);
        music.add(onglets);
	  /*-------------- FIN PANEL PRINCIPAL-------------*/

	/*--------------PANEL BIBLIOTHEQUE-------------*/
        //Cr�ation de la racine
        DefaultMutableTreeNode laBiblio = new DefaultMutableTreeNode("Biblioth�que");

        //ajout des branches � la racine
        for (int i = 1; i < 15; i++) {
            DefaultMutableTreeNode artiste = new DefaultMutableTreeNode("Artiste");

            //On rajoute 15 branches
            if (i < 15) {
                DefaultMutableTreeNode album = new DefaultMutableTreeNode("Album");
                artiste.add(album);
            }
            //On ajoute l'album � l'artiste
            laBiblio.add(artiste);
        }
        //cr�ation de l'arbre avec la taille par d�faut
        JTree arbre = new JTree(laBiblio);
        arbre.addMouseListener(new MyMouse());
        JScrollPane tree = new JScrollPane(arbre);
        tree.setPreferredSize(new Dimension(240, 547));
        biblio.add(tree);
	/*--------------FIN PANEL BIBLIOTHEQUE-------------*/

        
    
        recherche.add(txtRechercher); //je met le textfield dans le panel
        recherche.add(btnRechercher); //je met le bouton dans le panel
        cp.add(biblio);
        cp.add(music);
        cp.add(player);
        cp.add(recherche); //j'ajoute le panel o� est le textField au container
        cp.add(nothing);
        //pack();
        setVisible(true);

    }
}



