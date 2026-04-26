package org.example.input;

/**
 * Uchováva stav vstupov od používateľa.
 * Obsahuje informácie o stlačených klávesoch a pohybe myši.
 */
public class InputState {
    public boolean up, down, left, right;
    public boolean mouseClicked;
    public int mouseX, mouseY;
    public boolean key1, key2;
}
