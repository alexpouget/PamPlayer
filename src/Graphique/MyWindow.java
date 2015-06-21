package graphique;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import javax.swing.tree.DefaultMutableTreeNode;

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

	public static JButton play;
	private JButton stop;
	public static JSlider jSlider;
	public static  JLabel infoMusic;
	public static Music[] listMusic;
	public static JTextField filtreNews= new JTextField(15);

	public MyWindow() throws ParserConfigurationException, SAXException, IOException, JDOMException {


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
		recherche.setBackground(Color.green);

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
		tab1.setPreferredSize(new Dimension(710, 400));
		tab1.setBackground(Color.red);

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
		onglets.addTab("News", tab4);
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

		/*--------------PANEL NEWS-------------------------*/

	
		ArrayList<News> news=new ArrayList<News>();
		news=NewsGenerator.rssParser("https://news.google.fr/?output=rss&hl=fr&gl=fr&tbm=nws&authuser=0&q=Feu+album&oq=Feu+album");
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
		recherche.add(filtreNews);
		recherche.add(filtrer);
		cp.add(biblio);
		cp.add(music);
		cp.add(player);
		cp.add(recherche); //j'ajoute le panel o� est le textField au container
		cp.add(nothing);
		//pack();
		setVisible(true);

	}

}



