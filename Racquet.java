/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kyn Mark N. Trongcao
 */

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racquet {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 10;
    int x = 100;
    int xa = 0;
    int y;
    int left;
    int right;
    private Game game;

    public Racquet(Game game, int y, int left, int right) {
        this.game = game;
        this.y = y;
        this.left = left;
        this.right = right;
    }

    public void move() {
        if (x + xa > 0 && x + xa < game.getWidth() - WIDTH) {
            x = x + xa;
	}
    }

    public void paint(Graphics2D g) {
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    public void keyReleased(KeyEvent e) {
        xa = 0;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (e.getKeyCode() == left) {
                xa = -game.speed;
            }
            if (e.getKeyCode() == right) {
                xa = game.speed;
            }
        } else {
            if (e.getKeyCode() == left) {
                xa = -game.speed;
            }
            if (e.getKeyCode() == right) {
                xa = game.speed;
            }
        }
    }

    public Rectangle getBounds() {
	return new Rectangle(x, y, WIDTH, HEIGHT);
    }
        
    public int getTopY() {
        return y;
    }
        
    public int getBottomY() {
        return y + 10;
    }

    public void reset() {
        x = 100;
        xa = 0;
    }
}