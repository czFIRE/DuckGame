/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.resources.engine;

import game.swing.BackgroundPanel;
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
        this.bg = background;
    }
    
    

    //Generate ducks
    @Override
    public void run() {

        //draw duck
        
        
        
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        //delete duck
        
    }
    
    /*
    thread
    background panel argument
    bude se s tím tam hýbat
    */

}
