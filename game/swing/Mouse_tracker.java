package game.swing;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Mouse_tracker extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent me) {
        int mouseX = me.getX();
        int mouseY = me.getY();
        System.out.println("screen(X,Y) = " + mouseX + "," + mouseY);
    }
}
