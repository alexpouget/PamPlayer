package graphique;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import graphique.evenement.MyEvent;
import graphique.evenement.OpenEvent;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.ParserConfigurationException;

import newsgeneration.News;
import newsgeneration.NewsGenerator;
import music.*;

import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by alex on 26/05/2015.
 */
public class MyWindow extends JFrame {
    public static JButton play;
    private JButton stop;
    public static JSlider jSlider;
    public static  JLabel infoMusic;
    public static ArrayList<Music> listMusic;
    public static JTextField filtreNews= new JTextField(15);

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
        //diffï¿½rents menus
        JMenu menu1 = new JMenu("Fichier");
        JMenu menu2 = new JMenu("Edition");
        //differents choix de chaque menu
        JMenuItem demarrer = new JMenuItem("Dï¿½marrer");
        JMenuItem addmusic = new JMenuItem("Ajouter music");
        addmusic.addActionListener(new OpenEvent());
        JMenuItem fin = new JMenuItem("Fin");
        JMenuItem annuler = new JMenuItem("Annuler");
        JMenuItem copier = new JMenuItem("Copier");
        JMenuItem coller = new JMenuItem("Coller");

        //Ajouter les choix au menu
        menu1.add(demarrer);
        menu1.add(fin);
        menu1.add(addmusic);
        menu2.add(annuler);
        menu2.add(copier);
        menu2.add(coller);
        //Ajouter les menu sur la bar de menu
        menu_bar1.add(menu1);
        menu_bar1.add(menu2);
        //Ajouter la bar du menu ï¿½ la frame
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

        ListMusic list = new ListMusic();
        listMusic = list.getList();

        Table table = new Table(listMusic);
        table.getTable().addMouseListener(new MyEvent());
        tab1.add(table.getjScrollPane());

        onglets.addTab("Bibliothï¿½que", tab1);
        onglets.addTab("Playlist", tab2);
        onglets.addTab("Mix", tab3);
        onglets.addTab("News", tab4);
        music.add(onglets);
	  /*-------------- FIN PANEL PRINCIPAL-------------*/

        
    	/*--------------PANEL BIBLIOTHEQUE-------------*/
        ArrayList<String> listArtist = new ArrayList<String>(); //contient les artistes présents dans la bibliotheque
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
            DefaultMutableTreeNode laBiblio = new DefaultMutableTreeNode("Bibliothèque");
           
            //parcours de la liste des artistes afin de créé un noeud à pour chaque artiste
            for (String artist : listArtist) {
            	if(artist==null)
            		continue;
                DefaultMutableTreeNode artiste = new DefaultMutableTreeNode(""+artist); 
                ArrayList<String> listArtistAlbum = new ArrayList<String>();
                
                //parcours de la liste des musiques afin de trouver les musiques correspondant à l'artiste courant
                for(Music song: listMusic){
                	if(song.getAlbum()==null)
                		continue;
                	String artist2=song.getArtiste().getName().toUpperCase().trim();
                	//on récupère les albums de l'artiste et on les met dans la liste listArtistAlbum
                	if(artist2.equals(artist)){
                    	if(listArtistAlbum.contains(song.getAlbum().getName()))
                    		continue;
                    	else
                    		listArtistAlbum.add(song.getAlbum().getName());
                	}
                }
                //on parcourt la liste des albums de l'artiste courant afin de créer un noeud pour chaque album
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
            arbre.addMouseListener(new MyMouse());
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
        String[] listNews= new String[news.size()*2];
        for(int i=0; i<news.size();i++)
        {
            listNews[i]=news.get(i).getTitre();
//			listNews[i+1]=news.get(i).getUrl();
//			i++;

        }
        JList listAlbums= new JList(news.toArray());
        double dim=tab1.getPreferredSize().getWidth();
        Dimension dimension=new Dimension((int) dim, 500);
        listAlbums.setPreferredSize(dimension);

        tab4.add(listAlbums);


        listAlbums.addMouseListener(new MyMouse());

        // champs de filtre des news


        //filtreNews.addKeyListener(new KeyListener());
        //filtreNews.addKeyListener(new MyFilter());
        final JButton filtrer= new JButton("Filtrer News");
        System.out.println(MyFilter.newsRefresh);
        filtrer.addActionListener(new MyFilter());
        if(MyFilter.newsRefresh!=null)
        {

            news=MyFilter.newsRefresh;
//		listNews=new String[news.size()*2];
//		for(int i=0; i<news.size();i++)
//		{
//			listNews[i]=news.get(i).getTitre();
////			listNews[i+1]=news.get(i).getUrl();
////			i++;
//
//		}
//		listAlbums= new JList(news.toArray());
//		dim=tab1.getPreferredSize().getWidth();
//		dimension=new Dimension((int) dim, 500);
//		listAlbums.setPreferredSize(dimension);
//
//		tab4.add(listAlbums);
            listAlbums.revalidate();

        }

        //filtrer.addActionListener(new MyFilter(filtreNews.getText()));
        filtreNews.setLocation(500,403);
        //filtrerNews.add(filtreNews);
        recherche.add(filtreNews);
//		cp.add(filtrerNews);
		/*--------------FIN PANEL NEWS-------------------------*/
        recherche.add(txtRechercher); //je met le textfield dans le panel
        recherche.add(btnRechercher); //je met le bouton dans le panel
        cp.add(biblio);
        cp.add(music);
        cp.add(player);
        cp.add(recherche); //j'ajoute le panel oï¿½ est le textField au container
        cp.add(nothing);
        //pack();
        setVisible(true);

    }
}



