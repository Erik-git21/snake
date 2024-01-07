package snake5;

import javax.swing.JFrame;

public class GameFrame extends JFrame{

	GameFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(new GamePanel());
		this.setTitle("Snake");
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		
	}
	
}
