package org.example;

import java.awt.*;

public class Player {
    int x = 0;
    int y = 0;

    int width = 10;
    int height = 10;

    public void draw(Graphics g){
        g.drawRect(x, y, width, height);
    }
}
