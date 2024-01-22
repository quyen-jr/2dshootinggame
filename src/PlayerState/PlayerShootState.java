package PlayerState;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class PlayerShootState extends PlayerState{
	private boolean oneShoot=true;
	public PlayerShootState(Player _player, PlayerStateMachine _stateMachine, String _animBoolName, GamePanel _gp) {
		super(_player, _stateMachine, _animBoolName, _gp);
		getShootImage();
	}

	@Override
	public void enter() {
		super.enter();
		player.setVelocitỵ̣̣̣̣(0, 0);
		stateTimer=0.25f;
	
	}

	@Override
	public void exit() {
		super.exit();
		oneShoot=true;
	}

	@Override
	public void update() {
		super.update();
		player.setVelocitỵ̣̣̣̣(0, 0);
		if(triggerCalled&&oneShoot) {
			player.createBullet();
			oneShoot=false;
		}
		if(triggerCalled&&stateTimer<0) {
			stateMachine.changeState(player.idleState);
		}
	}
	private void getShootImage() {
		try {
			player.shoot= new BufferedImage[3];
			player.shoot[0]=ImageIO.read(getClass().getResourceAsStream("/playerShootImage/player-shoot1.png"));
			player.shoot[1]=ImageIO.read(getClass().getResourceAsStream("/playerShootImage/player-shoot2.png"));
			player.shoot[2]=ImageIO.read(getClass().getResourceAsStream("/playerShootImage/player-shoot3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
