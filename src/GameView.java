import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class GameView extends JPanel {
	
	// SET STARTING LOCATIONS
	
	// Game Characters
	int gc1Y = 540, gc1X = 10;
	String gc1Direction = "still";
	
	int gc2Y = 10, gc2X = 540;
	String gc2Direction = "still";
	
	// Items
	int gi1X = 337, gi1Y = 337;
	
	int gi2X = 20, gi2Y = 20;
	
	public GameView() { // Constructor
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		// Create characters
		GameCharacter gc1 = new GameCharacter("tank");
		GameCharacter gc2 = new GameCharacter("fighter");
		
		this.add(gc1);
		this.add(gc2);
		
		// Create items
		GameItem giBoots = new GameItem("boots");
		GameItem giSword = new GameItem("sword");
		
		giBoots.setBounds(gi1X, gi1Y, 25, 25);
		giSword.setBounds(gi2X, gi2Y, 25, 25);
		
		this.add(giBoots);
		this.add(giSword);
		
		// Start the movements per frame
		Timer t = new Timer(1000/60, e -> {
			switch(gc1Direction) {
				case "right":
					gc1.direction = "right";
					gc1X += gc1X<650 ? gc1.speed : 0;
					break;
				case "left":
					gc1.direction = "left";
					gc1X -= gc1X>0 ? gc1.speed : 0;
					break;
				case "up":
					gc1.direction = "up";
					gc1Y -= gc1Y>0 ? gc1.speed : 0;
					break;
				case "down":
					gc1.direction = "down";
					gc1Y += gc1Y<650 ? gc1.speed : 0;
					break;
			}
			
			switch(gc2Direction) {
				case "right":
					gc2.direction = "right";
					gc2X += gc2X<650 ? gc2.speed : 0;
					break;
				case "left":
					gc2.direction = "left";
					gc2X -= gc2X>0 ? gc2.speed : 0;
					break;
				case "up":
					gc2.direction = "up";
					gc2Y -= gc2Y>0 ? gc2.speed : 0;
					break;
				case "down":
					gc2.direction = "down";
					gc2Y += gc2Y<650 ? gc2.speed : 0;
					break;
			}
			
			gc1.setBounds(gc1X, gc1Y, 50, 50);
			gc2.setBounds(gc2X, gc2Y, 50, 50);
			
			// CHECK COLLISIONS
			if(gc1.hasCollidedWith(giBoots) && gc1.canHaveItems()) {
				gc1.add(giBoots);
				giBoots.hideItem();
			}
			
			if(gc2.hasCollidedWith(giBoots) && gc2.canHaveItems()) {
				gc2.add(giBoots);
				giBoots.hideItem();
			}
			
			if(gc1.hasCollidedWith(giSword) && gc1.canHaveItems()) {
				gc1.add(giSword);
				giSword.hideItem();
			}
			
			if(gc2.hasCollidedWith(giSword) && gc2.canHaveItems()) {
				gc2.add(giSword);
				giSword.hideItem();
			}
			
			this.setFocusable(true);
			this.requestFocusInWindow();
			this.repaint();
		});
		
		// ASSIGN KEYBOARD KEYS
		this.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					gc1Direction = "right";
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					gc1Direction = "left";
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					gc1Direction = "down";
				}
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					gc1Direction = "up";
				}
				
				if(e.getKeyCode() == KeyEvent.VK_W) {
					gc2Direction = "up";
				}
				if(e.getKeyCode() == KeyEvent.VK_D) {
					gc2Direction = "right";
				}
				if(e.getKeyCode() == KeyEvent.VK_A) {
					gc2Direction = "left";
				}
				if(e.getKeyCode() == KeyEvent.VK_S) {
					gc2Direction = "down";
				}
			}

			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					gc1Direction = "still";
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					gc1Direction = "still";
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					gc1Direction = "still";
				}
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					gc1Direction = "still";
				}
				
				if(e.getKeyCode() == KeyEvent.VK_W) {
					gc2Direction = "still";
				}
				if(e.getKeyCode() == KeyEvent.VK_D) {
					gc2Direction = "still";
				}
				if(e.getKeyCode() == KeyEvent.VK_A) {
					gc2Direction = "still";
				}
				if(e.getKeyCode() == KeyEvent.VK_S) {
					gc2Direction = "still";
				}
			}
		});
		
		// START THE MOVEMENTS
		t.start();
	}
}
