package PlayerState;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class PlayerIdleState extends PlayerGroundedState{
	
	public PlayerIdleState(Player _player, PlayerStateMachine _stateMachine, String _animBoolName,GamePanel _gp) {
		super(_player, _stateMachine, _animBoolName,_gp);
		getIdleImage();
	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub
		super.enter();
		player.setVelocitỵ̣̣̣̣(0, 0);
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		super.exit();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		//System.out.println(121);
	}
	private void getIdleImage() {
		try {
				player.idle= new BufferedImage[6];
				player.idle[0]=ImageIO.read(getClass().getResourceAsStream("/player/player-idle1.png"));
				player.idle[1]=ImageIO.read(getClass().getResourceAsStream("/player/player-idle2.png"));
				player.idle[2]=ImageIO.read(getClass().getResourceAsStream("/player/player-idle3.png"));
				player.idle[3]=ImageIO.read(getClass().getResourceAsStream("/player/player-idle4.png"));
				player.idle[4]=ImageIO.read(getClass().getResourceAsStream("/player/player-idle5.png"));
				player.idle[5]=ImageIO.read(getClass().getResourceAsStream("/player/player-idle6.png"));
				player.image=player.idle[0];
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
