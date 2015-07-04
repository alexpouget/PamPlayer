package graphique.evenement;

import graphique.MyWindow;

import java.awt.Dialog.ModalityType;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.text.Utilities;

public class MyConnexion implements ActionListener {
	
	public static JTextField fieldLogin;
	public static JPasswordField fieldMdp;
	public static JLabel labelErreurCo;
	public static JDialog connexion;
	@Override
	public void actionPerformed(ActionEvent e) {
		
		 Frame[] frames=MyWindow.getFrames();
		 connexion=new JDialog(frames[0], true);
		 connexion.setLocationRelativeTo(null);
		 JPanel pan=new JPanel();
		 pan.setLayout(new FlowLayout());
		 JLabel labelLogin= new JLabel("Login: ");
		 fieldLogin= new JTextField(7);
		 JLabel labelMdp= new JLabel("Password: ");
		 fieldMdp= new JPasswordField(7);
		 labelErreurCo=new JLabel();
		 JButton buttonConnect= new JButton("Se connecter");
		 buttonConnect.addActionListener(new MyConnect());
		 pan.add(labelLogin);
		 pan.add(fieldLogin);
		 pan.add(labelMdp);
		 pan.add(fieldMdp);
		 pan.add(buttonConnect);
		 pan.add(labelErreurCo);
		 connexion.add(pan);
	
	
		 connexion.setTitle("CONNEXION");
	     connexion.setSize(400,100);
	     connexion.setVisible(true);
	    
	}
     

}
