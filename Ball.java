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

public class Ball {
    private static final int DIAMETER = 30;
	
    int x = 115;
    int y = 170;
    int xa = 1;
    int ya = 1;
    private Game game;

    public Ball(Game game) {
        this.game = game;
    }
	
    void move() {
        boolean changeDirection = true;
        if (x + xa <= 0) {
            xa = game.speed;	
        } else if (x + xa >= game.getWidth() - DIAMETER)	{
            xa = -game.speed;
        } else if (y + ya > game.getHeight() - DIAMETER) {
            game.playerOneScore++;
            game.gameResetTypeTwo();            
        } else if (y + ya <= 0) {     
            game.playerTwoScore++;
            game.gameResetTypeOne();         
        } else if (topCollision()){
            Sound.BALL.play();
            game.speed++;
            ya = game.speed;
            y = game.racquet1.getBottomY();
        } else if (bottomCollision()) {
            Sound.BALL.play();
            game.speed++;
            ya = -game.speed;
            y = game.racquet2.getTopY() - DIAMETER;           
        } else {
            changeDirection = false;
        } 
	x = x + xa;
	y = y + ya;
    }

    private boolean topCollision() {
        return game.racquet1.getBounds().intersects(getBounds());
    }
        
    private boolean bottomCollision() {
        return game.racquet2.getBounds().intersects(getBounds());
    }

    public void paint(Graphics2D g) {
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }
        
    public void resetTypeOne() {
	x = 115;
	y = 170;
	xa = -1;
	ya = 1;
    }
    
    public void resetTypeTwo() {
        x = 115;
	y = 170;
	xa = 1;
	ya = -1;
    }
}