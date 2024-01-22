package PlayerState;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class PlayerDamagedState extends PlayerState {

	public PlayerDamagedState(Player _player, PlayerStateMachine _stateMachine, String _animBoolName, GamePanel _gp) {
		super(_player, _stateMachine, _animBoolName, _gp);
		getDamagedImage();
	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub
		super.enter();
		player.setVelocitỵ̣̣̣̣(-2, 3);
		stateTimer=0.09f;
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		super.exit();
		player.setVelocitỵ̣̣̣̣(0,player.Yvelocity);
	}

	@Override
	public void update() {
		super.update();
	//	int delta=player.faceDirection==1?1:-1;
		//System.out.println(player.preventMove);
		if(player.collision.canMoveOnLeftSide(player.facingGetKnockBack*7)&&player.faceDirection==1)
			player.setVelocitỵ̣̣̣̣(player.facingGetKnockBack*7, 3);
		else if(player.collision.canMoveOnRightSide(player.facingGetKnockBack*7)&&player.faceDirection==-1){
			player.setVelocitỵ̣̣̣̣(player.facingGetKnockBack*7, 3);
			
		}else {
			player.setVelocitỵ̣̣̣̣(0, 0);
			player.updateVelocity();
		}
		if(triggerCalled&&stateTimer<0) {
			stateMachine.changeState(player.idleState);
		}
	}
	private void getDamagedImage() {
		try {
			player.damaged= new BufferedImage[2];
			player.damaged[0]=ImageIO.read(getClass().getResourceAsStream("/playerDamagedImage/player-hurt1.png"));
			player.damaged[1]=ImageIO.read(getClass().getResourceAsStream("/playerDamagedImage/player-hurt2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
