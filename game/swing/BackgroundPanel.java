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

    private static JLabel timeShower;

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

        /*timeShower = new JLabel("time");
        timeShower.setBounds(1090, 890, 100, 20);
        f.add(timeShower);*/
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
         * Thread to generate guys
         */

        /**
         * Thread to move with guys
         */
        thread1 = Engine.createThread(this);
        thread1.start();
        
        BlindGuy guy2 = new BlindGuy(200, 700, 0.0625f, 25);
        blindGuys.add(guy2);
        
        thread2 = GenerateGuys.createThread(this);
        thread2.start();

        /**
         * Shows time + stops app after given time
         */
        exitTimer();

        /* // doesn't belong here
        BlindGuy guy = new BlindGuy();
        guy.setX(80);
        guy.setY(100);
        guy.setRatio(0.25);
        blindGuys.add(guy);
        BlindGuy guy1 = new BlindGuy();
        guy1.setX(500);
        guy1.setY(500);
        guy1.setRatio(0.125);
        blindGuys.add(guy1);
        // to here */

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
        /*blindGuys.forEach((current) -> {
            current.paintBlindGuy(g);
        });*/
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
        /*if (blindGuys.isEmpty()) {
            System.exit(0);
        }*/
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent me) {

    }

    @Override
    public void mousePressed(MouseEvent me) {
        int mouseX = me.getX();
        int mouseY = me.getY();
        /*System.out.println("***");
        System.out.println(me.getX() + " " + me.getY());*/

        //System.out.println("Click registered!                        2");
        int dims[];

        for (BlindGuy guy : blindGuys) {
            dims = guy.getDims();
            if ((dims[0] + dims[2]) > 1200) {
                dims[2] = 1200 - dims[0];
            }
            /*System.out.println("***");
            for (int dim : dims) {
                System.out.println(dim);
            }
            System.out.println("***");*/
            if (((mouseX > dims[0]) && (mouseX < (dims[0] + dims[2]))) && ((mouseY > dims[1]) && (mouseY < (dims[1] + dims[3])))) {
                blindGuys.remove(guy);
                score += 1;
                System.out.println("Hit");
                System.out.println(blindGuys.size());

                /*if (blindGuys.isEmpty()) {
                    System.exit(0);
                }*/

                //add rerender
                repaint();

                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
