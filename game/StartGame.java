package game;

import game.resources.engine.Engine;
import game.swing.BackgroundPanel;
import java.awt.EventQueue;
import java.io.IOException;

public class StartGame {

   public static void main(String[] args) throws InterruptedException {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    BackgroundPanel.createDefaultFrame().setVisible(true);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

}

    /*public static void main(String[] args) throws InterruptedException {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {                   
                    Thread thread = Engine.createThread(null);
                    thread.start();
            }
        });
    }

}*/