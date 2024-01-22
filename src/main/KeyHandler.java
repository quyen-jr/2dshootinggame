package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import javax.xml.stream.XMLInputFactory;

public class KeyHandler implements KeyListener{
	public boolean upPressed, downPressed, rightPressed, leftPressed, spacePressed,enterPressed;
	public int xInput, yInput;
	private Set<Character> pressedKeySet=  new HashSet<Character>();
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		handleIfPressMoveKey(e);
		int code= e.getKeyCode();
			switch (code) {
			case KeyEvent.VK_SPACE:
				spacePressed=true;
				break;
			case KeyEvent.VK_ENTER:
				enterPressed=true;
				break;
			default:
				break;
			}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		handleIfReleaseMoveKey(e);
		int code= e.getKeyCode();
		switch (code) {
		case KeyEvent.VK_SPACE:
			spacePressed=false;
			break;
		case KeyEvent.VK_ENTER:
			enterPressed=false;
			break;
		default:
			break;
		}
	}
	private void handleIfPressMoveKey(KeyEvent e) {
		pressedKeySet.add(e.getKeyChar());
		String ch= String.valueOf(e.getKeyChar()).toLowerCase();
		if(ch.equals("d")) {
			xInput=1;
		}else if(ch.equals("a")) {
			xInput=-1;
		}
	}

	private void handleIfReleaseMoveKey(KeyEvent e) {
		pressedKeySet.remove(e.getKeyChar());
		if(!pressedKeySet.isEmpty()) {
			String ch= String.valueOf(pressedKeySet.iterator().next());
			if(ch.equals("d")) {
				xInput=1;
			}else if(ch.equals("a")) {
				xInput=-1;
			}
		}
	}
	public void update() {
		updateXInput();
	}
	private void updateXInput() {
		if(pressedKeySet.isEmpty()) {
			xInput=0;
			return;
		}

	}

}
