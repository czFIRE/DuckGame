package game;

import game.swing.BackgroundPanel;
import java.awt.EventQueue;
import java.io.IOException;

import java.io.*;
import javax.swing.JApplet;
import java.awt.image.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.imageio.*;
import javax.swing.*;
import javax.imageio.stream.*;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.Point;

public class StartGame {

    public static void main(String[] args)throws InterruptedException {
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
