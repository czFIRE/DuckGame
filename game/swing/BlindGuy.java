/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.swing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author czFIRE
 */
public class BlindGuy {

    private final BufferedImage img;
    private int x, y;
    private double ratio = 0.125;
    private int speed = 20;

    public BlindGuy() throws IOException {
        img = ImageIO.read(BackgroundPanel.class.getResourceAsStream("/game/resources/human.png"));
    }

    public void setSpeed(int x) {
        speed = x;
    }

    public void setRatio(double a) {
        ratio = a;
    }

    public double getRatio() {
        return ratio;
    }

    public void setX(int a) {
        x = a;
    }

    public int getX() {
        return x;
    }

    public void setY(int a) {
        y = a;
    }

    public int[] getDims() {
        int dims[] = new int[4];
        dims[0] = x;
        dims[1] = y;
        dims[2] = (int) (img.getWidth() * ratio);
        dims[3] = (int) (img.getHeight() * ratio);
        return dims;
    }

    void paintBlindGuy(Graphics g) {
        g.drawImage(img, x, y, (int) (img.getWidth() * ratio), (int) (img.getHeight() * ratio), null);
    }

    public void update() {
        x = x + speed;
    }
}
