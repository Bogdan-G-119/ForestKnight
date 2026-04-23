package org.example.core;

import javax.swing.*;
public class Game extends JFrame{
    static int width = 780;
    static int height = 514;
    public static void start(){
        JFrame frame = new JFrame("Forest Knight");

        GamePanel panel = new GamePanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(panel);

        frame.pack();

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}
