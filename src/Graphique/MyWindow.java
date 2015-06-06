package Graphique;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class MyWindow extends JFrame {
	public MyWindow(){
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
	player.setLocation(5,0);
	music.setSize(725, 555);
	music.setLocation(255, 105);
	recherche.setSize(220, 100);
	recherche.setLocation(760, 0);

	/*---------------BARRE DE MENU----------------*/
			//Bar de menu
	JMenuBar menu_bar1 = new JMenuBar();
			//différents menus 
	JMenu menu1 = new JMenu("Fichier");
	JMenu menu2 = new JMenu("Edition");
			//differents choix de chaque menu
	JMenuItem demarrer = new JMenuItem("Démarrer");
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
			//Ajouter la bar du menu à la frame 
	setJMenuBar(menu_bar1);
	 /*-------------FIN BARRE DE MENU--------*/
	
	/*--------------PANEL PRINCIPAL-------------*/
	JTabbedPane onglets = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
	JPanel tab1 = new JPanel();
	JPanel tab2 = new JPanel();
	JPanel tab3 = new JPanel();
	JPanel tab4 = new JPanel();
	tab1.setPreferredSize(new Dimension(710, 520));
	
	String[] lesMusiques = {"uneChanson", "uneAutreChanson", "encoreUne"};
	JList lstMusic = new JList(lesMusiques);
    lstMusic.addMouseListener(new MyMouse());
	JScrollPane spMusic = new JScrollPane(lstMusic); 
	tab1.add(spMusic);
	
	onglets.addTab( "Bibliothèque", tab1 );
	onglets.addTab( "Playlist", tab2 );
	onglets.addTab( "Mix", tab3 );
	onglets.addTab( "Quizz", tab4 );
	music.add(onglets);
	  /*-------------- FIN PANEL PRINCIPAL-------------*/
	
	/*--------------PANEL BIBLIOTHEQUE-------------*/
	//Création de la racine
	  DefaultMutableTreeNode laBiblio = new DefaultMutableTreeNode("Bibliothèque");
	      
	  //ajout des branches à la racine
	  for(int i = 1; i < 15; i++){
	    DefaultMutableTreeNode artiste = new DefaultMutableTreeNode("Artiste");
	         
	    //On rajoute 15 branches
	    if(i < 15){   
	      DefaultMutableTreeNode album = new DefaultMutableTreeNode("Album");
	      artiste.add(album);
	    }
	    //On ajoute l'album à l'artiste
	    laBiblio.add(artiste);
	  }
	  //création de l'arbre avec la taille par défaut
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
	cp.add(recherche); //j'ajoute le panel où est le textField au container
	cp.add(nothing);
	//pack();
    setVisible(true);
}
}

