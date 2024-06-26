import java.awt.Color;
import java.net.URL;

import javax.swing.*;

public class GameCharacter extends JPanel {
	GameItem[] items = {
		null, null, null
	};
	
	int itemCount = 0;
	int maxItems = 3;
	
	int attack = 0;
	int speed = 0;
	int defense = 0;
	
	int counter = 1;
	
	String direction = "right";
	
	JPanel[] pnlItems = {null ,null, null};
	
	JLabel lblAttk;
	
	public GameCharacter(String type) {
		
//		this.setBackground(Color.BLACK);
		this.setOpaque(false);
		this.setLayout(null);
		
		JLabel lblSprite = new JLabel();
		lblSprite.setBounds(0,0,50,50);
		
		URL urlUp1 = Home.class.getResource("/r_up_1.png");
		ImageIcon icnUp1 = new ImageIcon(urlUp1);
		URL urlUp2 = Home.class.getResource("/r_up_2.png");
		ImageIcon icnUp2 = new ImageIcon(urlUp2);
		URL urlUp3 = Home.class.getResource("/r_up_3.png");
		ImageIcon icnUp3 = new ImageIcon(urlUp3);
		
		URL urlDown1 = Home.class.getResource("/r_down_1.png");
		ImageIcon icnDown1 = new ImageIcon(urlDown1);
		URL urlDown2 = Home.class.getResource("/r_down_2.png");
		ImageIcon icnDown2 = new ImageIcon(urlDown2);
		URL urlDown3 = Home.class.getResource("/r_down_3.png");
		ImageIcon icnDown3 = new ImageIcon(urlDown3);
		
		URL urlLeft1 = Home.class.getResource("/r_left_1.png");
		ImageIcon icnLeft1 = new ImageIcon(urlLeft1);
		URL urlLeft2 = Home.class.getResource("/r_left_2.png");
		ImageIcon icnLeft2 = new ImageIcon(urlLeft2);
		URL urlLeft3 = Home.class.getResource("/r_left_3.png");
		ImageIcon icnLeft3 = new ImageIcon(urlLeft3);
		
		URL urlRight1 = Home.class.getResource("/r_right_1.png");
		ImageIcon icnRight1 = new ImageIcon(urlRight1);
		URL urlRight2 = Home.class.getResource("/r_right_2.png");
		ImageIcon icnRight2 = new ImageIcon(urlRight2);
		URL urlRight3 = Home.class.getResource("/r_right_3.png");
		ImageIcon icnRight3 = new ImageIcon(urlRight3);
		
		pnlItems[0] = new JPanel();
		pnlItems[0].setVisible(false);
		pnlItems[0].setBounds(0,0,10,10);
		
		this.add(pnlItems[0]);
		
		pnlItems[1] = new JPanel();
		pnlItems[1].setVisible(false);
		pnlItems[1].setBounds(10,0,10,10);
		
		this.add(pnlItems[1]);
		
		pnlItems[2] = new JPanel();
		pnlItems[2].setVisible(false);
		pnlItems[2].setBounds(20,0,10,10);
		
		this.add(pnlItems[2]);
		
		add(lblSprite);
		
		switch(type) {
			case "fighter":
				this.attack = 40;
				this.speed = 5;
				this.defense = 20;
				
				break;

			case "tank":
				this.attack = 20;
				this.speed = 3;
				this.defense = 40;
				
				break;
		}
		
		lblAttk = new JLabel("A: " + this.attack);
		lblAttk.setBounds(0,10,50,20);
		this.add(lblAttk);
		
		Timer t = new Timer(1000/4, e -> {
			switch(direction) {
				case "right":
					if(counter == 1)
						lblSprite.setIcon(icnRight1);
					if(counter == 2)
						lblSprite.setIcon(icnRight2);
					if(counter == 3)
						lblSprite.setIcon(icnRight1);
					if(counter == 4)
						lblSprite.setIcon(icnRight3);
					counter += 1;
					if(counter == 5)
						counter = 1;
					break;
				case "left":
					if(counter == 1)
						lblSprite.setIcon(icnLeft1);
					if(counter == 2)
						lblSprite.setIcon(icnLeft2);
					if(counter == 3)
						lblSprite.setIcon(icnLeft1);
					if(counter == 4)
						lblSprite.setIcon(icnLeft3);
					counter += 1;
					if(counter == 5)
						counter = 1;
					break;
				case "up":
					if(counter == 1)
						lblSprite.setIcon(icnUp1);
					if(counter == 2)
						lblSprite.setIcon(icnUp2);
					if(counter == 3)
						lblSprite.setIcon(icnUp1);
					if(counter == 4)
						lblSprite.setIcon(icnUp3);
					counter += 1;
					if(counter == 5)
						counter = 1;
					break;
				case "down":
					if(counter == 1)
						lblSprite.setIcon(icnDown1);
					if(counter == 2)
						lblSprite.setIcon(icnDown2);
					if(counter == 3)
						lblSprite.setIcon(icnDown1);
					if(counter == 4)
						lblSprite.setIcon(icnDown3);
					counter += 1;
					if(counter == 5)
						counter = 1;
					break;
			}
			
			repaint();
		});
		
		t.start();
	}
	
	void applyEffects() {
		this.attack += this.items[this.itemCount].attack;
		this.speed += this.items[this.itemCount].speed;
		this.defense += this.items[this.itemCount].defense;
		
		this.pnlItems[this.itemCount].setVisible(true);
		
		JLabel lblItemSprite = new JLabel();
		lblItemSprite.setBounds(0,0,10,10);
		lblItemSprite.setIcon(items[itemCount].icnSmall);
		this.pnlItems[this.itemCount].add(lblItemSprite);
		
		lblAttk.setText("A: " + this.attack);
	}
	
	boolean hasCollidedWith(GameItem gameItem) {
		int gIX = gameItem.getBounds().x;
		int gIY = gameItem.getBounds().y;
		int gIH = gameItem.getBounds().height;
		int gIW = gameItem.getBounds().width;
		
		int gCX = this.getBounds().x;
		int gCY = this.getBounds().y;
		int gCH = this.getBounds().height;
		int gCW = this.getBounds().width;
		
		return (gCX > (gIX - gCW) && gCX < (gIX + gIW)) && (gCY > (gIY - gCH) && gCY < (gIY + gIH));
	}
	
	void add(GameItem gameItem) {
		items[this.itemCount] = gameItem;
		applyEffects();
		itemCount += 1;
	}
	
	boolean canHaveItems() {
		return itemCount < maxItems;
	}
}
