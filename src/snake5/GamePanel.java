package snake5;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.*;
import java.util.random.*;


import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener{

		private final int width = 500;
		private final int height = 500;
		private final int unit = 25;
		private final int game_units= (width * height) / unit;
		private final int delay = 75;
		boolean running = false;
		final int[] x= new int[game_units];
		final int[] y = new int[game_units];
		private int bodyParts = 6;
		private int applesEaten = 0;
		int appleX = 0;
		int appleY = 0;
	
		
		
		Timer timer;
		Random random;
		char direction = 'R';
		
		GamePanel() {
			random = new Random();
			this.setPreferredSize(new Dimension(width,height));
			this.setBackground(Color.black);
			this.setFocusable(true);
			this.addKeyListener(new MyKeyAdapter());
			startGame();
		}
		
		
		public void startGame() {
			newApple();
			running = true;
			timer = new Timer(delay,this);
			timer.start();
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			draw(g);
		}
		
		
		private void newApple() {
			appleX = random.nextInt((int)(width/unit))*unit;
			appleY = random.nextInt((int)(height/unit))*unit;
			
		}


		public void draw(Graphics g) {
			
			if(running) {
				for(int i = 0; i < height/unit; i++) {
					g.drawLine(i*unit, 0, i*unit, height);
					g.drawLine(0, i*unit, width, i*unit);
				}
				g.setColor(Color.red);
				g.fillOval(appleX, appleY, unit, unit);
				
				for(int i = 0; i < bodyParts; i++) {
					if(i ==0) {
						g.setColor(Color.green);
						g.fillRect(x[i], y[i], unit, unit);
					}
					else {
						g.setColor(Color.LIGHT_GRAY);
						g.fillRect(x[i], y[i], unit, unit);
					}
				}
				g.setColor(Color.red);
				g.setFont(new Font("Ink Free", Font.BOLD, 40));
				FontMetrics metrics = getFontMetrics(g.getFont());
				g.drawString("Score: " + applesEaten , (width - metrics.stringWidth("Score: ")) / 2, g.getFont().getSize());
				
			} else {
				gameOver(g);
			}
			
			
		
		}
		
		public void move() {
			for(int i = bodyParts; i > 0; i --) {
				x[i] = x[i-1];
				y[i] = y[i-1];
			}
			
			switch(direction) {
			case 'U':
				y[0] = y[0] - unit;
				break;
			case 'D':
				y[0] = y[0] + unit;	
				break;
			case 'L':
				x[0] = x[0] - unit;
				break;
			case 'R':
				x[0] = x[0] + unit;	
				break;
			}
			
			if(!running) {
				timer.stop();
			}
			
					
		}
		
		public void checkApple() {
			if((x[0] == appleX) && (y[0] == appleY)) {
				bodyParts++;
				applesEaten++;
				newApple();
			}
		}
		
		public void checkCollisons() {
			for(int i = bodyParts; i > 0; i--) {
				if((x[0] == x[i]) && (y[0] == y[i])) {
					running = false;
				}
			}
		
			if(x[0] < 0) {
				running = false;
			}
			if(x[0] > width) {
				running = false;
			}
			if(y[0] > height) {
				running = false;
			}
			if(y[0] < 0) {
				running = false;
			}
		}
		
		public void gameOver(Graphics g) {
			
			g.setColor(Color.red);
			g.setFont(new Font("Ink Free", Font.BOLD, 40));
			FontMetrics metrics1 = getFontMetrics(g.getFont());
			g.drawString("Score: " + applesEaten , (width - metrics1.stringWidth("Score: ")) / 2, g.getFont().getSize());
			
			
			g.setColor(Color.red);
			g.setFont(new Font("Ink Free", Font.BOLD, 75));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Game Over", (width - metrics.stringWidth("Game Over")) / 2, height/2);
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(running) {
				move();
				checkApple();
				checkCollisons();
			}
			repaint();
			
		}
		
		public class MyKeyAdapter extends KeyAdapter {
			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if(direction != 'R') {
						direction = 'L';
					}
					break;
				case KeyEvent.VK_RIGHT:
					if(direction != 'L') {
						direction = 'R';
					}
					break;
				case KeyEvent.VK_UP:
					if(direction != 'D') {
						direction = 'U';
					}
					break;
				case KeyEvent.VK_DOWN:
					if(direction != 'U') {
						direction = 'D';
					}
					break;
				}
			}
		}
		
		
		
			
	
	
}
