package Graphique;


import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by alex on 26/05/2015.
 */
public class MyWindow extends JFrame {
    public static JButton play;
    private JButton stop;
    public static JSlider jSlider;

    public MyWindow(){

        setSize(400, 120);
        setTitle("PamPlayer");
        play = new JButton("play");
        stop = new JButton("stop");

        play.setPreferredSize(new Dimension(60, 30));
        stop.setPreferredSize(new Dimension(60, 30));

        play.addActionListener(new MyEvent());
        stop.addActionListener(new MyEvent());

        jSlider = new JSlider(0, 100, 0);
        //jSlider.addMouseListener();

        JPanel jPanel1 = new JPanel();
        jPanel1.add(play);
        jPanel1.add(stop);

        JPanel jPanel = new JPanel();
        jPanel.add(jSlider);

        JPanel mainControl = new JPanel(new GridLayout(1, 2));

        mainControl.add(jPanel);
        mainControl.add(jPanel1);

        add(mainControl,BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

}
