package Graphique;

import javax.swing.*;
import java.awt.*;

/**
 * Created by alex on 26/05/2015.
 */
public class MyWindow extends JFrame {

    public MyWindow(){
        setSize(800,600);
        setTitle("PamPlayer");
        Container container = getContentPane();
        GridBagLayout gridBagLayout = new GridBagLayout();
        container.setLayout(gridBagLayout);

        addWindowListener(new MyEvent());
        JButton play = new JButton("play");
        container.add(play);
        JButton pause = new JButton("pause");
        container.add(pause);
        JButton re = new JButton("re");
        container.add(re);

        MyEvent myEvent = new MyEvent();
        play.addActionListener(myEvent);
        pause.addActionListener(myEvent);

        re.addActionListener(myEvent);
        addWindowListener(myEvent);


        setVisible(true);
    }

}
