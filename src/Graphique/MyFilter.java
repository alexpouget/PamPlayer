package graphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.xml.parsers.ParserConfigurationException;

import newsgeneration.News;

import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

public class MyFilter implements ActionListener {
	  static ArrayList<News> newsRefresh=new ArrayList<News>();
	
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			System.out.println(MyWindow.filtreNews.getText());
			try {
				String url= "https://news.google.fr/?output=rss&hl=fr&gl=fr&tbm=nws&authuser=0&q="+MyWindow.filtreNews.getText()+"&oq="+MyWindow.filtreNews.getText();
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
