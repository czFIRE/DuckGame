/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.resources.engine;

import game.swing.BackgroundPanel;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author czFIRE
 */

public class GenerateGuys implements Runnable {

    public static Thread createThread(BackgroundPanel background) {
        return new Thread(new GenerateGuys(background));
    }
    private final BackgroundPanel bg;

    private GenerateGuys(BackgroundPanel background) {
        bg = background;
    }

    int x, y, speed, random;
    float ratio = 0.0625f;

    @Override
    public void run() {
        while (true) {
            try {
            random = 80 + (int) (Math.random() * (201));
            ratio = (float) (random) / 1000f;
            speed = 10 + (int) (Math.random() * (71));
            x = (int) (Math.random() * (201));
            y = 1 + (int) (Math.random() * (640));

            try {
                bg.addNewGuy(x, y, ratio, speed);
            } catch (IOException ex) {
                Logger.getLogger(GenerateGuys.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(GenerateGuys.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
