package dip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * start = Ez az indító gomb
 * exit = Ez a kilépő gomb
 */
public class MainMenu extends Canvas {
    static JFrame thisFrame;

    JButton start;
    JButton exit;

    public MainMenu() {
        setSize(900, 900);
    }


    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics ig) {
        BufferedImage image = new BufferedImage(900, 900, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, 900, 900);

        ig.drawImage(image, 0, 0, this);

    }

    /**
     * void ui = ez helyezi fel a gombokat a menübe
     */
    public void ui(JFrame frame) {
        thisFrame = frame;


        start = new JButton("Play");
        start.setBounds(250, 450, 400, 150);
        start.setBackground(Color.WHITE);
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });


        exit = new JButton("Exit");
        exit.setBounds(250, 600, 400, 150);
        exit.setBackground(Color.WHITE);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exitButton();
            }
        });

        frame.add(start);
        frame.add(exit);


    }

    /**
     * startGame = elindítja a játékot, készít egy új mapet, ezt az ablakot bezárja
     */
    private void startGame() {


        Map map = new Map();
        map.start();


        thisFrame.dispose();
    }

    private void exitButton() {
        thisFrame.dispose();

    }

    /**
     *
     * @param menu
     */
    public void open(MainMenu menu) {
        final JFrame frame = new JFrame();
        menu.ui(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(menu);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        menu.requestFocus();
    }

}
