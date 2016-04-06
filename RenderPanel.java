package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RenderPanel extends JPanel{
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(8804653));
		g.fillRect(0, 0, Snake.WID, Snake.HIGH);
		Snake snake = Snake.snake;

		g.setColor(new Color(16724736));
		g.fillRect(snake.cherry.x*Snake.SCALE, snake.cherry.y*Snake.SCALE, Snake.SCALE, Snake.SCALE);
		
		g.setColor(new Color(39168));
		for (int i=0 ; i<snake.snakeParts.size() ; i++){
			g.fillRect(snake.snakeParts.get(i).x*Snake.SCALE, snake.snakeParts.get(i).y*Snake.SCALE, Snake.SCALE, Snake.SCALE);
		}
		g.setFont(new Font("Sanserif", Font.BOLD, 20));
		g.setColor(new Color(3348992));
		if (snake.paused || snake.over)
			g.drawString("Press Space to start", Snake.WID/2-100, Snake.HIGH/2);
		g.setFont(new Font("Sanserif", Font.BOLD, 14));
		g.drawString("SCORE: "+snake.score, 5, 20);
		g.drawString("Time: "+snake.ticks/100, Snake.WID-100, 20);
	}
		
}	
