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
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author czFIRE
 */
public class BackgroundPanel extends JPanel implements MouseListener {

    private final BufferedImage img;

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

    private static BackgroundPanel createDefaultPane() throws IOException {
        return new BackgroundPanel(ImageIO.read(BackgroundPanel.class.getResourceAsStream("/game/resources/backgroundimage.jpg"))); ////////

    }

    BufferedImage getImg() {
        return img;
    }
    
    public final ArrayList<BlindGuy> blindGuys;

    private BackgroundPanel(BufferedImage img) throws IOException {
        this.img = img;
        this.setLayout(new BorderLayout());
        this.addMouseListener(this);
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
        blindGuys.add(guy1);
        // to here

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

    @Override
    public void mouseClicked(MouseEvent me) {
        int mouseX = me.getX();
        int mouseY = me.getY();
        int dims[] = new int[4];
        for (BlindGuy guy : blindGuys) {
            dims = guy.getDims();
            if (((mouseX > dims[0]) && (mouseX < (dims[0] + dims[2]))) && ((mouseY > dims[1]) && (mouseX < (dims[1] + dims[3])))) {
                blindGuys.remove(guy);
                System.out.println("Hit");
                System.out.println(blindGuys.size());
                
                //add rerender
                repaint();
                
                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}
