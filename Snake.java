package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Snake implements ActionListener, KeyListener{

	public static Snake snake;
	public static final int WID = 395, HIGH = 400, UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;
	public JFrame jframe;
	public int tailLength;
	public ArrayList<Point> snakeParts = new ArrayList<Point>();
	public int direction = DOWN;
	public Point head;
	public Timer timer = new Timer(10, this);
	public RenderPanel renderPanel;
	public Point cherry;
	public Random random = new Random();
	public int ticks, score;
	public boolean over, paused=true;
	
	
	public Snake(){
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		jframe = new JFrame("Snake");
		jframe.setSize(WID, HIGH);
		jframe.setVisible(true);
		jframe.setLocation(dim.width/2-jframe.getWidth()/2, dim.height/2-jframe.getHeight()/2);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(renderPanel = new RenderPanel());
		renderPanel.repaint();
		jframe.addKeyListener(this);
		startGame();
		timer.start();
		
	}
		
	private void startGame() {
		over = false;
		ticks = 0;
		score = 0;
		snakeParts.clear();
		head = new Point(0, -1);
		direction = DOWN;
		cherry = new Point(WID/SCALE/2, HIGH/SCALE/2);
		tailLength = 5;
	}


	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && direction != DOWN)
			direction = UP;
		if ((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && direction != UP)
			direction = DOWN;
		if ((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && direction != RIGHT)
			direction = LEFT;
		if ((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && direction != LEFT)
			direction = RIGHT;
		if (key == KeyEvent.VK_SPACE){
			if (over)
				startGame();
			else paused = !paused;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		renderPanel.repaint();
		if (!paused && !over){
			ticks++;
			if (ticks % 10 == 0){
				score++;												
				if (direction == DOWN)
					if (head.y+1<36 && !snakeParts.contains(new Point(head.x,head.y+1)))
						head = new Point(head.x, head.y +1);
					else 
						over = true;
				if (direction == UP)
					if (head.y-1 > -1 && !snakeParts.contains(new Point(head.x,head.y-1)))
						head = new Point(head.x, head.y - 1);
					else over = true;
				if (direction == RIGHT)
					if (head.x+1<38 && !snakeParts.contains(new Point(head.x+1,head.y)))
						head = new Point(head.x + 1, head.y);
					else over = true;
				if (direction == LEFT)
					if (head.x-1>-1 && !snakeParts.contains(new Point(head.x-1,head.y)))
						head = new Point(head.x - 1, head.y);
					else over=true;
				if (!over)
					snakeParts.add(head);	
				if (snakeParts.size() > tailLength)
					snakeParts.remove(0);
				if (head.equals(cherry)){
					score += 100;
					tailLength++;
					cherry = new Point(random.nextInt(38), random.nextInt(36));
				}
			}
		}
			
		
	}
	
	public static void main(String[] args) {
		snake = new Snake();
		}
}
