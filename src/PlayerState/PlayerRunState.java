package PlayerState;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import main.GamePanel;

public class PlayerRunState extends PlayerGroundedState {
	private Clip moveClip;
	public PlayerRunState(Player _player, PlayerStateMachine _stateMachine, String _animBoolName, GamePanel _gp) {
		super(_player, _stateMachine, _animBoolName, _gp);
		getRunImage();
		setUpSound();
		
	}

	@Override
	public void enter() {
		super.enter();
		moveClip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	@Override
	public void exit() {
		super.exit();
     	stopSound();
	}

	@Override
	public void update() {
		super.update();
	//	playSound();
	
		int xInput= player.keyH.xInput; 
		player.setVelocitỵ̣̣̣̣(xInput*player.speed, this.player.Yvelocity);
		if(xInput==0||player.preventMove) {
			stateMachine.changeState(player.idleState);
		}
	}
	private void getRunImage() {
		try {
			player.run=	new BufferedImage[6];
			player.run[0]= ImageIO.read(getClass().getResourceAsStream("/PlayerRunImage/player-run1.png"));
			player.run[1]= ImageIO.read(getClass().getResourceAsStream("/PlayerRunImage/player-run2.png"));
			player.run[2]= ImageIO.read(getClass().getResourceAsStream("/PlayerRunImage/player-run3.png"));
			player.run[3]= ImageIO.read(getClass().getResourceAsStream("/PlayerRunImage/player-run4.png"));
			player.run[4]= ImageIO.read(getClass().getResourceAsStream("/PlayerRunImage/player-run5.png"));
			player.run[5]= ImageIO.read(getClass().getResourceAsStream("/PlayerRunImage/player-run6.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void setUpSound() {
		try {
			AudioInputStream ais= AudioSystem.getAudioInputStream(getClass().getResource("/music/run (mp3cut.net) (1).wav"));
			moveClip=AudioSystem.getClip();
			moveClip.open(ais);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void playSound() {
		moveClip.start();
	}
	private void stopSound() {
		moveClip.stop();
	}
}
