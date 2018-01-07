/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.swing;

import game.resources.engine.Engine;
import game.resources.engine.GenerateGuys;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author czFIRE
 */
public class BackgroundPanel extends JPanel implements MouseListener {

    private final BufferedImage img;

    private int score = 0;

    public static JFrame createDefaultFrame() throws IOException {

        JFrame f = new JFrame();
        f.setLayout(new BorderLayout());

        BackgroundPanel p = createDefaultPane();
        f.add(p);
        // f.setSize(p.getImg().getWidth(), p.getImg().getHeight());
        f.setSize(1200, 900);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image aim = toolkit.getImage(BackgroundPanel.class.getResource("/game/resources/mercedes_logo_adjusted.png"));
        Cursor cursor = toolkit.createCustomCursor(aim, new Point(0, 0), "mercedesCrosshair");
        f.setCursor(cursor);

        return f;
    }

    public static BackgroundPanel createDefaultPane() throws IOException {
        return new BackgroundPanel(ImageIO.read(BackgroundPanel.class.getResourceAsStream("/game/resources/backgroundimage.jpg"))); ////////
    }

    BufferedImage getImg() {
        return img;
    }

    private ArrayList<BlindGuy> blindGuys;

    private Thread thread1, thread2;

    public BackgroundPanel(BufferedImage img) throws IOException {
        this.img = img;
        this.setLayout(new BorderLayout());
        this.addMouseListener(this);
        blindGuys = new ArrayList<>();


        /**
         * Thread to move with guys
         */
        
        thread1 = Engine.createThread(this);
        thread1.start();

        /**
         * Thread to generate guys
         */
        
        thread2 = GenerateGuys.createThread(this);
        thread2.start();

        /**
         * Shows time + stops app after given time
         */
        
        exitTimer();
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
        for (BlindGuy blindGuy : blindGuys) {
            blindGuy.paintBlindGuy(g);
        }
    }
    
    private ArrayList<BlindGuy> guysToRemove;

    public void triggerRepaint() {
        guysToRemove = new ArrayList<>();
        for (BlindGuy guy : blindGuys) {
            guy.update();
            if (guy.getX() > 1200) {
                guysToRemove.add(guy);
            }
        }
        blindGuys.removeAll(guysToRemove);

        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent me) {

    }

    @Override
    public void mousePressed(MouseEvent me) {
        int mouseX = me.getX();
        int mouseY = me.getY();

        int dims[];

        for (BlindGuy guy : blindGuys) {
            dims = guy.getDims();
            if ((dims[0] + dims[2]) > 1200) {
                dims[2] = 1200 - dims[0];
            }

            if (((mouseX > dims[0]) && (mouseX < (dims[0] + dims[2]))) && ((mouseY > dims[1]) && (mouseY < (dims[1] + dims[3])))) {
                blindGuys.remove(guy);
                score += 1;
                
                /*System.out.println("Hit");
                System.out.println(blindGuys.size());*/

                repaint();

                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }

    private void exitTimer() {
        Timer timer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Score: " + score);
                thread1.stop();
                thread2.stop();

                System.exit(0);
            }
        });
        timer.start();
    }
    
    public void addNewGuy(int x, int y, float ratio, int speed) throws IOException{
        BlindGuy guy = new BlindGuy(x, y, ratio, speed);
        blindGuys.add(guy);
        repaint();
    }

}
