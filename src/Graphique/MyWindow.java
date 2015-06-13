package graphique;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import graphique.evenement.MyEvent;
import graphique.evenement.OpenEvent;
import music.*;
import java.awt.*;
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

        ListMusic list = new ListMusic();
        listMusic = list.getList();

        Table table = new Table(listMusic);
        table.getTable().addMouseListener(new MyEvent());
        tab1.add(table.getjScrollPane());

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



