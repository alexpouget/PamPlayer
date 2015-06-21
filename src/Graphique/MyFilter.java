package graphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

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
				String url= "https://news.google.fr/?output=rss&hl=fr&gl=fr&tbm=nws&authuser=0&q="+MyWindow.filtreNews.getText()+"+album&oq="+MyWindow.filtreNews.getText()+"album";
				newsRefresh=newsgeneration.NewsGenerator.rssParser(url);
			} catch (ParserConfigurationException | SAXException | IOException
					| JDOMException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	


}
