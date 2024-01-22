package PlayerState;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class PlayerFallState extends PlayerState {
	
	public PlayerFallState(Player _player, PlayerStateMachine _stateMachine, String _animBoolName, GamePanel _gp) {
		super(_player, _stateMachine, _animBoolName, _gp);
		getFallImage();
	}

	@Override
	public void enter() {
		super.enter();
	}

	@Override
	public void exit() {
		super.exit();
	}

	@Override
	public void update() {
		super.update();
		// ngan khi va cham
		setMoveSpeedWhenFall();

		if(player.collision.isGrounded&&player.collision.getySpeed()==0) {
			// them lenh getyspeed vi no chuyen qua player idle==> set veloc ve 0
			stateMachine.changeState(player.idleState);
		}
	}
	private void setMoveSpeedWhenFall() {
		if(player.preventMove) {
			return;
		}
		int xInput= player.keyH.xInput;
		if(xInput!=0)
			player.setVelocitỵ̣̣̣̣((xInput*(player.speed)*0.8f), this.player.Yvelocity);
		else {
			player.setVelocitỵ̣̣̣̣(0, player.Yvelocity);
		}		
	}

	private void getFallImage() {
		try {			
			player.fall= new BufferedImage[1];
			player.fall[0]= ImageIO.read(getClass().getResourceAsStream("/playerJumpImage/player-jump2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
