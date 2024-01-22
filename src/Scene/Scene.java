package Scene;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.GamePanel;

public class Scene implements MouseListener,MouseMotionListener  {
	protected GamePanel gp;
	protected  float mouseX;
	protected  float mouseY;
	public Scene(GamePanel _gp) {
		this.gp=_gp;
		
	}
	public void enter() {
		
	}
	public void exit() {
	}
	public void update() {
		
	}
	public void draw(Graphics2D g2) {
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX=e.getX();
		mouseY=e.getY();
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		mouseX=e.getX();
		mouseY=e.getY();
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
