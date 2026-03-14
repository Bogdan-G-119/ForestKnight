package org.example;

import java.awt.*;

public class Player {
    static int x = 0;
    static int y = 0;

    static int width = 10;
    static int height = 10;

    public void draw(Graphics g){
        g.drawRect(x, y, width, height);
    }
}
