package newsgeneration;

import graphique.MyWindow;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import javax.swing.JList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.SAXException;

import com.sun.xml.internal.stream.buffer.sax.SAXBufferCreator;

import java.io.*;
import java.util.List;
import java.util.Iterator;


public class NewsGenerator {

	public static void Redirect(String keyWord) throws ParserConfigurationException, SAXException, IOException, JDOMException {

		
		String url= "https://news.google.fr/?output=rss&hl=fr&gl=fr&tbm=nws&authuser=0&q="+keyWord+"+album&oq="+keyWord+"album";
		rssParser(url);

	}        
	
	public static ArrayList<News> rssParser(String urlFluxRss) throws ParserConfigurationException, SAXException, IOException, JDOMException
	{
		Document document;
		Element racine;


		//urlFluxRss= "https://news.google.fr/?output=rss&hl=fr&gl=fr&tbm=nws&authuser=0&q=Feu+album&oq=Feu+album";
		URL url2 = new URL(urlFluxRss);


		//On crée une instance de SAXBuilder
		SAXBuilder sxb= new SAXBuilder();
		document=sxb.build(url2);
		racine = document.getRootElement();

		//On crée une List contenant tous les noeuds "etudiant" de l'Element racine
	
		List<Element> listItem= new ArrayList<Element>();
		ArrayList<String> listTitre= new ArrayList<String>();
		ArrayList<News> listNews= new ArrayList<News>();
		ArrayList<String> listUrl= new ArrayList<String>();

		// on recupere les les enfants de channel
		listItem=racine.getChild("channel").getChildren("item");
	


		for(int i=0;i<listItem.size();i++)
		{
			
			// on recupére pour chaque item son title et son lien et on met dans un objet news
			News n= new News(listItem.get(i).getChildText("title"),listItem.get(i).getChildText("link").substring(listItem.get(i).getChildText("link").indexOf("&url")+5, (listItem.get(i).getChildText("link").lastIndexOf("/"))));
			listNews.add(n);
		}
	
		return listNews;


	}


	// méthode de rechargement des news utilisée après l'ajout d'une musique à la bibliotheque
	public static void rechargerNews(){
		ArrayList<News> newsRefresh=new ArrayList<News>();
		int nbr;
		Random randomGenerator = new Random();
		nbr = MyWindow.listMusic.size();
		int randInt = randomGenerator.nextInt(nbr);
		String url="https://news.google.fr/?output=rss&hl=fr&gl=fr&tbm=nws&authuser=0&q="+MyWindow.listMusic.get(randInt).getArtiste().getName()+"+&oq="+MyWindow.listMusic.get(randInt).getArtiste().getName();

		try {
			newsRefresh=newsgeneration.NewsGenerator.rssParser(url);
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JDOMException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        MyWindow.listAlbums.setListData(newsRefresh.toArray());
	}
}






