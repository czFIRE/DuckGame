/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.resources.engine;

import game.StartGame;
import game.swing.BackgroundPanel;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author czFIRE
 */
public class FinalScreen extends JFrame{
    
    private JButton retry, exit;
      
    public FinalScreen (int score, BackgroundPanel bg){
        super("Game ended!");
        this.setLayout(new GridLayout(2, 2, 5, 5));
        this.add(new JLabel("Your score:"));
        this.add(new JLabel("" + score));
        retry = new JButton("Retry");
        this.add(retry);
        exit = new JButton("Exit");
        this.add(exit);
        
        exit.addActionListener((e) -> {
            System.exit(0);
        });
        
        retry.addActionListener((e) -> {
            bg.f.dispose();
            
             // Call new game
             
            try {
                BackgroundPanel.createDefaultFrame().setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(FinalScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            this.dispose();
        });
        
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}
