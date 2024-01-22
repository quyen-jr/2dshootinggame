package PlayerState;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.xml.sax.EntityResolver;

import Entity.Entity;
import main.GamePanel;

public class PlayerJumpState extends PlayerState {

	public PlayerJumpState(Player _player, PlayerStateMachine _stateMachine, String _animBoolName, GamePanel _gp) {
		super(_player, _stateMachine, _animBoolName, _gp);
		getJumpImage();
	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub
		super.enter();
	//	System.out.println("jump");
		player.setVelocitỵ̣̣̣̣(0, 0);
		player.jump();    
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		super.exit();
	}

	@Override
	public void update() {
		super.update();	
		setMoveSpeedWhenJump();
		if(player.collision.getySpeed()+player.collision.getAcceleration()>0) {
			player.setVelocitỵ̣̣̣̣(0, player.Yvelocity);
			stateMachine.changeState(player.fallState);
		}
	}

	private void setMoveSpeedWhenJump() {
		if(player.preventMove) {
		//	player.setVelocitỵ̣̣̣̣(0,player.Xvelocity );
			return;
		}
		int xInput= player.keyH.xInput;
		if(xInput!=0)
			player.setVelocitỵ̣̣̣̣((xInput*(player.speed)*0.8f), this.player.Yvelocity);
		else {
			player.setVelocitỵ̣̣̣̣(0, player.Yvelocity);
		}
	}
	private void getJumpImage() {
		try {
			player.jump= new BufferedImage[1];
			player.jump[0]= ImageIO.read(getClass().getResourceAsStream("/playerJumpImage/player-jump1.png"));
		//	player.jump[1]= ImageIO.read(getClass().getResourceAsStream("/playerJumpImage/player-jump2.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
