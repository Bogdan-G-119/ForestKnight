package org.example.input;

import java.awt.event.*;

public class InputHandler implements KeyListener, MouseListener, MouseMotionListener {

    private final InputState input;

    public InputHandler(InputState input) {
        this.input = input;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> input.up = true;
            case KeyEvent.VK_A -> input.left = true;
            case KeyEvent.VK_S -> input.down = true;
            case KeyEvent.VK_D -> input.right = true;

            case KeyEvent.VK_1 -> input.key1 = true;
            case KeyEvent.VK_2 -> input.key2 = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> input.up = false;
            case KeyEvent.VK_A -> input.left = false;
            case KeyEvent.VK_S -> input.down = false;
            case KeyEvent.VK_D -> input.right = false;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            input.mouseClicked = true;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        input.mouseX = e.getX();
        input.mouseY = e.getY();
    }

    public void keyTyped(KeyEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
}