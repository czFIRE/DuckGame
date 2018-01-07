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
public class Engine implements Runnable {

    public static Thread createThread(BackgroundPanel background) {
        return new Thread(new Engine(background));
    }
    private final BackgroundPanel bg;

    private Engine(BackgroundPanel background) {
        bg = background;
        //System.out.println("Ahoj");
    }

    int x, y, speed, random;
    float ratio = 0.0625f;

    //Generate ducks
    @Override
    public void run() {
        //draw duck
        while (true) {
            try {
                Thread.sleep(100);
                //System.out.println("NEco");
                bg.triggerRepaint();
                /*random = 100 + (int) (Math.random() * (401));
                //ratio = random / 1000;
                speed = 10 + (int) (Math.random() * (71));
                x = (int) (Math.random() * (201));
                y = 435 + (int) (Math.random() * (466));

                try {
                    bg.addNewGuy(x, y, ratio, speed);
                } catch (IOException ex) {
                    Logger.getLogger(GenerateGuys.class.getName()).log(Level.SEVERE, null, ex);
                }*/
            } catch (InterruptedException ex) {
                Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        //delete duck

        /*Timer timer1; //little car panel for reference
    timer1 = new Timer();
    timer1.start();*/
    }

    /*
    thread
    background panel argument
    bude se s tím tam hýbat
     */
}
