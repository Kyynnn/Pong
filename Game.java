/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kyn Mark N. Trongcao
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {
    Ball ball = new Ball(this);
    Racquet racquet1 = new Racquet(this, 50, KeyEvent.VK_A, KeyEvent.VK_D);
    Racquet racquet2 = new Racquet(this, 300, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        
    int speed = 1;
    int playerOneScore = 0;
    int playerTwoScore = 0;
    boolean isGameOver;

    public Game() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                racquet1.keyReleased(e);
                racquet2.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                racquet1.keyPressed(e);
                racquet2.keyPressed(e);
            }
        });
        setFocusable(true);
        Sound.BACK.loop();
    }

    private void move() {
        ball.move();
        racquet1.move();
        racquet2.move();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        this.setBackground(Color.BLACK);
        g2d.setColor(Color.WHITE);
        ball.paint(g2d);
        g2d.setColor(Color.BLUE);
        racquet1.paint(g2d);
        g2d.setFont(new Font("Verdana", Font.BOLD, 12));
        g2d.drawString("Player 1: " + String.valueOf(playerOneScore), 10, 30);
        g2d.setColor(Color.RED);
        racquet2.paint(g2d);
        g2d.drawString("Player 2: " + String.valueOf(playerTwoScore), 10, 330);
    }
    
    public void gameResetTypeOne() {
        ball.resetTypeOne();
        racquet1.reset();
        racquet2.reset();
        speed = 1;
    }
    
    public void gameResetTypeTwo() {
        ball.resetTypeTwo();
        racquet1.reset();
        racquet2.reset();
        speed = 1;
    }
    
    public void gameOverReset() {
        if (playerOneScore == 3) {    
            Sound.BACK.stop();
            Sound.GAMEOVER.play();
            int choice = JOptionPane.showConfirmDialog(null, "Player One Wins! Try Again?");
            if (choice == 0) {
                Sound.BACK.loop();
                gameResetTypeTwo();
                playerOneScore = 0;
                playerTwoScore = 0;
                speed = 1;
            } else {
                System.exit(ABORT);
            } 
        } else if (playerTwoScore == 3) {
            Sound.BACK.stop();
            Sound.GAMEOVER.play();            
            int choice = JOptionPane.showConfirmDialog(null, "Player Two Wins! Try Again?");
            if (choice == 0) {
                gameResetTypeTwo();
                Sound.BACK.loop();
                playerOneScore = 0;
                playerTwoScore = 0;
                speed = 1;
            } else {
                System.exit(ABORT);
            } 
        }
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Pong");
        Game game = new Game();
        frame.add(game);
        frame.setSize(300, 400);
        frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while(true) {             
            game.move();
            game.repaint();
            Thread.sleep(10);
            game.gameOverReset();
        }
    }
}