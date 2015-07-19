package graphique;

import java.awt.*;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.swing.JList;
import javax.xml.parsers.ParserConfigurationException;


import newsgeneration.News;
import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import newsgeneration.News;
import newsgeneration.NewsGenerator;

public class MyMouse extends MouseAdapter {
		public void mouseClicked(MouseEvent evt) {

			
			// redirection vers le site pour l'écran des news lors du double clique sur une news
	        if (evt.getClickCount() == 2) {
	        	JList list=(JList)evt.getSource();

	        	News news= (News) list.getSelectedValue();
	        	Properties sys = System.getProperties();
				String os = sys.getProperty("os.name");
				Runtime r = Runtime.getRuntime();
				if(Desktop.isDesktopSupported())
				{
				  try {
					Desktop.getDesktop().browse(new URI(news.getUrl()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                }

		}
}
}

