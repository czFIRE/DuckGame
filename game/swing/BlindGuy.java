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
    private boolean alive = true;
    private int x, y;
    private double ratio = 0.125;

    public BlindGuy() throws IOException {
        img = ImageIO.read(BackgroundPanel.class.getResourceAsStream("/game/resources/human.png"));
    }

    public void setRatio (double a){
        ratio = a;
    }
    
    public void setX (int a) {
        x = a;
    }

    public void setY (int a) {
        y = a;
    }
    
    public void Die (){
        alive=false;
    }
    
    public boolean isAlive () {
        return alive;
    }
    
    /*void paintBlindGuy(Graphics g){
        g.drawImage(img, x, y, img.getWidth(), img.getHeight(), null);
    }*/
    
    void paintBlindGuy(Graphics g/*, double ratio*/){
        g.drawImage(img, x, y, (int) ((img.getWidth())*ratio), (int) (img.getHeight()*ratio), null);
    }
}
