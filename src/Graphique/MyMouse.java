package graphique;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JList;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import newsgeneration.NewsGenerator;

public class MyMouse extends MouseAdapter {
		public void mouseClicked(MouseEvent evt) {
	        if (evt.getClickCount() == 2) {
	        	JList list=(JList)evt.getSource();
	        	String row= list.getSelectedValue().toString();
	        	try {
					NewsGenerator.Redirect(row);
	        		
				} catch (ParserConfigurationException | SAXException
						| IOException | JDOMException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            // Double-click detected
	        } else if (evt.getClickCount() == 3) {
	        }
		}
}

