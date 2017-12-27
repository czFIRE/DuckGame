/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.swing;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author jvanek
 */
public class BackgroundPanel extends JPanel {

    private final BufferedImage img;

    public static JFrame createDefaultFrame() throws IOException {
        JFrame f = new JFrame();
        f.setLayout(new BorderLayout());

        BackgroundPanel p = createDefaultPane();
        f.add(p);
        // f.setSize(p.getImg().getWidth(), p.getImg().getHeight());
        f.setSize(1400, 1050);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image aim = toolkit.getImage(BackgroundPanel.class.getResource("/game/resources/mercedes_logo_adjusted.png"));
        Cursor cursor = toolkit.createCustomCursor(aim, new Point(0, 0), "mercedesCrosshair");
        f.setCursor(cursor);
        
        return f;

    }

    private static BackgroundPanel createDefaultPane() throws IOException {
        return new BackgroundPanel(ImageIO.read(BackgroundPanel.class.getResourceAsStream("/game/resources/backgroundimage.jpg"))); ////////

    }
    //private final BlindGuy blindGuy; first variant
    public final ArrayList<BlindGuy> blindGuys;
    
    BufferedImage getImg() {
        return img;
    }

    private BackgroundPanel(BufferedImage img) throws IOException {
        this.img = img;
        this.setLayout(new BorderLayout());
        this.addMouseListener(new Mouse_tracker());
        //blindGuy = new BlindGuy(); first variant
        blindGuys = new ArrayList<>();
        
        // doesn't belong here
        BlindGuy guy = new BlindGuy();
        guy.setX(80);
        guy.setY(100);
        guy.setRatio(0.25);
        blindGuys.add(guy);
        BlindGuy guy1 = new BlindGuy();
        guy1.setX(500);
        guy1.setY(500);
        guy1.setRatio(0.125);
        guy1.Die();
        blindGuys.add(guy1);
        // to here
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
        /*for (int i=0;i<=blindGuy.size();i++) {
        }*/
        blindGuys.forEach((current) -> {
            if(current.isAlive()) current.paintBlindGuy(g);
        });
        //blindGuy.paintBlindGuy(g,0.25); first variant
        
    }

}
