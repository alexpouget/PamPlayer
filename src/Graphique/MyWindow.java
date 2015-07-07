package graphique;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import graphique.evenement.MyConnexion;
import graphique.evenement.MyEvent;
import graphique.evenement.MySynchro;
import graphique.evenement.OpenEvent;

import javax.xml.parsers.ParserConfigurationException;

import newsgeneration.News;
import newsgeneration.NewsGenerator;
import music.Music;
import music.ListMusic;
import music.Album;
import music.Artiste;

import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import graphique.evenement.MyEvent;
import graphique.evenement.OpenEvent;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import newsgeneration.News;
import newsgeneration.NewsGenerator;
import music.*;

import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Created by alex on 26/05/2015.
 */
public class MyWindow extends JFrame {


    public static ArrayList<Music> listMusic;

    public static JButton play;
    private JButton stop;
    public static JSlider jSlider;
    public static  JLabel infoMusic;
    public static  JLabel labelResultSynchro;
    public static JTextField filtreNews= new JTextField(15);
    public static JList listAlbums;
    public static JMenuItem connect;

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
        JMenuItem addmusic = new JMenuItem("Ajouter music");
        connect = new JMenuItem("Se connecter");
        connect.addActionListener(new MyConnexion());
        addmusic.addActionListener(new OpenEvent());
        JMenuItem addAlbum = new JMenuItem("Ajouter album");
        addAlbum.addActionListener(new OpenEvent());
        JMenuItem fin = new JMenuItem("Fin");
        JMenuItem annuler = new JMenuItem("Annuler");
        JMenuItem copier = new JMenuItem("Copier");
        JMenuItem coller = new JMenuItem("Coller");

        //Ajouter les choix au menu
        menu1.add(demarrer);
        menu1.add(fin);
        menu1.add(addmusic);
        menu1.add(addAlbum);
        menu1.add(connect);
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
        tab4.setPreferredSize(new Dimension(710, 400));
        tab1.setPreferredSize(new Dimension(710, 520));
        tab5.setPreferredSize(new Dimension(310, 400));

        ListMusic list = new ListMusic();
        listMusic = list.getList();

        Table table = new Table(listMusic);
        table.getTable().addMouseListener(new MyEvent());
        tab1.add(table.getjScrollPane());

        onglets.addTab("Biblioth�que", tab1);
        onglets.addTab("Playlist", tab2);
        onglets.addTab("Mix", tab3);
        onglets.addTab("News", tab4);
        onglets.addTab("Synchronisation",tab5);
        music.add(onglets);
	  /*-------------- FIN PANEL PRINCIPAL-------------*/

        
    	/*--------------PANEL BIBLIOTHEQUE-------------*/
        ArrayList<String> listArtist = new ArrayList<String>(); //contient les artistes pr�sents dans la bibliotheque
        //ArrayList<String> listAlbum = new ArrayList<String>();
        for (Music elem : listMusic) {
        	if(elem.getArtiste()==null)
        		continue;
        	String artist=elem.getArtiste().getName().toUpperCase().trim();
        	if(listArtist.contains(artist))
        		continue;
        	else 
        		listArtist.add(artist);
        }
                
            //Creation de la racine
            DefaultMutableTreeNode laBiblio = new DefaultMutableTreeNode("Biblioth�que");
           
            //parcours de la liste des artistes afin de cree un noeud pour chaque artiste
            for (String artist : listArtist) {
            	if(artist==null)
            		continue;
                DefaultMutableTreeNode artiste = new DefaultMutableTreeNode(""+artist); 
                ArrayList<String> listArtistAlbum = new ArrayList<String>();
                
                //parcours de la liste des musiques afin de trouver les musiques correspondant a l'artiste courant
                for(Music song: listMusic){
                	if(song.getAlbum()==null)
                		continue;
                	String artist2=song.getArtiste().getName().toUpperCase().trim();
                	//on recupere les albums de l'artiste et on les met dans la liste listArtistAlbum
                	if(artist2.equals(artist)){
                    	if(listArtistAlbum.contains(song.getAlbum().getName()))
                    		continue;
                    	else
                    		listArtistAlbum.add(song.getAlbum().getName());
                	}
                }
                //on parcourt la liste des albums de l'artiste courant afin de creer un noeud pour chaque album
	            for (String theAlbum: listArtistAlbum) {
	            	if(theAlbum==null)
	            		continue;
	                   DefaultMutableTreeNode album = new DefaultMutableTreeNode(theAlbum);
	                   artiste.add(album);
	                }
                laBiblio.add(artiste);
            }
            //creation de l'arbre avec la taille par defaut
            JTree arbre = new JTree(laBiblio);
            //arbre.addMouseListener(new MyMouse());
            JScrollPane tree = new JScrollPane(arbre);
            tree.setPreferredSize(new Dimension(240, 547));
            biblio.add(tree);
    	/*--------------FIN PANEL BIBLIOTHEQUE-------------*/
/*--------------PANEL NEWS-------------------------*/


        ArrayList<News> news=new ArrayList<News>();
        try {
            news=NewsGenerator.rssParser("https://news.google.fr/?output=rss&hl=fr&gl=fr&tbm=nws&authuser=0&q=Feu+album&oq=Feu+album");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }
        listAlbums= new JList(news.toArray());
        double dim=tab1.getWidth();
        Dimension dimension=new Dimension(710, 200);
        listAlbums.setPreferredSize(dimension);
        //listAlbums.setBackground(Color.RED);
        //listAlbums.setSize(300, 200);
        //listAlbums.setSize(100, 300);
        //listAlbums.setSize(new Dimension(410, 200));
        tab4.add(listAlbums);


        listAlbums.addMouseListener(new MyMouse());


        final JButton filtrer= new JButton("Filtrer News");
        filtrer.addActionListener(new MyFilter());

        JPanel panelFiltre= new JPanel();
        panelFiltre.add(filtreNews);
        panelFiltre.add(filtrer);

        tab4.add(panelFiltre);
		/*--------------FIN PANEL NEWS-------------------------*/
        /*------------------------PANEL SYNCHRO-------------------*/
      
        JPanel panelSynchro= new JPanel();
        labelResultSynchro= new JLabel();
        
        JButton buttonSynchro= new JButton("Lancer Synchro musique");
        panelSynchro.add(buttonSynchro);
       // buttonSynchro.setLocation(500, 300);
        buttonSynchro.addActionListener(new MySynchro());
        panelSynchro.setLocation(300, 500);
        tab5.add(panelSynchro);
        tab5.add(labelResultSynchro);
        /*------------------------FIN PANEL SYNCHRO-------------------*/
        
        /*------------------------POP UP DE CONNEXIOn-----------------*/
       
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



